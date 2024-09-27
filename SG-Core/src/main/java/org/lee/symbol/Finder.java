package org.lee.symbol;

import org.json.JSONArray;
import org.json.JSONObject;
import org.lee.type.TypeTag;
import org.lee.common.util.FileUtil;
import org.lee.common.TrieTree;

import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

public class Finder {

    private static class Holder{
        public final TrieTree<TypeTag, Signature> finder = new TrieTree<>();
        public final Map<TypeTag, List<Signature>> reverseFinder = new ConcurrentHashMap<>();

        synchronized void put(Signature signature){
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

    private static final Holder BUILTIN_OPERATOR_HOLDER = new Holder();
    private static final Holder BUILTIN_FUNCTION_HOLDER = new Holder();
    private static final Holder BUILTIN_AGGREGATE_HOLDER = new Holder();
    private static final Holder BUILTIN_WINDOW_HOLDER = new Holder();

    private static final Holder UDF_HOLDER = new Holder();
    private static final Holder UDTF_HOLDER = new Holder();
    private static final Holder UDAF_HOLDER = new Holder();

    public void put(Operator operator){
        BUILTIN_OPERATOR_HOLDER.put(operator);
    }

    public void put(Function function){
        if(function instanceof Aggregator){
            if(function instanceof UserDefined){
                UDAF_HOLDER.put(function);
            }else {
                BUILTIN_AGGREGATE_HOLDER.put(function);
            }
            return;
        }
        if(function instanceof UserDefined){
            UDF_HOLDER.put(function);
        }else {
            BUILTIN_FUNCTION_HOLDER.put(function);
        }
    }

    public static Finder getFinder(){
        return singleton;
    }

    public static final Finder singleton = new Finder();
    private static boolean isLoad = false;
    private Finder(){}

    public synchronized static void load(){
        if(isLoad){
            return;
        }
        isLoad = true;
        InputStream stream = Finder.class.getClassLoader().getResourceAsStream("symbol.json");
        JSONObject symbols = new JSONObject(FileUtil.inputStreamToString(stream));
        JSONArray aggregationList = symbols.getJSONArray("aggregate");
        JSONArray functionList = symbols.getJSONArray("function");
        JSONArray operatorList = symbols.getJSONArray("operator");
        build(aggregationList, Finder::jsonToAggregation);
        build(functionList, Finder::jsonToFunction);
        build(operatorList, Finder::jsonToOperator);
    }

    private static void build(JSONArray symbolArray, Consumer<JSONObject> process){
        long start = System.currentTimeMillis();
        StreamSupport.stream(symbolArray.spliterator(), true).forEach(
                json -> {
                    JSONObject aggregate = (JSONObject) json;
                    process.accept(aggregate);
                }
        );
        System.out.println("Build symbol elapse: " + (System.currentTimeMillis() - start));
    }

    private static boolean checkDisabled(JSONObject jsonSymbol){
        if(jsonSymbol.has("enable") && !jsonSymbol.getBoolean("enable")){
            System.out.println("Disable by " + (jsonSymbol.has("reason") ? jsonSymbol.getString("reason") : "Unknown reason"));
            return true;
        }
        return false;
    }

    private static void jsonToFunction(JSONObject function){
        if(checkDisabled(function)){
            return;
        }
        final String body = function.getString("body");
        final TypeTag returnType = TypeTag.getEnum(function.getString("return"));
        final TypeTag[] arguments = jsonArrayToTypeTags(function.getJSONArray("args"));
        singleton.put(new Function(body, returnType, arguments));
    }

    private static void jsonToOperator(JSONObject operator){
        if(checkDisabled(operator)){
            return;
        }
        final String body = operator.getString("body");
        final TypeTag returnType = TypeTag.getEnum(operator.getString("return"));
        final TypeTag[] arguments = jsonArrayToTypeTags(operator.getJSONArray("args"));
        final int priority = operator.has("priority") ? operator.getInt("priority") : 1;
        singleton.put(new Operator(body, priority, returnType, arguments));
    }

    private static void jsonToAggregation(JSONObject aggregate){
        if(checkDisabled(aggregate)){
            return;
        }
        final String body = aggregate.getString("body");
        final TypeTag returnType = TypeTag.getEnum(aggregate.getString("return"));
        final TypeTag[] arguments = jsonArrayToTypeTags(aggregate.getJSONArray("args"));
        singleton.put(new Aggregation(body, returnType, arguments));
    }

    private static TypeTag[] jsonArrayToTypeTags(JSONArray arr){
        TypeTag[] typeTags = new TypeTag[arr.length()];
        IntStream.range(0, arr.length()).sequential().forEach(i->typeTags[i] = TypeTag.getEnum(arr.getString(i)));
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

    public List<Signature> getFunctionInPartial(List<TypeTag> tags){
        // todo:
        return null;
    }

    public int maxFunctionArgWidth(){
        return BUILTIN_FUNCTION_HOLDER.finder.maxWidth();
    }
}
