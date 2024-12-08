package org.lee;

import org.lee.common.generator.Generator;
import org.lee.resource.MetaEntry;
import org.lee.resource.SymbolTable;
import org.lee.context.SQLGeneratorContext;
import org.lee.sql.statement.select.SelectNormalStatement;
import org.lee.sql.statement.select.SelectStatement;
import org.slf4j.MDC;

import java.io.File;
import java.io.InputStream;

public class TestSingleSQLGenerator implements Generator<SelectStatement> {

    public TestSingleSQLGenerator(){
        File file = new File("logs/sql-generator.log");
        if(file.exists()){
            System.out.println("Remove log file ... ");
            file.delete();
        }
        MDC.put("traceID", "main");
        MDC.put("statementID", "main");
        load();
    }

    public static void load(){
        InputStream inputStream = TestLoadFrom.class.getClassLoader().getResourceAsStream("tpcds.json");
//        String jsonString = Utils.is2String(inputStream);
//        MetaEntry entry = SQLGeneratorContext.getCurrentMetaEntry();
//        SymbolTable symbolTable = SQLGeneratorContext.getCurrentSymbolTable();
//        entry.load(jsonObject);
//        symbolTable.init();
    }

    @Override
    public SelectStatement generate() {
//        SelectStatement statement = new SelectNormalStatement();
//        statement.fuzz();
//        return statement;
        return null;
    }

    public static String outputPath(){
        return "core/src/test/resources/temp_result.sql";
    }

    public static String outputInitializedPath(){
        return "core/src/test/resources/temp_init.sql";
    }
}
