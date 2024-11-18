package org.lee.integration.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.util.SparkFileUtils$;
import org.lee.integration.client.Client;
import org.lee.integration.client.Execution;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class SparkClient implements Client<Dataset<Row>> {

    private final SparkSession spark;
    private final File path = SparkFileUtils$.MODULE$.createTempDir(System.getProperty("java.io.tmpdir"), "spark");
    public SparkClient(){
        SparkConf conf = new SparkConf();
        conf.setAppName("integration");
        conf.setMaster("local[4]");
        conf.set("spark.executor.cores", "4");
        conf.set("spark.executor.instances", "2");
        conf.set("spark.executor.memory", "1g");
        conf.set("spark.driver.memory", "1g");
        conf.set("spark.sql.warehouse.dir", path.toString());
        spark = SparkSession.builder().config(conf).getOrCreate();
    }

    private void prepareWarehouseDir(){
        Path dirPath = Paths.get(path.toString(), "spark-warehouse");
        // 删除目录及其内容
        try {
            deleteDirectoryRecursively(dirPath);
            System.out.println("Directory deleted successfully for: " + dirPath);
        } catch (IOException e) {
            System.err.println("Failed to delete directory: " + e.getMessage());
        }
    }

    private void deleteDirectoryRecursively(Path path) throws IOException {
        if (Files.exists(path)) {
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        }
    }

    private class SparkExecution implements Execution<Dataset<Row>> {
        private final String code;
        private Dataset<Row> res;
        private Throwable error;
        private boolean isSuccess;
        private SparkExecution(String code){
            this.code = code;
        }

        @Override
        public Dataset<Row> get() {
            return res;
        }

        @Override
        public boolean isSuccess() {
            return isSuccess;
        }

        @Override
        public String getCode() {
            return code;
        }

        @Override
        public void execute() {
            try {
                res = spark.sql(code);
                res.collect();
                isSuccess = true;
            }catch (Throwable t){
                error = t;
                isSuccess = false;
            }
        }

        @Override
        public Throwable throwable() {
            return error;
        }
    }

    @Override
    public Execution<Dataset<Row>> createExecution(String code) {
        return new SparkExecution(code);
    }
}
