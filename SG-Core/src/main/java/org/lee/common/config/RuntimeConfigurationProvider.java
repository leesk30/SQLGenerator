package org.lee.common.config;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.lee.common.SyntaxType;
import org.lee.common.exception.NotImplementedException;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public abstract class RuntimeConfigurationProvider {

    public final static Configurations configurations = new Configurations();
    private final static Configuration DEFAULT = getDefaultConfigurations();

    public static RuntimeConfigurationProvider getDefaultProvider(){
        return new SparkGeneratorConfigurationProvider();
    }

    public static RuntimeConfigurationProvider getProvider(File sourceFile){
        try {
            Configuration configuration = configurations.properties(sourceFile);
            if(!configuration.containsKey(Conf.SYNTAX_TYPE.toString())){
                System.out.println("The syntax of the configuration doesn't exist. Using default.");
                return new  SparkGeneratorConfigurationProvider();
            }

            SyntaxType syntaxType = configuration.getEnum(Conf.SYNTAX_TYPE.toString(), SyntaxType.class);
            switch (syntaxType){
                case spark:
                case rain:
                    return new SparkGeneratorConfigurationProvider();
                default:
                    throw new NotImplementedException("Not implements yet");
            }
        }catch (ConfigurationException e){
            e.printStackTrace();
            throw new RuntimeException("Cannot find configuration resource");
        }
    }

    public static RuntimeConfigurationProvider getProvider(String sourceFilePath){
        File sourceFile = new File(sourceFilePath);
        if(!sourceFile.exists()){
            throw new RuntimeException("Cannot find configuration file. The path is not exists: " + sourceFilePath);
        }
        return getProvider(new File(sourceFilePath));
    }


    protected final File sourceFile;
    protected final Map<Rule, Boolean> ruleMapTemplate = new HashMap<>();

    protected RuntimeConfigurationProvider(){
        this.sourceFile = null;
        ruleConstructor();
    }

    protected RuntimeConfigurationProvider(File sourceFile){
        this.sourceFile = sourceFile;
        ruleConstructor();
    }

    public Map<Rule, Boolean> getTemplateRuleMap() {
        return ruleMapTemplate;
    }

    private static Configuration getDefaultConfigurations(){
        try {
            return configurations.properties(RuntimeConfigurationProvider.class.getClassLoader().getResource("DefaultConfiguration.properties"));
        }catch (ConfigurationException e){
            e.printStackTrace();
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
            e.printStackTrace();
            throw new RuntimeException("Cannot find configuration resource");
        }
    }

    public Configuration getDefaultConfig(){
        return DEFAULT;
    }

    abstract public RuntimeConfiguration newRuntimeConfiguration();
    abstract protected void ruleConstructor();

}
