package org.lee;

import org.json.JSONObject;
import org.lee.common.global.MetaEntry;
import org.lee.base.Generator;
import org.lee.portal.SQLGeneratorContext;
import org.lee.statement.select.SelectNormalStatement;
import org.lee.statement.select.SelectStatement;
import org.lee.common.global.SymbolTable;
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
        MDC.put("stmtId", "main");
        load();
    }

    public static void load(){
        InputStream inputStream = TestLoadFrom.class.getClassLoader().getResourceAsStream("tpcds.json");
        String jsonString = Utils.is2String(inputStream);
        MetaEntry entry = SQLGeneratorContext.getCurrentMetaEntry();
        SymbolTable symbolTable = SQLGeneratorContext.getCurrentSymbolTable();
//        entry.load(jsonObject);
//        symbolTable.init();
    }

    @Override
    public SelectStatement generate() {
        SelectStatement statement = new SelectNormalStatement();
        statement.fuzz();
        return statement;
    }

    public static String outputPath(){
        return "src/test/resources/temp_result.sql";
    }

    public static String outputInitializedPath(){
        return "src/test/resources/temp_init.sql";
    }
}
