package org.lee.symbol;

import org.lee.type.TypeTag;

import java.util.HashMap;
import java.util.Map;

public class SymbolFinder {
    private static final class BuiltinOperatorHolder {
        static final SymbolTrieTree finder = new SymbolTrieTree();
        static final Map<TypeTag, Signature> reverseFinder = new HashMap<>();
    }
    private static final class BuiltinFunctionHolder {
        static final SymbolTrieTree finder = new SymbolTrieTree();
        static final Map<TypeTag, Signature> reverseFinder = new HashMap<>();
    }
    private static final class BuiltinAggregatorHolder {
        static final SymbolTrieTree finder = new SymbolTrieTree();
        static final Map<TypeTag, Signature> reverseFinder = new HashMap<>();
    }
    private static final class BuiltinWindowHolder {
        static final SymbolTrieTree finder = new SymbolTrieTree();
        static final Map<TypeTag, Signature> reverseFinder = new HashMap<>();
    }
    private static final class UserDefinedNormalOperationHolder{
        static final SymbolTrieTree finder = new SymbolTrieTree();
        static final Map<TypeTag, Signature> reverseFinder = new HashMap<>();
    }

    public static SymbolTrieTree getFunctionFinder() {
        return BuiltinFunctionHolder.finder;
    }
    public static SymbolTrieTree getOperatorFinder(){
        return BuiltinOperatorHolder.finder;
    }
    public static SymbolTrieTree getAggregatorFinder(){
        return BuiltinAggregatorHolder.finder;
    }
    public static SymbolTrieTree getWindowFinder(){
        return BuiltinWindowHolder.finder;
    }

    public static SymbolTrieTree getUDFFinder(){
        return UserDefinedNormalOperationHolder.finder;
    }
    public static SymbolTrieTree getUDAFFinder(){
        return UserDefinedNormalOperationHolder.finder;
    }

    public static Map<TypeTag, Signature> getBuiltinNormalOperationReverseFinder(){
        return BuiltinOperatorHolder.reverseFinder;
    }
    public static Map<TypeTag, Signature> getBuiltinAggregateOperationReverseFinder(){
        return BuiltinAggregatorHolder.reverseFinder;
    }
    public static Map<TypeTag, Signature> getBuiltinWindowOperationReverseFinder(){
        return BuiltinWindowHolder.reverseFinder;
    }
    public static Map<TypeTag, Signature> getUserDefinedNormalOperationReverseFinder(){
        return SymbolFinder.UserDefinedNormalOperationHolder.reverseFinder;
    }

    protected static void put(Operator operator){
        getAggregatorFinder().put(operator);
    }

    protected static void put(UserDefinedFunction userDefinedFunction){

    }

    private SymbolFinder(){

    }



}
