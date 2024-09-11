package org.lee.symbol;

import org.lee.type.TypeTag;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Finder {

    private static class Holder{
        public final SymbolTrieTree finder = new SymbolTrieTree();
        public final Map<TypeTag, List<Signature>> reverseFinder = new ConcurrentHashMap<>();

        synchronized void put(Signature signature){
            final TypeTag key = signature.getReturnType();
            finder.put(signature);
            if(!reverseFinder.containsKey(key)){
                reverseFinder.put(key, new Vector<>());
            }
            reverseFinder.get(key).add(signature);
        }

        List<Signature> getByReturn(TypeTag returnType){
            return reverseFinder.get(returnType);
        }

        List<Signature> getByInput(TypeTag ... input){
            return finder.get(input);
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
        return finder;
    }

    public static final Finder finder = new Finder();
    private static boolean isLoad = false;
    private Finder(){}

    public static void load(){
        if(isLoad){
            return;
        }
        isLoad = true;
        TypeTag.stream().parallel()
                .peek(typeTag -> finder.put(Aggregation.buildCount(typeTag)))
                .peek(typeTag -> {
                    if(!typeTag.isComparable())
                        return;
                    finder.put(Aggregation.buildMax(typeTag));
                    finder.put(Aggregation.buildMin(typeTag));
                })
                .filter(TypeTag::isComputable)
                .peek(typeTag -> finder.put(Aggregation.buildSum(typeTag)))
                .forEach(typeTag -> finder.put(Aggregation.buildAvg(typeTag)))
                ;
    }
}
