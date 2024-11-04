package org.lee;

import org.lee.common.config.InternalConfig;
import org.lee.common.config.InternalConfigs;
import org.lee.common.global.MetaEntry;
import org.lee.common.Utility;
import org.lee.portal.worker.SQLGeneratorWorker;
import org.lee.portal.SQLGeneratorContext;
import org.lee.statement.SQLFormatter;
import org.lee.statement.support.SQLStatement;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.*;
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

    @Ignore
    public static void generateDDLAndData() throws IOException {
        TestSingleSQLGenerator.load();
        MetaEntry metaEntry = SQLGeneratorContext.getCurrentMetaEntry();
        String results = metaEntry.toDDLs(true, "USING PARQUET");
        try (final FileWriter fileWriter = new FileWriter(TestSingleSQLGenerator.outputInitializedPath())){
            fileWriter.write(results);
            fileWriter.write(metaEntry.toInitializedInserts());
            fileWriter.flush();
        }
    }

    @Test
    public void testGenerator() throws IOException {
        long startAt = System.currentTimeMillis();
        String output = TestSingleSQLGenerator.outputPath();
        System.out.println("Output dir is: " + output);
        final SQLFormatter formatter = new SQLFormatter();
        final int numOfThread = 4;
        final ExecutorService service = Executors.newFixedThreadPool(numOfThread);
        final SQLGeneratorWorker[] workers = new SQLGeneratorWorker[numOfThread];
        try (final FileWriter writer = new FileWriter(output)){
            BlockingQueue<SQLStatement> queue = new ArrayBlockingQueue<>(1000);
            for(int i=0; i < numOfThread; i++){
                InputStream stream = this.getClass().getClassLoader().getResourceAsStream("tpcds.json");
                InternalConfig config = InternalConfigs.create(stream);
                workers[i] = new SQLGeneratorWorker(1000/numOfThread, config, queue);
                service.submit(workers[i]);
            }
            for (int i=0; i< 1000; i++){
//                System.out.printf("Take: %d ", i);
                SQLStatement statement = queue.take();
                String sql = statement.getString();
                if(sql == null){
                    System.out.println(i);
                }
                writer.write("----------->\n" + formatter.format(sql) + "\n");
                writer.flush();
            }
            for (SQLGeneratorWorker worker: workers){
                worker.stopIt();
            }
            service.shutdown();
            service.awaitTermination(100, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("Generate elapse: %d ms%n", System.currentTimeMillis() - startAt);
    }
}
