package org.lee.integration;

import org.testng.annotations.Test;

public class IssueTesting extends SparkClientTestModule {

    @Test
    public void testIssueMissingGroupByAttribute(){
        execute("USE DATABASE db1");
//        execute(sql_string);
    }
}
