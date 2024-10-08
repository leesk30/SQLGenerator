package org.lee.common.config;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.lee.common.SyntaxType;
import org.lee.common.exception.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public abstract class RuntimeConfigurationProvider {
    private final static Logger LOGGER = LoggerFactory.getLogger("ProviderCreator");

    public final static Configurations configurations = new Configurations();
    private final static Configuration DEFAULT = getDefaultConfigurations();

    public static RuntimeConfigurationProvider getDefaultProvider(){
        return new SparkGeneratorConfigurationProvider();
    }

    public static RuntimeConfigurationProvider getProvider(File sourceFile){
        try {
            Configuration configuration = configurations.properties(sourceFile);
            if(!configuration.containsKey(Conf.SYNTAX_TYPE.toString())){
                LOGGER.warn("The syntax of the configuration doesn't exist. Using default.");
                return new SparkGeneratorConfigurationProvider();
            }

            SyntaxType syntaxType = configuration.getEnum(Conf.SYNTAX_TYPE.toString(), SyntaxType.class);
            switch (syntaxType){
                case spark:
                case rain:
                    return new SparkGeneratorConfigurationProvider();
                default:
                    LOGGER.error(String.format("The syntax of the syntax type %s is not implement yet", syntaxType));
                    throw new NotImplementedException("Not implements yet");
            }
        }catch (ConfigurationException e){
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException("Cannot find configuration resource");
        }
    }

    public static RuntimeConfigurationProvider getProvider(String sourceFilePath){
        File sourceFile = new File(sourceFilePath);
        if(!sourceFile.exists()){
            LOGGER.error("Cannot find configuration file. The path is not exists: " + sourceFilePath);
            throw new RuntimeException("Cannot find configuration file. The path is not exists: " + sourceFilePath);
        }
        return getProvider(new File(sourceFilePath));
    }


    protected final File sourceFile;
    protected final Map<Rule, Boolean> ruleMapTemplate = new HashMap<>();
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected RuntimeConfigurationProvider(){
        this.sourceFile = null;
        onCreateNewConfigurationProvider();
    }

    protected RuntimeConfigurationProvider(File sourceFile){
        this.sourceFile = sourceFile;
        onCreateNewConfigurationProvider();
    }

    public Map<Rule, Boolean> getTemplateRuleMap() {
        return ruleMapTemplate;
    }

    private static Configuration getDefaultConfigurations(){
        try {
            return configurations.properties(RuntimeConfigurationProvider.class.getClassLoader().getResource("DefaultConfiguration.properties"));
        }catch (ConfigurationException e){
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException("Cannot find configuration resource");
        }
    }

    public Configuration getTemplateConfig(){
        try {
            if(sourceFile == null){
                return DEFAULT;
            }
            return configurations.properties(sourceFile);
        }catch (ConfigurationException e){
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException("Cannot find configuration resource");
        }
    }

    public Configuration getDefaultConfig(){
        return DEFAULT;
    }

    abstract public RuntimeConfiguration newRuntimeConfiguration();
    abstract protected void onCreateNewConfigurationProvider();

}
