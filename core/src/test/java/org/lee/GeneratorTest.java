package org.lee;

import org.lee.common.config.InternalConfig;
import org.lee.common.config.InternalConfigs;
import org.lee.common.enumeration.Mode;
import org.lee.common.utils.NodeUtils;
import org.lee.common.utils.RandomUtils;
import org.lee.portal.worker.SQLGeneratorDefaultThreadWorker;
import org.lee.resource.MetaEntry;
import org.lee.context.SQLGeneratorContext;
import org.lee.sql.statement.SQLStatement;
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
        IntStream.range(0, 100).parallel().forEach(i -> System.out.print(charset[RandomUtils.randomIntFromRange(0, charset.length)]));
    }

    @Test
    public static void generateString(){
        final int maxLength = 2;
        String res = RandomUtils.randomStringByLength(maxLength);
        assert res.length() <= maxLength;
        System.out.println(res.length());
        System.out.println(res);
        System.out.println(NodeUtils.stringToLikePattern(res));
    }

    @Test
    public void generateDDLAndData() throws IOException {
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("tpcds.json");
        InternalConfig config = InternalConfigs.create(Mode.diff, stream);
        SQLGeneratorContext context = new SQLGeneratorContext(config);
        MetaEntry metaEntry = context.getMetaEntry();
        String results = metaEntry.toDDLs(true, "USING PARQUET");
//        try (final FileWriter fileWriter = new FileWriter(TestSingleSQLGenerator.outputInitializedPath())){
//            fileWriter.write(results);
//            fileWriter.write(metaEntry.toInitializedInserts());
//            fileWriter.flush();
//        }
        System.out.println(results);
        System.out.println(metaEntry.toInitializedInserts());
    }

    @Test
    public void generateTemplate() throws IOException {
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("tpcds.json");
        InternalConfig config = InternalConfigs.create(Mode.diff, stream);
        MetaEntry entry = new MetaEntry();
        entry.init(config.getMetaEntry());
        System.out.println(entry.toTemplateInserts());
    }

    @Test
    public void testGenerator() throws IOException {
        long startAt = System.currentTimeMillis();
        String output = TestSingleSQLGenerator.outputPath();
        System.out.println("Output dir is: " + output);
        final int numOfThread = 1;
        final ExecutorService service = Executors.newFixedThreadPool(numOfThread);
        final SQLGeneratorDefaultThreadWorker[] workers = new SQLGeneratorDefaultThreadWorker[numOfThread];
        try (final FileWriter writer = new FileWriter(output)){
            BlockingQueue<SQLStatement> queue = new ArrayBlockingQueue<>(1000);
            for(int i=0; i < numOfThread; i++){
                InputStream stream = this.getClass().getClassLoader().getResourceAsStream("tpcds.json");
                InternalConfig config = InternalConfigs.create(Mode.diff, stream);
                workers[i] = new SQLGeneratorDefaultThreadWorker(1000/numOfThread, config, queue);
                service.submit(workers[i]);
            }
            for (int i=0; i< 1000; i++){
//                System.out.printf("Take: %d ", i);
                SQLStatement statement = queue.take();
                String sql = statement.getFormattedString();
                if(sql == null){
                    System.out.println(i);
                }
                writer.write("----------->\n" + sql + "\n");
                writer.flush();
            }
            for (SQLGeneratorDefaultThreadWorker worker: workers){
                worker.terminate();
            }
            service.shutdown();
            service.awaitTermination(100, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("Generate elapse: %d ms%n", System.currentTimeMillis() - startAt);
    }

    @Test
    public void testGeneratorOnly() throws InterruptedException {
        final int numOfGenerate = 100000;
        final int numOfThread = 1;
        final ExecutorService service = Executors.newFixedThreadPool(numOfThread);
        final SQLGeneratorDefaultThreadWorker[] workers = new SQLGeneratorDefaultThreadWorker[numOfThread];
        final BlockingQueue<SQLStatement> queue = new ArrayBlockingQueue<>(1000);
        final InputStream stream = this.getClass().getClassLoader().getResourceAsStream("tpcds.json");
        final InternalConfig config = InternalConfigs.create(Mode.diff, stream);
        final long startAt = System.currentTimeMillis();

        for(int i=0; i < numOfThread; i++){
            workers[i] = new SQLGeneratorDefaultThreadWorker(numOfGenerate/numOfThread, config, queue);
            service.submit(workers[i]);
        }

        for (int i=0; i< numOfGenerate; i++){
//                System.out.printf("Take: %d ", i);
            SQLStatement statement = queue.take();
            String sql = statement.getFormattedString();
//            System.out.println(sql);
        }
        System.out.printf("Generate elapse: %d ms%n", System.currentTimeMillis() - startAt);
    }
}
