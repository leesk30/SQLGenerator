package org.lee.testutils;

import org.lee.SQLGeneratorContext;
import org.lee.common.Utility;
import org.lee.common.global.SymbolTable;
import org.lee.symbol.Signature;
import org.lee.type.TypeTag;

import java.util.ArrayList;
import java.util.List;

public class Convert {

    public static List<Signature> pathToSignature(TypeTag source, List<TypeTag> path){
        final SymbolTable symbolTable = SQLGeneratorContext.getCurrentSymbolTable();
        final List<Signature> signatures = new ArrayList<>();
        for(TypeTag target: path){
            Signature signature = Utility.randomlyChooseFrom(symbolTable.getCaster(source, target));
            source = target;
            signatures.add(signature);
        }
        return signatures;
    }
}
