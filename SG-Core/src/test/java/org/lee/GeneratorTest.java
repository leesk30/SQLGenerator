package org.lee;

import org.lee.common.MetaEntry;
import org.lee.fuzzer.Generator;
import org.lee.statement.SQLFormatter;
import org.lee.statement.select.SelectStatement;
import org.lee.symbol.Finder;
import org.lee.symbol.Function;
import org.lee.type.TypeTag;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.IntStream;

public class GeneratorTest {

    @Test
    public static void testGenerator() throws IOException {
        Generator<SelectStatement> generator = new TestSingleSQLGenerator();
        long startAt = System.currentTimeMillis();
        Finder finder = Finder.getFinder();
        Finder.load();
        MetaEntry.relationMap.values().forEach(r -> {
            System.out.println(r.toDDL(true, " USING PARQUET "));
        });
        finder.put(new Function("abs(%s)", TypeTag.decimal, TypeTag.decimal));
        String output = TestSingleSQLGenerator.outputPath();
        System.out.println("Output dir is: " + output);
        final SQLFormatter formatter = new SQLFormatter();
        try (final FileWriter writer = new FileWriter(output)){
            IntStream.range(0, 1000).forEach(
                    i -> {
                        SelectStatement statement = generator.generate();
                        String result = statement.getString();
                        try {
                            writer.write("----------->\n" + formatter.format(result) + "\n");
                            writer.flush();
                        } catch (IOException e) {
                            System.out.println("ERROR: " + e.getMessage());
                            throw new RuntimeException(e);
                        }
                    }
            );
        }
        System.out.printf("Generate elapse: %d ms%n", System.currentTimeMillis() - startAt);
    }
}
