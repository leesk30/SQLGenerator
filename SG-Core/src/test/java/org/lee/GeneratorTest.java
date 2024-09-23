package org.lee;

import org.lee.common.MetaEntry;
import org.lee.fuzzer.Generator;
import org.lee.statement.SQLFormatter;
import org.lee.statement.select.SelectStatement;
import org.lee.symbol.Finder;
import org.lee.symbol.Function;
import org.lee.type.TypeTag;
import org.lee.util.FuzzUtil;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.IntStream;

public class GeneratorTest {

    @Test
    public static void generateWords(){
        String path = "D:\\Projects\\Rains\\SQLGenerator\\SG-Core\\src\\main\\resources\\strings.txt";
        char[] charset = {
          'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'g', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'G', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        };
        System.out.println("pqMBSrNGkQP78mgFD7uMAiotWhTaaBQEBcZVKnKWTtmLEUBvwVP9qPvqlUps6s8VKqGxcnsiZB4KiL8u6LiIehMGqg8a0ShmiirXgtWtd1g1EZMPe9Ie1fsT6nIOEYLVw6ABPN95x2dsp6AODtb4KwUgQL2uvtazTMQEF65ClRy6iYQNz6z12nd3czbdd0F4GxTfqVCQ2qkchnmOhq9xGgwDz8e6sDCQ7dNHckAmXRcYSiz6MEHr35dgCACChm3MrbbTgVLiLV4IGqMtdYxgvOLRcsDPPeKHAt34NCFE3FDamIbXWg5B6gAhW8QbFv1D77hZrBdkUgroLXbU560WdScdakBIIftv3ZExTweWD34CGfpTZSkGPlOged56mZ3xANlCENHxF9KZD2KIAxqw11Z9GV2SdxPWlswp0ZBYLu00L79eiW87DM1AnzGAg11VWqFRzVnVFqGAEvA1Mrx27xEAoTER1kKwElYmDpTnP5PVQc5YCZ3Hrmk9GIlzDZsu9PtABMGm27fKNbYgKBOVDvr4rRrwk5vHLH0a2nrK7WavDgHvUhoRVIzP8TPuvrkpLeSGO2bkn90RhGa4GvGmDOLxHKImH73wLVbPRGxDlB0suBfiP7vaHcrld0EDXQOIggqWpBaUOrP4GklgqkqeVt6reRsRYUVApoNrq7xMVfpE4Y8rUK68OqdnzlxOWFcfo3gVrMlzhXNp9WeNhlrZlGTNy183w8mi4qMeG5GQc357MOsPSAPWvAA0Z83N2GUpPDeLfYRptASIK7I6xgrtg6kyLut7b5m5gKTef1IstA2CcSlkvSyZ8oLPEFR89oAqyzaDLmgRrdkGwteCiEC8im7eeWcEL2tPVnxHdCV7zK4TP3gGx4C3QFXBgbIM6tDtc2UpUleM3gXXP5wTlwCaTYGnsff0CE5fSFMPuNvpmyDXRGBo0bEsfgdTEnc2GFn5SddLLgGE2g8tgWi2SF4V59WlrQElWPzAGUMaRlGW".length());
        IntStream.range(0, 1000).parallel().forEach(i -> System.out.print(charset[FuzzUtil.randomIntFromRange(0, charset.length)]));
    }

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
