package org.lee.common.config;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.lee.common.NamedLoggers;
import org.lee.common.constant.SystemConst;
import org.lee.common.enumeration.Mode;
import org.lee.common.enumeration.SyntaxType;
import org.lee.common.exception.InternalError;
import org.lee.common.utils.IOUtils;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Properties;

public final class InternalConfigs {
    public final static String DEFAULT_SYMBOL_RESOURCE = "symbol.json";
    public final static String ENTRIES_KEY = "entries";
    public final static String SYMBOLS_KEY = "symbols";
    public final static String SYNTAX_KEY = "syntax";
    public final static String MODE_KEY = "mode";
    public final static String CONFIG_SOURCE_KEY = "config";
    public final static String NUM = "num";
    public final static String WORKERS = "workers";
    public final static Logger LOGGER = NamedLoggers.getCommonLogger(InternalConfigs.class);

    public final static InternalConfig EMPTY = new DefaultPartialInternalConfig(new JSONObject());

    public static CommandLineOptions readCommandLineOptions(){
        return CommandLineOptions.getInstance();
    }

    public static InternalConfig create(final String inputEntriesPath){
        return new DefaultPartialInternalConfig(inputEntriesPath);
    }

    public static InternalConfig create(final Mode mode, final String inputEntriesPath){
        return new DefaultPartialInternalConfig(mode, inputEntriesPath);
    }

    public static InternalConfig create(final String mode, final String inputEntriesPath){
        Mode modeEnum = Mode.fromString(mode);
        return new DefaultPartialInternalConfig(modeEnum, inputEntriesPath);
    }

    public static InternalConfig create(final JSONObject inputEntries){
        return new DefaultPartialInternalConfig(inputEntries);
    }

    public static InternalConfig create(final InputStream inputEntries){
        return new DefaultPartialInternalConfig(inputEntries);
    }

    public static InternalConfig create(final Mode mode, final InputStream inputEntries){
        return new DefaultPartialInternalConfig(mode, inputEntries);
    }

    public static InternalConfig create(final String mode, final InputStream inputEntries){
        Mode modeEnum = Mode.fromString(mode);
        return new DefaultPartialInternalConfig(modeEnum, inputEntries);
    }

    public static void modifyLoggingConfig(final String xmlFilePath){
        System.setProperty("log4j.configuration", xmlFilePath);
        // org.apache.logging.log4j.LogManager.getContext();
        // don't close it
        org.apache.logging.log4j.core.config.Configurator.initialize(null, xmlFilePath);
        LOGGER.info("Modify logging config with: " + xmlFilePath);
    }

    public static String getLoggingConfigSourceTemplateString(){
        return IOUtils.inputStreamToString(InternalConfigs.class.getClassLoader().getResourceAsStream("log4j2.xml"));
    }

    public static InputStream getLoggingConfigSourceTemplate(){
        return InternalConfigs.class.getClassLoader().getResourceAsStream("log4j2.xml");
    }

    private static abstract class AbstractInternalConfig implements InternalConfig {

        protected SyntaxType syntaxType;
        protected Mode mode;
        protected JSONObject entries;
        protected JSONObject symbols;
        protected Properties source;

        private AbstractInternalConfig(){
        }

        private AbstractInternalConfig(
                SyntaxType syntaxType,
                Mode mode,
                JSONObject metaEntry,
                JSONObject symbolTable,
                Properties source
        ){
            this.syntaxType = syntaxType;
            this.mode = mode;
            this.entries = metaEntry;
            this.symbols = symbolTable;
            this.source = source;
        }

        public SyntaxType getSyntaxType() {
            return syntaxType;
        }

        public Mode getGeneratePolicy() {
            return mode;
        }

        public JSONObject getMetaEntry() {
            return entries;
        }

        public JSONObject getSymbolTable() {
            return symbols;
        }

        public Properties getSourceRuntimeConfig() {
            return source;
        }

        protected static File getAndRequiredFileExists(String filePath){
            if(filePath == null){
                throw new InternalError("The input source file path cannot be null");
            } else if (filePath.trim().isEmpty()) {
                throw new InternalError("The input source file path cannot be empty");
            }
            File file = new File(filePath);
            if(!file.exists()){
                throw new InternalError("The input source file path doesn't exist.");
            } else if (file.isDirectory()) {
                throw new InternalError("The input source file path is a directory.");
            }
            return file;
        }

        protected static JSONObject getJSONFromFileSource(String filePath){
            File file = getAndRequiredFileExists(filePath);
            try(InputStream stream = Files.newInputStream(file.toPath())) {
                return new JSONObject(IOUtils.inputStreamToString(stream));
            }catch (IOException e){
                throw new InternalError(e);
            }
        }

        protected static Properties getPropertiesFromFileSource(String filePath){
            File file = getAndRequiredFileExists(filePath);
            try(InputStream stream = Files.newInputStream(file.toPath())) {
                Properties newProperties = new Properties();
                newProperties.load(stream);
                return newProperties;
            }catch (IOException e){
                throw new InternalError(e);
            }
        }

        protected static InputStream getResourceAsStream(String resourceName){
            return InternalConfig.class.getClassLoader().getResourceAsStream(resourceName);
        }

        protected static JSONObject getDefaultSymbols(){
            try(InputStream stream = getResourceAsStream(DEFAULT_SYMBOL_RESOURCE)) {
                return new JSONObject(IOUtils.inputStreamToString(stream));
            } catch (IOException e) {
                throw new InternalError(e);
            }
        }

        public InternalConfig shallowCopy() {
            return new FrozenInternalConfig(syntaxType, mode, entries, symbols, source);
        }

        public InternalConfig deepCopy() {
            Properties newSource = new Properties(source);
            JSONObject newEntries = new JSONObject(entries.toString(4));
            JSONObject newSymbols = new JSONObject(symbols.toString(4));
            return new FrozenInternalConfig(syntaxType, mode, newEntries, newSymbols, newSource);
        }

    }

    private static class FrozenInternalConfig extends AbstractInternalConfig {
        private FrozenInternalConfig(
                SyntaxType syntaxType,
                Mode mode,
                JSONObject metaEntry,
                JSONObject symbolTable,
                Properties source
        ){
            super(syntaxType, mode, metaEntry, symbolTable, source);
        }
    }

    private static class DefaultPartialInternalConfig extends AbstractInternalConfig {
        private DefaultPartialInternalConfig(String entriesPath){
            super(SyntaxType.spark, Mode.normal, getJSONFromFileSource(entriesPath), getDefaultSymbols(), new Properties());
        }

        private DefaultPartialInternalConfig(JSONObject entries){
            super(SyntaxType.spark, Mode.normal, entries, getDefaultSymbols(), new Properties());
        }

        private DefaultPartialInternalConfig(InputStream entries){
            super(SyntaxType.spark, Mode.normal, new JSONObject(IOUtils.inputStreamToString(entries)), getDefaultSymbols(), new Properties());
        }

        private DefaultPartialInternalConfig(Mode mode, String entriesPath){
            super(SyntaxType.spark, mode, getJSONFromFileSource(entriesPath), getDefaultSymbols(), new Properties());
        }

        private DefaultPartialInternalConfig(Mode mode, InputStream entries){
            super(SyntaxType.spark, mode, new JSONObject(IOUtils.inputStreamToString(entries)), getDefaultSymbols(), new Properties());
        }

        private DefaultPartialInternalConfig(SyntaxType syntaxType, String entriesPath){
            super(syntaxType, Mode.normal, getJSONFromFileSource(entriesPath), getDefaultSymbols(), new Properties());
        }

        private DefaultPartialInternalConfig(SyntaxType syntaxType, Mode mode, String entriesPath){
            super(syntaxType, mode, getJSONFromFileSource(entriesPath), getDefaultSymbols(), new Properties());
        }

        private DefaultPartialInternalConfig(SyntaxType syntaxType, Mode mode, String entriesPath, String symbolsPath){
            super(syntaxType, mode, getJSONFromFileSource(entriesPath), getJSONFromFileSource(symbolsPath), new Properties());
        }

        private DefaultPartialInternalConfig(SyntaxType syntaxType, Mode mode, String entriesPath, String symbolsPath, String configSourcePath){
            super(syntaxType, mode, getJSONFromFileSource(entriesPath), getJSONFromFileSource(symbolsPath), getPropertiesFromFileSource(configSourcePath));
        }

    }

    public static class CommandLineOptions extends AbstractInternalConfig {

        private final static CommandLineOptions singleton = new CommandLineOptions();;

        public static CommandLineOptions getInstance(){
            if(!singleton.isInitialized){
                singleton.init();
            }
            return singleton;
        }

        private boolean isInitialized;
        private int generateNum;
        private String outputFilePath;
        private String log4jConfigPath;
        private int workerNum;

        private CommandLineOptions(){
            super();
            this.isInitialized = false;
        }

        private synchronized void init() {
            if(isInitialized){
                return;
            }
            try {// entries
                String filePathOfEntries = System.getProperty(ENTRIES_KEY, "");
                entries = getJSONFromFileSource(filePathOfEntries);

                // mode
                mode = Mode.fromString(System.getProperty(MODE_KEY, "normal"));

                // symbols
                String filePathOfSymbols = System.getProperty(SYMBOLS_KEY, "");
                if (StringUtils.isEmpty(filePathOfSymbols) || StringUtils.isBlank(filePathOfSymbols)) {
                    symbols = getDefaultSymbols();
                } else {
                    symbols = getJSONFromFileSource(filePathOfSymbols);
                }

                // syntax type
                syntaxType = SyntaxType.fromString(System.getProperty(SYNTAX_KEY, "spark"));

                // config source
                String filePathOfConfig = System.getProperty(CONFIG_SOURCE_KEY, "");
                if (StringUtils.isEmpty(filePathOfConfig) || StringUtils.isBlank(filePathOfConfig)) {
                    source = new Properties();
                } else {
                    source = getPropertiesFromFileSource(filePathOfConfig);
                }

                generateNum = Integer.parseInt(System.getProperty(NUM, "1000"));
                workerNum = Math.min(Integer.parseInt(System.getProperty(WORKERS, "4")), SystemConst.MAX_WORKERS);

                log4jConfigPath = System.getProperty("log4j", "");

            }catch (InternalError e){
                isInitialized = false;
                throw e;
            }
            isInitialized = true;
        }

        public int getGenerateNum() {
            return generateNum;
        }

        public int getWorkerNum() {
            return workerNum;
        }

        public String getOutputFilePath() {
            return outputFilePath;
        }

        public String getLog4jConfigPath() {
            return log4jConfigPath;
        }
    }

    private static class SystemEnvironmentInternalConfig extends AbstractInternalConfig {
        public SystemEnvironmentInternalConfig(){
            super();
            // todo
        }
    }

}
