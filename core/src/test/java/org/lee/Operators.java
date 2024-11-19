package org.lee;

import org.lee.resource.SymbolTable;
import org.lee.sql.SQLGeneratorContext;
import org.lee.sql.type.TypeTag;

public class Operators {

    public static void main(String[] args) {
        SymbolTable symbolTable = SQLGeneratorContext.getCurrentSymbolTable();
        symbolTable.getOperatorByReturn(TypeTag.null_);

    }
}
