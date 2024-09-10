package org.lee;

import org.lee.fuzzer.Generator;
import org.lee.statement.SQLFormatter;
import org.lee.statement.select.SelectStatement;
import org.testng.annotations.Test;

import java.util.stream.IntStream;

public class GeneratorTest {

    @Test
    public static void testGenerator(){
        Generator<SelectStatement> generator = new TestSingleSQLGenerator();
        long startAt = System.currentTimeMillis();
        SQLFormatter formatter = new SQLFormatter();
        IntStream.range(0, 100).forEach(
                i -> {
                    SelectStatement statement = generator.generate();
                    String result = statement.getString();
                    System.out.println("----------->\n" + formatter.format(result));
                }
        );
        System.out.printf("Generate elapse: %d ms%n", System.currentTimeMillis() - startAt);
    }
}
