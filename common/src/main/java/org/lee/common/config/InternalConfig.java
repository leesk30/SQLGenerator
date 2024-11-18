package org.lee.common.config;

import org.json.JSONObject;
import org.lee.common.NamedLoggers;
import org.lee.common.enumeration.Mode;
import org.lee.common.enumeration.SyntaxType;
import org.slf4j.Logger;

import java.util.Properties;

public interface InternalConfig{
    Logger LOGGER = NamedLoggers.getCoreLogger(InternalConfig.class);
    SyntaxType getSyntaxType();
    Properties getSourceRuntimeConfig();
    Mode getGeneratePolicy();
    JSONObject getMetaEntry();
    JSONObject getSymbolTable();
    InternalConfig shallowCopy();
    InternalConfig deepCopy();

    default void display(){
        LOGGER.debug("InternalConfig-SyntaxType: " + getSyntaxType());
        LOGGER.debug("InternalConfig-Mode: " + getGeneratePolicy());
        LOGGER.debug("InternalConfig-MetaEntry: total " + getMetaEntry().length());
        LOGGER.debug("InternalConfig-SymbolTable: total " + getSymbolTable().length());
    }
}
