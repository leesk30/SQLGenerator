package org.lee.integration;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.lee.integration.client.Execution;
import org.lee.integration.spark.SparkClient;
import org.lee.context.SQLGeneratorContext;
import org.lee.testutils.Simple;

public class SparkClientTestModule {
    protected final SparkClient client = new SparkClient();
    protected final SQLGeneratorContext context = Simple.getSQLGeneratorContext("tpcds.json");

    public SparkClientTestModule(){
        ddl();
    }

    public void ddl(){
        String[] createTables = context.getMetaEntry().toDDLs(true, "USING PARQUET").split(";");
        for(String ddl: createTables){
            if(ddl.trim().isEmpty()){
                continue;
            }
            Execution<Dataset<Row>> execution = client.createExecution(ddl);
            System.out.println("Starting to execute: " + execution.getCode());
            execution.execute();
            boolean isSuccess = execution.isSuccess();

            if(!isSuccess){
                throw new RuntimeException(execution.throwable());
            }
        }
    }

    protected void execute(String code){
        Execution<Dataset<Row>> execution = client.createExecution(code);
        execution.execute();

        if(execution.isSuccess()){
            execution.get().show();
        }else {
            throw new RuntimeException(execution.throwable());
        }
    }
}
