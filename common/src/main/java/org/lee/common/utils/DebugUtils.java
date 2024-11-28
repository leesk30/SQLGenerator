package org.lee.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.lee.common.Assertion;
import org.lee.common.NamedLoggers;
import org.slf4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;
import java.util.UUID;

public class DebugUtils {
    private final static Logger LOGGER = NamedLoggers.getCommonLogger(DebugUtils.class);

    public static String getLocalFrameInfo(){
        return getLocalFrameInfo(4);
    }

    public static String getLocalFrameInfo(final int truncateSize){
        StackTraceElement[] localStackElement = Thread.getAllStackTraces().get(Thread.currentThread());
        Assertion.requiredTrue(localStackElement.length >= truncateSize);
        StackTraceElement[] removedNonSenseStackInfo = Arrays.copyOfRange(localStackElement, truncateSize, localStackElement.length);
        return StringUtils.joinWith("\n", (Object[]) removedNonSenseStackInfo);
    }

    public static void recordLocalFrameInfo4DebugInLog(Logger logger){
        if(logger == null){
            logger = LOGGER;
        }
        logger.debug(getLocalFrameInfo(4));
    }

    public static String truncate(UUID u){
        String s = u.toString().replaceAll("-", "");
        if(s.length() > 8){
            return s.substring(0, 8);
        }else {
            return s;
        }
    }

    public static final boolean ENABLE_DEV_DEBUG_LOGGER;

    static {
        InputStream stream = DebugUtils.class.getClassLoader().getResourceAsStream("debug.properties");
        Properties properties = new Properties();
        try {
            properties.load(stream);
        }catch (IOException ignored){}
        ENABLE_DEV_DEBUG_LOGGER = Boolean.parseBoolean(properties.getProperty("ENABLE_DEV_DEBUG_LOGGER", "false"));
    }
}
