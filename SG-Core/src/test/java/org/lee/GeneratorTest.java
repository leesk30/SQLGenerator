package org.lee;

import org.lee.common.MetaEntry;
import org.lee.base.Generator;
import org.lee.common.Utility;
import org.lee.statement.SQLFormatter;
import org.lee.statement.select.SelectStatement;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.IntStream;

public class GeneratorTest {

    @Test
    public static void generateWords(){
        char[] charset = {
          'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'g', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'G', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        };
        IntStream.range(0, 100).parallel().forEach(i -> System.out.print(charset[Utility.randomIntFromRange(0, charset.length)]));
    }

    @Test
    public static void generateString(){
        final int maxLength = 2;
        String res = Utility.randomStringByLength(maxLength);
        assert res.length() <= maxLength;
        System.out.println(res.length());
        System.out.println(res);
        System.out.println(Utility.stringToLikePattern(res));
    }

    @Test
    public static void generateDDLAndData() throws IOException {
        TestSingleSQLGenerator.load();
        String results = MetaEntry.toDDLs(true, "USING PARQUET");
        try (final FileWriter fileWriter = new FileWriter(TestSingleSQLGenerator.outputInitializedPath())){
            fileWriter.write(results);
            fileWriter.write(MetaEntry.toInitializedInserts());
            fileWriter.flush();
        }
    }

    @Test
    public static void testGenerator() throws IOException {
        Generator<SelectStatement> generator = new TestSingleSQLGenerator();
        long startAt = System.currentTimeMillis();
        String output = TestSingleSQLGenerator.outputPath();
        System.out.println("Output dir is: " + output);
        final SQLFormatter formatter = new SQLFormatter();
        try (final FileWriter writer = new FileWriter(output)){
            IntStream.range(0, 1000).parallel().forEach(
                    i -> {
                        SelectStatement statement = generator.generate();
                        String result = statement.getString();
                        try {
                            synchronized (writer){
                                writer.write("----------->\n" + formatter.format(result) + "\n");
                                writer.flush();
                            }
                        } catch (IOException e) {
                            System.out.println("ERROR: " + e.getMessage());
                            e.printStackTrace();
                            throw new RuntimeException(e);
                        }
                    }
            );
        }
        System.out.printf("Generate elapse: %d ms%n", System.currentTimeMillis() - startAt);
    }
}
