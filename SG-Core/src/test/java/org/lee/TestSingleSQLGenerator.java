package org.lee;

import org.json.JSONObject;
import org.lee.common.MetaEntry;
import org.lee.fuzzer.Generator;
import org.lee.statement.SQLStatement;
import org.lee.statement.select.SelectNormalStatement;
import org.lee.statement.select.SelectStatement;

import java.io.InputStream;

public class TestSingleSQLGenerator implements Generator<SelectStatement> {

    public TestSingleSQLGenerator(){
        InputStream inputStream = TestLoadFrom.class.getClassLoader().getResourceAsStream("tpcds.json");
        String jsonString = Utils.is2String(inputStream);
        JSONObject jsonObject = new JSONObject(jsonString);
        MetaEntry.load(jsonObject);
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
}