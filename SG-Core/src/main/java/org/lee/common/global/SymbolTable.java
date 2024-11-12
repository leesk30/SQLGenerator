package org.lee.common.global;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.json.JSONArray;
import org.json.JSONObject;
import org.lee.common.Utility;
import org.lee.common.structure.TrieTree;
import org.lee.symbol.*;
import org.lee.type.TypeTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class SymbolTable implements Resource<JSONObject> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private static final String KEY_FUNCTION = "function";
    private static final String KEY_OPERATOR = "operator";
    private static final String KEY_AGGREGATE = "aggregate";

    private static final String KEY_ENABLE = "enable";
    private static final String KEY_REASON = "reason";
    private static final String KEY_BODY = "body";
    private static final String KEY_ARGS = "args";
    private static final String KEY_PRIORITY = "priority";
    private static final String KEY_RETURN = "return";

    private static class Holder{
        public final TrieTree<TypeTag, Symbol> finder = new TrieTree<>();
        public final Map<TypeTag, List<Symbol>> reverseFinder = new EnumMap<>(TypeTag.class);

        void put(Symbol symbol){
            final TypeTag key = symbol.getReturnType();
            finder.put(symbol.getArgumentsTypes(), symbol);
            if(!reverseFinder.containsKey(key)){
                reverseFinder.put(key, new Vector<>());
            }
            reverseFinder.get(key).add(symbol);
        }

        List<Symbol> getByReturn(TypeTag returnType){
            return reverseFinder.get(returnType);
        }

        List<Symbol> getByInput(TypeTag ... input){
            return finder.get(Arrays.asList(input));
        }
    }

    private final Holder BUILTIN_OPERATOR_HOLDER = new Holder();
    private final Holder BUILTIN_FUNCTION_HOLDER = new Holder();
    private final Holder BUILTIN_AGGREGATE_HOLDER = new Holder();
    private final Holder BUILTIN_WINDOW_HOLDER = new Holder();

    private final Holder UDF_HOLDER = new Holder();
    private final Holder UDTF_HOLDER = new Holder();
    private final Holder UDAF_HOLDER = new Holder();
    private final Table<TypeTag, TypeTag, List<Symbol>> cachedCaster = HashBasedTable.create();
    private final Table<TypeTag, TypeTag, Boolean> runtimeCachedUnreachableCasterPath = HashBasedTable.create();
    private final Map<TypeTag, List<Symbol>> cachedLiteralFunction = new EnumMap<>(TypeTag.class);

    public void put(Operator operator){
        BUILTIN_OPERATOR_HOLDER.put(operator);
    }

    public void put(Function function){
        final Holder holder;
        if(function instanceof Aggregator){
            holder = function instanceof UserDefined ? UDAF_HOLDER : BUILTIN_AGGREGATE_HOLDER;
        }else {
            holder = function instanceof UserDefined ? UDF_HOLDER : BUILTIN_FUNCTION_HOLDER;
        }
        holder.put(function);
    }

    private boolean isLoad = false;
    public SymbolTable(){}

    @Override
    public synchronized void init(JSONObject symbols){
        if(isLoad){
            return;
        }
        isLoad = true;
        build(KEY_AGGREGATE, symbols, this::jsonToAggregation);
        build(KEY_FUNCTION, symbols, this::jsonToFunction);
        build(KEY_OPERATOR, symbols, this::jsonToOperator);
        cache();
    }

    private void cache(){
        List<Symbol> allLiteralFunction = BUILTIN_AGGREGATE_HOLDER.finder.get(Collections.emptyList());
        for(Symbol symbol : allLiteralFunction){
            TypeTag key = symbol.getArgumentsTypes().get(0);
            List<Symbol> container;
            if(cachedLiteralFunction.containsKey(key)){
                container = cachedLiteralFunction.get(key);
                container.add(symbol);
            }else {
                cachedLiteralFunction.put(key, new ArrayList<Symbol>(){{add(symbol);}});
            }
        }

        for(TypeTag source: TypeTag.values()){
            List<Symbol> casters = this.getFunction(source);
            for(Symbol caster: casters){
                TypeTag target = caster.getReturnType();
                if(!cachedCaster.contains(source, target)){
                    cachedCaster.put(source, target, new ArrayList<>());
                }
                Objects.requireNonNull(cachedCaster.get(source, target)).add(caster);
            }
        }
    }

    private void build(String head, JSONObject symbols, Consumer<JSONObject> process){
        JSONArray symbolArray = symbols.getJSONArray(head);
        long start = System.currentTimeMillis();
        int total = symbolArray.length();
        for(int i=0; i < symbolArray.length(); i++){
            JSONObject symbol = symbolArray.getJSONObject(i);
            process.accept(symbol);
        }
        logger.debug(String.format("Build %d symbols for %s elapse: %d ms",  total, head, (System.currentTimeMillis() - start)));
    }

    private boolean checkDisabled(JSONObject symbol){
        if(symbol.has(KEY_ENABLE) && !symbol.getBoolean(KEY_ENABLE)){
            String reason = symbol.has(KEY_REASON) ? symbol.getString(KEY_REASON) : "Unknown reason";
            logger.debug("Disable by " + reason);
            return true;
        }
        return false;
    }

    private void jsonToFunction(JSONObject function){
        if(checkDisabled(function)){
            return;
        }
        final String body = function.getString(KEY_BODY);
        final TypeTag returnType = TypeTag.getEnum(function.getString(KEY_RETURN));
        final TypeTag[] arguments = jsonArrayToTypeTags(function.getJSONArray(KEY_ARGS));
        this.put(new Function(body, returnType, arguments));
    }

    private void jsonToOperator(JSONObject operator){
        if(checkDisabled(operator)){
            return;
        }
        final String body = operator.getString(KEY_BODY);
        final TypeTag returnType = TypeTag.getEnum(operator.getString(KEY_RETURN));
        final TypeTag[] arguments = jsonArrayToTypeTags(operator.getJSONArray(KEY_ARGS));
        final int priority = operator.has(KEY_PRIORITY) ? operator.getInt(KEY_PRIORITY) : 1;
        this.put(new Operator(body, priority, returnType, arguments));
    }

    private void jsonToAggregation(JSONObject aggregate){
        if(checkDisabled(aggregate)){
            return;
        }
        final String body = aggregate.getString(KEY_BODY);
        final TypeTag returnType = TypeTag.getEnum(aggregate.getString(KEY_RETURN));
        final TypeTag[] arguments = jsonArrayToTypeTags(aggregate.getJSONArray(KEY_ARGS));
        this.put(new Aggregation(body, returnType, arguments));
    }

    private static TypeTag[] jsonArrayToTypeTags(JSONArray arr){
        TypeTag[] typeTags = new TypeTag[arr.length()];
        for(int i=0; i<arr.length(); i++){
            typeTags[i] = TypeTag.getEnum(arr.getString(i));
        }
        return typeTags;
    }

    public List<Symbol> getAggregate(TypeTag input){
        return BUILTIN_AGGREGATE_HOLDER.finder.get(Collections.singletonList(input));
    }

    public List<Symbol> getOperator(List<TypeTag> args){
        return BUILTIN_OPERATOR_HOLDER.finder.get(args);
    }

    public List<Symbol> getOperator(TypeTag ... args){
        return BUILTIN_OPERATOR_HOLDER.finder.get(Arrays.asList(args));
    }

    public List<Symbol> getFunction(List<TypeTag> tags){
        return BUILTIN_FUNCTION_HOLDER.finder.get(tags);
    }

    public List<Symbol> getFunction(TypeTag ... tags){
        return BUILTIN_FUNCTION_HOLDER.finder.get(Arrays.asList(tags));
    }


    public List<Symbol> getAggregateByReturn(TypeTag returnType){
        return BUILTIN_AGGREGATE_HOLDER.reverseFinder.get(returnType);
    }

    public List<Symbol> getOperatorByReturn(TypeTag returnType){
        return BUILTIN_OPERATOR_HOLDER.reverseFinder.get(returnType);
    }

    public List<Symbol> getFunctionByReturn(TypeTag returnType){
        return BUILTIN_FUNCTION_HOLDER.reverseFinder.get(returnType);
    }

    public Set<TypeTag> getAllAggregateReturnType(){
        return BUILTIN_AGGREGATE_HOLDER.reverseFinder.keySet();
    }

    public List<Symbol> getOperatorAndFunctionByReturn(final TypeTag returnType){
        return new ArrayList<Symbol>(getFunctionByReturn(returnType)){
            {
                addAll(getOperatorByReturn(returnType));
            }
        };
    }

    public List<Symbol> getLiteralFunction(TypeTag returnType){
        return cachedLiteralFunction.getOrDefault(returnType, Collections.emptyList());
    }

    public List<Symbol> getLiteralFunction(){
        final List<Symbol> result = new ArrayList<>();
        for(List<Symbol> v: cachedLiteralFunction.values()){
            result.addAll(v);
        }
        return result;
    }

    public List<Symbol> getFunctionInPartial(List<TypeTag> tags){
        // todo:
        return null;
    }

    public int maxFunctionArgWidth(){
        return BUILTIN_FUNCTION_HOLDER.finder.maxWidth();
    }

    public Table<TypeTag, TypeTag, List<Symbol>> getCasterTable(){
        return cachedCaster;
    }

    public List<Symbol> getCaster(TypeTag source, TypeTag target){
        if(cachedCaster.contains(source, target)){
            return cachedCaster.get(source, target);
        }
        return Collections.emptyList();
    }

    public boolean existsCaster(TypeTag source, TypeTag target){
        return cachedCaster.contains(source, target) && !Objects.requireNonNull(cachedCaster.get(source, target)).isEmpty();
    }

    private static List<TypeTag> getConvertRoute(final TypeTag source, final TypeTag target){
        return Arrays.stream(TypeTag.values()).filter(t -> t!=target && t!=source).collect(Collectors.toList());
    }

    public List<TypeTag> findCasterPath(final TypeTag source, final TypeTag target, final int maxCastingDepth) {
        if (runtimeCachedUnreachableCasterPath.contains(source, target)) {
            logger.error(String.format("Runtime skip unreachable convert path source<%s> to target<%s>.", source, target));
            return Collections.emptyList();
        }

        final List<TypeTag> route = getConvertRoute(source, target);
        Collections.shuffle(route);
        final List<TypeTag> path = new ArrayList<>();
        Set<TypeTag> visited = EnumSet.noneOf(TypeTag.class);
        Stack<CasterSearchState> stack = new Stack<>();
        stack.push(new CasterSearchState(source, 0)); // initialize
        while (!stack.isEmpty()) {
            CasterSearchState state = stack.peek();
            TypeTag currentSource = state.currentNode;
            int depth = stack.size() - 1;

            if (this.existsCaster(currentSource, target)) {
                // i=1 for skip root node
                for(int i=1; i < stack.size(); i++){
                    CasterSearchState s = stack.get(i);
                    path.add(s.currentNode);
                }
                path.add(target);
                break;
            }

            if (depth >= maxCastingDepth || state.index >= route.size()) {
                CasterSearchState s = stack.pop();
                visited.remove(s.currentNode);
                continue;
            }

            TypeTag nextTarget = route.get(state.index++);
            // recycle check
            if (this.existsCaster(currentSource, nextTarget) && !visited.contains(nextTarget)) {
                stack.push(new CasterSearchState(nextTarget, 0));
                visited.add(nextTarget);
            }
        }

        if (path.isEmpty() || path.get(path.size() - 1) != target) {
            runtimeCachedUnreachableCasterPath.put(source, target, true);
            return Collections.emptyList();
        }
        return path;
    }

    private static class CasterSearchState {
        TypeTag currentNode;
        int index;

        CasterSearchState(TypeTag currentNode, int index) {
            this.currentNode = currentNode;
            this.index = index;
        }
    }

    public List<Symbol> findCasterSignatures(TypeTag source, TypeTag target, int maxDepth){
        final List<TypeTag> path = findCasterPath(source, target, maxDepth);
        final List<Symbol> symbols = new ArrayList<>();
        for(TypeTag next: path){
            Symbol symbol = Utility.randomlyChooseFrom(this.getCaster(source, next));
            source = next;
            symbols.add(symbol);
        }
        return symbols;
    }
}
