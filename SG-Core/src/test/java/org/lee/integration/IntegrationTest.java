package org.lee.integration;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.lee.generator.statement.SQLGenerator;
import org.lee.integration.client.Client;
import org.lee.integration.client.Execution;
import org.lee.integration.spark.SparkClient;
import org.lee.sql.SQLGeneratorContext;
import org.lee.sql.statement.select.SelectStatement;
import org.lee.testutils.Simple;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class IntegrationTest{

    @Test
    public void testSparkWithoutData(){
        /** TODO optimize */
        Client<Dataset<Row>> client = new SparkClient();
        SQLGeneratorContext context = Simple.getSQLGeneratorContext("tpcds.json");
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

        SQLGenerator generator = context.getGenerator();
        Map<String, Integer> exceptionCounter = new HashMap<>();
        int total = 500;
        int successCount = 0;
        for(int i=0; i < total; i++){
            SelectStatement statement = generator.generateSelect();
            String code = statement.getString();
            System.out.println("Generated: " + code);
            Execution<Dataset<Row>> execution = client.createExecution(code);
            execution.execute();

            if(execution.isSuccess()){
                successCount++;
            }else {
                Throwable exception = execution.throwable();
                String message = exception.getMessage();
                System.out.println("Execute failed with: " + execution.getClass().getName());
                System.out.println(message);
                System.out.println();
                String key = exception.getClass().getName() + ":" + message;
                if(!exceptionCounter.containsKey(key)){
                    exceptionCounter.put(key, 1);
                }else {
                    exceptionCounter.put(key, exceptionCounter.get(key) + 1);
                }
            }
            System.out.println("==========================================");
        }

        double rate = successCount / (double) total;
        String[] arr = new String[exceptionCounter.size()];
        exceptionCounter.keySet().toArray(arr);
        Arrays.sort(arr, Comparator.comparing(exceptionCounter::get));

        System.out.println("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
        System.out.println("Success Rate: " + rate);
        System.out.println("Total Exception: " + exceptionCounter.values().stream().mapToInt(Integer::intValue).sum());
        System.out.println("Top 10 exceptions: ");
        int bound = Math.min(10, arr.length);
        for(int j = 0; j < bound; j++){
            System.out.println("!!!========================================");
            System.out.println(arr[j]);
            System.out.println("!!!========================================");
        }

        assert rate > 0.98;
    }
}
