package org.lee;

import org.lee.fuzzer.Generator;
import org.lee.statement.SQLFormatter;
import org.lee.statement.select.SelectStatement;
import org.lee.symbol.Finder;
import org.lee.symbol.Function;
import org.lee.type.TypeTag;
import org.testng.annotations.Test;

import java.util.stream.IntStream;

public class GeneratorTest {

    @Test
    public static void testGenerator(){
        Generator<SelectStatement> generator = new TestSingleSQLGenerator();
        long startAt = System.currentTimeMillis();
        Finder finder = Finder.getFinder();
        Finder.load();
        finder.put(new Function("abs(%s)", TypeTag.decimal, TypeTag.decimal));
        SQLFormatter formatter = new SQLFormatter();
        IntStream.range(0, 1000).forEach(
                i -> {
                    SelectStatement statement = generator.generate();
                    String result = statement.getString();
                    System.out.println("----------->\n" + formatter.format(result));
                }
        );
        System.out.printf("Generate elapse: %d ms%n", System.currentTimeMillis() - startAt);
    }
}
