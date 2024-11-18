package org.lee.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NamedLoggers {

    public enum Module{
        core,
        server,
        common,
    }

    public static Logger getCoreLogger(Class<?> cls){
        return getPrettyNamedLogger(Module.core, cls);
    }

    public static Logger getCommonLogger(Class<?> cls){
        return getPrettyNamedLogger(Module.common, cls);
    }

    public static Logger getPrettyNamedLogger(Module module, Class<?> cls){
        return LoggerFactory.getLogger(module + ":" + getPrettyClassFileName(cls));
    }

    private static String getPrettyClassFileName(Class<?> cls){
        String clsName = cls.getName();
        if(clsName.contains(".")){
            String[] arr = clsName.split("\\.");
            return arr[arr.length-1];
        }
        return clsName;
    }

    public static void main(String[] args) {
        Logger logger = getPrettyNamedLogger(Module.core, NamedLoggers.class);
        logger.error("TEST !!!!!!!");
    }
}
