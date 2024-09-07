package org.lee;

import org.lee.fuzzer.Generator;
import org.lee.statement.select.SelectStatement;
import org.testng.annotations.Test;

public class GeneratorTest {

    @Test
    public static void testGenerator(){
        Generator<SelectStatement> generator = new TestSingleSQLGenerator();
        long startAt = System.currentTimeMillis();
        SelectStatement statement = generator.generate();
        System.out.printf("Generate elapse: %d ms%n", System.currentTimeMillis() - startAt);
        String result = statement.getString();
        System.out.println("----------->\n" + result);
    }
}
