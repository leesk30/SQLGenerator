package org.lee.common.config;

import org.lee.common.enumeration.Mode;
import org.lee.common.enumeration.SyntaxType;
import org.lee.common.exception.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public abstract class RuntimeConfigurationProvider {
    private final static Logger LOGGER = LoggerFactory.getLogger("ProviderCreator");

    public final static Properties DEFAULT_PROPERTIES = new Properties();

    public static RuntimeConfigurationProvider getDefaultProvider(){
        return new SparkGeneratorConfigurationProvider();
    }

    public static RuntimeConfigurationProvider getProvider(InternalConfig config){
        SyntaxType syntaxType = config.getSyntaxType();
        Properties properties = config.getSourceRuntimeConfig();
        RuntimeConfigurationProvider provider;
        switch (syntaxType){
            case spark:
            case rain:
                provider = new SparkGeneratorConfigurationProvider(properties);
                break;
            default:
                LOGGER.error(String.format("The syntax of the syntax type %s is not implement yet", syntaxType));
                throw new NotImplementedException("Not implements yet");
        }
        if(config.getGeneratePolicy() == Mode.diff){
            provider.ruleMapTemplate.put(Rule.REWRITER_REORDER, true);
            LOGGER.info("Using diff mode to generate sql.");
        }
        return provider;
    }

    protected final Properties source;
    protected final Map<Rule, Boolean> ruleMapTemplate = new HashMap<>();
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected RuntimeConfigurationProvider(){
        this.source = null;
        onCreateNewConfigurationProvider();
    }

    protected RuntimeConfigurationProvider(Properties properties){
        this.source = properties;
        onCreateNewConfigurationProvider();
    }


    public Map<Rule, Boolean> getTemplateRuleMap() {
        return ruleMapTemplate;
    }


    public Properties getTemplateConfig(){
        if(source == null){
            return new Properties();
        }
        return this.source;
    }

    public boolean confirm(Rule name){
        return getTemplateRuleMap().getOrDefault(name, name.getDefaultValue());
    }

    abstract public RuntimeConfiguration newRuntimeConfiguration();
    abstract protected void onCreateNewConfigurationProvider();
}