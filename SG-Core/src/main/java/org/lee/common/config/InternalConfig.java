package org.lee.common.config;

import org.lee.common.Mode;
import org.lee.common.SyntaxType;
import org.lee.common.global.MetaEntry;
import org.lee.common.global.SymbolTable;

import java.util.Properties;

public interface InternalConfig{
    Version version = Version.instance;
    SyntaxType getSyntaxType();
    RuntimeConfigurationProvider getProvider();
    Properties getSourceRuntimeConfig();
    Mode getGeneratePolicy();
    MetaEntry getMetaEntry();
    SymbolTable getSymbolTable();
    InternalConfig shallowCopy();
    InternalConfig deepCopy();
    static InternalConfig create(){
        return null;
    }
}
