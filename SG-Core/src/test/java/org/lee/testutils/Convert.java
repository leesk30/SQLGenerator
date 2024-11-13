package org.lee.testutils;

import org.lee.sql.SQLGeneratorContext;
import org.lee.common.Utility;
import org.lee.common.global.SymbolTable;
import org.lee.sql.symbol.Symbol;
import org.lee.sql.type.TypeTag;

import java.util.ArrayList;
import java.util.List;

public class Convert {

    public static List<Symbol> pathToSignature(TypeTag source, List<TypeTag> path){
        final SymbolTable symbolTable = SQLGeneratorContext.getCurrentSymbolTable();
        final List<Symbol> symbols = new ArrayList<>();
        for(TypeTag target: path){
            Symbol symbol = Utility.randomlyChooseFrom(symbolTable.getCaster(source, target));
            source = target;
            symbols.add(symbol);
        }
        return symbols;
    }
}
