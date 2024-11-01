package org.lee;

import org.lee.common.global.SymbolTable;
import org.lee.portal.SQLGeneratorContext;
import org.lee.type.TypeTag;

public class Operators {

    public static void main(String[] args) {
        SymbolTable symbolTable = SQLGeneratorContext.getCurrentSymbolTable();
        symbolTable.getOperatorByReturn(TypeTag.null_);

    }
}
