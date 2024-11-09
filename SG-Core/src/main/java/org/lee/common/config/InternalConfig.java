package org.lee.common.config;

import org.json.JSONObject;
import org.lee.common.Mode;
import org.lee.common.SyntaxType;
import org.lee.portal.SQLGeneratorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public interface InternalConfig{
    Version version = Version.instance;
    Logger LOGGER = LoggerFactory.getLogger("InternalConfig");
    SyntaxType getSyntaxType();
    Properties getSourceRuntimeConfig();
    Mode getGeneratePolicy();
    JSONObject getMetaEntry();
    JSONObject getSymbolTable();
    InternalConfig shallowCopy();
    InternalConfig deepCopy();

    default SQLGeneratorContext reflect(){
        return SQLGeneratorContext.getOrCreate(this);
    }

    default void display(){
        LOGGER.debug("InternalConfig-SyntaxType: " + getSyntaxType());
        LOGGER.debug("InternalConfig-Mode: " + getGeneratePolicy());
        LOGGER.debug("InternalConfig-MetaEntry: total " + getMetaEntry().length());
        LOGGER.debug("InternalConfig-SymbolTable: total " + getSymbolTable().length());
    }
}
