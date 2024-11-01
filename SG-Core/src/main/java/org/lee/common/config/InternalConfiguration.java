package org.lee.common.config;

import org.lee.common.Mode;
import org.lee.common.SyntaxType;
import org.lee.common.global.MetaEntry;
import org.lee.common.global.SymbolTable;

import java.util.Properties;

public final class InternalConfiguration {
    public final static Version version = Version.instance;

//    private static class SystemEnvPropertyInternalConfig implements InternalConfig{
//
//    }
    private static abstract class InternalConfigStruct {
        protected SyntaxType syntaxType;
        protected RuntimeConfigurationProvider provider;
        protected Mode mode;
        protected MetaEntry entries;
        protected SymbolTable symbols;
        protected Properties source;

        private InternalConfigStruct(){}

        private InternalConfigStruct(
                SyntaxType syntaxType,
                RuntimeConfigurationProvider provider,
                Mode mode,
                MetaEntry metaEntry,
                SymbolTable symbolTable,
                Properties source
        ){
            this.syntaxType = syntaxType;
            this.provider = provider;
            this.mode = mode;
            this.entries = metaEntry;
            this.symbols = symbolTable;
            this.source = source;
        }

        public SyntaxType getSyntaxType() {
            return syntaxType;
        }

        public RuntimeConfigurationProvider getProvider() {
            return provider;
        }

        public Mode getGeneratePolicy() {
            return mode;
        }

        public MetaEntry getMetaEntry() {
            return entries;
        }

        public SymbolTable getSymbolTable() {
            return symbols;
        }

        public Properties getSourceRuntimeConfig() {
            return null;
        }
    }

    private static class DefaultPartialInternalConfig extends InternalConfigStruct implements InternalConfig{

        @Override
        public InternalConfig shallowCopy() {
            return null;
        }

        @Override
        public InternalConfig deepCopy() {
            return null;
        }
    }

    private static class SystemPropertyInternalConfig extends InternalConfigStruct implements InternalConfig{
        public SystemPropertyInternalConfig(){
            super();
            syntaxType = SyntaxType.fromString(System.getProperty("syntax", "spark"));
            provider = RuntimeConfigurationProvider.getProvider(this);
            String filePathOfEntries = System.getProperty("entries", "");
            entries = new MetaEntry();
            symbols = new SymbolTable();
            mode = Mode.fromString(System.getProperty("mode", "spark"));
            source = new Properties();
        }

        private SystemPropertyInternalConfig(
                SyntaxType syntaxType,
                RuntimeConfigurationProvider provider,
                Mode mode,
                MetaEntry metaEntry,
                SymbolTable symbolTable,
                Properties source
        ){
            super(syntaxType, provider, mode, metaEntry, symbolTable, source);
        }


        @Override
        public InternalConfig shallowCopy() {
            return new SystemPropertyInternalConfig(syntaxType, provider, mode, entries, symbols, source);
        }

        @Override
        public InternalConfig deepCopy() {
            return shallowCopy();
        }
    }
}
