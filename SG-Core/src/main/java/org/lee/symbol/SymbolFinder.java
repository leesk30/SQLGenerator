package org.lee.symbol;

import org.lee.type.TypeTag;

import java.util.HashMap;
import java.util.Map;

public class SymbolFinder {
    private static final class BuiltinNormalOperationHolder {
        static final SymbolTrieTree finder = new SymbolTrieTree();
        static final Map<TypeTag, Signature> reverseFinder = new HashMap<>();
    }
    private static final class BuiltinAggregateOperationHolder {
        static final SymbolTrieTree finder = new SymbolTrieTree();
        static final Map<TypeTag, Signature> reverseFinder = new HashMap<>();
    }
    private static final class BuiltinWindowOperationHolder {
        static final SymbolTrieTree finder = new SymbolTrieTree();
        static final Map<TypeTag, Signature> reverseFinder = new HashMap<>();
    }
    private static final class UserDefinedNormalOperationHolder{
        static final SymbolTrieTree finder = new SymbolTrieTree();
        static final Map<TypeTag, Signature> reverseFinder = new HashMap<>();
    }


    public static SymbolTrieTree getBuiltinNormalOperationFinder(){
        return BuiltinNormalOperationHolder.finder;
    }
    public static SymbolTrieTree getBuiltinAggregateOperationFinder(){
        return BuiltinAggregateOperationHolder.finder;
    }
    public static SymbolTrieTree getBuiltinWindowOperationFinder(){
        return BuiltinWindowOperationHolder.finder;
    }
    public static SymbolTrieTree getUserDefinedNormalOperationFinder(){
        return UserDefinedNormalOperationHolder.finder;
    }
    public static Map<TypeTag, Signature> getBuiltinNormalOperationReverseFinder(){
        return SymbolFinder.BuiltinNormalOperationHolder.reverseFinder;
    }
    public static Map<TypeTag, Signature> getBuiltinAggregateOperationReverseFinder(){
        return SymbolFinder.BuiltinAggregateOperationHolder.reverseFinder;
    }
    public static Map<TypeTag, Signature> getBuiltinWindowOperationReverseFinder(){
        return SymbolFinder.BuiltinWindowOperationHolder.reverseFinder;
    }
    public static Map<TypeTag, Signature> getUserDefinedNormalOperationReverseFinder(){
        return SymbolFinder.UserDefinedNormalOperationHolder.reverseFinder;
    }

    protected static void put(BuiltinOperation builtinOperation){

    }

    protected static void put(UserDefinedFunction userDefinedFunction){

    }

}
