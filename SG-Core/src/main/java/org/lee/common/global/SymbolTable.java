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

import java.io.InputStream;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class SymbolTable {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private static class Holder{
        public final TrieTree<TypeTag, Signature> finder = new TrieTree<>();
        public final Map<TypeTag, List<Signature>> reverseFinder = new EnumMap<>(TypeTag.class);

        void put(Signature signature){
            final TypeTag key = signature.getReturnType();
            finder.put(signature.getArgumentsTypes(), signature);
            if(!reverseFinder.containsKey(key)){
                reverseFinder.put(key, new Vector<>());
            }
            reverseFinder.get(key).add(signature);
        }

        List<Signature> getByReturn(TypeTag returnType){
            return reverseFinder.get(returnType);
        }

        List<Signature> getByInput(TypeTag ... input){
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
    private final Table<TypeTag, TypeTag, List<Signature>> cachedCaster = HashBasedTable.create();
    private final Table<TypeTag, TypeTag, Boolean> runtimeCachedUnreachableCasterPath = HashBasedTable.create();
    private final Map<TypeTag, List<Signature>> cachedLiteralFunction = new EnumMap<>(TypeTag.class);

    public void put(Operator operator){
        BUILTIN_OPERATOR_HOLDER.put(operator);
    }

    public void put(Function function){
        final Holder holder;
        if(function instanceof Aggregator){
            holder = function instanceof UserDefined ? UDAF_HOLDER : UDF_HOLDER;
        }else {
            holder = function instanceof UserDefined ? UDF_HOLDER : BUILTIN_FUNCTION_HOLDER;
        }
        holder.put(function);
    }

    private boolean isLoad = false;
    public SymbolTable(){}

    public synchronized void load(){
        if(isLoad){
            return;
        }
        isLoad = true;
        InputStream stream = SymbolTable.class.getClassLoader().getResourceAsStream("symbol.json");
        JSONObject symbols = new JSONObject(Utility.inputStreamToString(stream));
        JSONArray aggregationList = symbols.getJSONArray("aggregate");
        JSONArray functionList = symbols.getJSONArray("function");
        JSONArray operatorList = symbols.getJSONArray("operator");
        build(aggregationList, this::jsonToAggregation);
        build(functionList, this::jsonToFunction);
        build(operatorList, this::jsonToOperator);
        cache();
    }

    private void cache(){
        List<Signature> allLiteralFunction = BUILTIN_AGGREGATE_HOLDER.finder.get(Collections.emptyList());
        for(Signature signature: allLiteralFunction){
            TypeTag key = signature.getArgumentsTypes().get(0);
            List<Signature> container;
            if(cachedLiteralFunction.containsKey(key)){
                container = cachedLiteralFunction.get(key);
                container.add(signature);
            }else {
                cachedLiteralFunction.put(key, new ArrayList<Signature>(){{add(signature);}});
            }
        }

        for(TypeTag source: TypeTag.values()){
            List<Signature> casters = this.getFunction(source);
            for(Signature caster: casters){
                TypeTag target = caster.getReturnType();
                if(!cachedCaster.contains(source, target)){
                    cachedCaster.put(source, target, new ArrayList<>());
                }
                Objects.requireNonNull(cachedCaster.get(source, target)).add(caster);
            }
        }
    }

    private void build(JSONArray symbolArray, Consumer<JSONObject> process){
        long start = System.currentTimeMillis();
        StreamSupport.stream(symbolArray.spliterator(), true).forEach(
                json -> {
                    JSONObject aggregate = (JSONObject) json;
                    process.accept(aggregate);
                }
        );
        logger.debug("Build symbol elapse: " + (System.currentTimeMillis() - start));
    }

    private boolean checkDisabled(JSONObject jsonSymbol){
        if(jsonSymbol.has("enable") && !jsonSymbol.getBoolean("enable")){
            logger.debug("Disable by " + (jsonSymbol.has("reason") ? jsonSymbol.getString("reason") : "Unknown reason"));
            return true;
        }
        return false;
    }

    private void jsonToFunction(JSONObject function){
        if(checkDisabled(function)){
            return;
        }
        final String body = function.getString("body");
        final TypeTag returnType = TypeTag.getEnum(function.getString("return"));
        final TypeTag[] arguments = jsonArrayToTypeTags(function.getJSONArray("args"));
        this.put(new Function(body, returnType, arguments));
    }

    private void jsonToOperator(JSONObject operator){
        if(checkDisabled(operator)){
            return;
        }
        final String body = operator.getString("body");
        final TypeTag returnType = TypeTag.getEnum(operator.getString("return"));
        final TypeTag[] arguments = jsonArrayToTypeTags(operator.getJSONArray("args"));
        final int priority = operator.has("priority") ? operator.getInt("priority") : 1;
        this.put(new Operator(body, priority, returnType, arguments));
    }

    private void jsonToAggregation(JSONObject aggregate){
        if(checkDisabled(aggregate)){
            return;
        }
        final String body = aggregate.getString("body");
        final TypeTag returnType = TypeTag.getEnum(aggregate.getString("return"));
        final TypeTag[] arguments = jsonArrayToTypeTags(aggregate.getJSONArray("args"));
        this.put(new Aggregation(body, returnType, arguments));
    }

    private static TypeTag[] jsonArrayToTypeTags(JSONArray arr){
        TypeTag[] typeTags = new TypeTag[arr.length()];
        for(int i=0; i<arr.length(); i++){
            typeTags[i] = TypeTag.getEnum(arr.getString(i));
        }
        return typeTags;
    }

    public List<Signature> getAggregate(TypeTag input){
        return BUILTIN_AGGREGATE_HOLDER.finder.get(Collections.singletonList(input));
    }

    public List<Signature> getOperator(List<TypeTag> args){
        return BUILTIN_OPERATOR_HOLDER.finder.get(args);
    }

    public List<Signature> getFunction(List<TypeTag> tags){
        return BUILTIN_FUNCTION_HOLDER.finder.get(tags);
    }

    public List<Signature> getFunction(TypeTag ... tags){
        return BUILTIN_FUNCTION_HOLDER.finder.get(Arrays.asList(tags));
    }


    public List<Signature> getAggregateByReturn(TypeTag returnType){
        return BUILTIN_AGGREGATE_HOLDER.reverseFinder.get(returnType);
    }

    public List<Signature> getOperatorByReturn(TypeTag returnType){
        return BUILTIN_OPERATOR_HOLDER.reverseFinder.get(returnType);
    }

    public List<Signature> getFunctionByReturn(TypeTag returnType){
        return BUILTIN_FUNCTION_HOLDER.reverseFinder.get(returnType);
    }

    public Set<TypeTag> getAllAggregateReturnType(){
        return BUILTIN_AGGREGATE_HOLDER.reverseFinder.keySet();
    }

    public List<Signature> getOperatorAndFunctionByReturn(final TypeTag returnType){
        return new ArrayList<Signature>(getFunctionByReturn(returnType)){
            {
                addAll(getOperatorByReturn(returnType));
            }
        };
    }

    public List<Signature> getLiteralFunction(TypeTag returnType){
        return cachedLiteralFunction.getOrDefault(returnType, Collections.emptyList());
    }

    public List<Signature> getLiteralFunction(){
        final List<Signature> result = new ArrayList<>();
        for(List<Signature> v: cachedLiteralFunction.values()){
            result.addAll(v);
        }
        return result;
    }

    public List<Signature> getFunctionInPartial(List<TypeTag> tags){
        // todo:
        return null;
    }

    public int maxFunctionArgWidth(){
        return BUILTIN_FUNCTION_HOLDER.finder.maxWidth();
    }

    public Table<TypeTag, TypeTag, List<Signature>> getCasterTable(){
        return cachedCaster;
    }

    public List<Signature> getCaster(TypeTag source, TypeTag target){
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

    public List<Signature> findCasterSignatures(TypeTag source, TypeTag target, int maxDepth){
        final List<TypeTag> path = findCasterPath(source, target, maxDepth);
        final List<Signature> signatures = new ArrayList<>();
        for(TypeTag next: path){
            Signature signature = Utility.randomlyChooseFrom(this.getCaster(source, next));
            source = next;
            signatures.add(signature);
        }
        return signatures;
    }
}
