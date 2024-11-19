package org.lee.common.utils;

import org.lee.common.NamedLoggers;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class IOUtils {

    private final static Logger LOGGER = NamedLoggers.getCommonLogger(IOUtils.class);

    public static String inputStreamToString(InputStream input){
        StringBuilder builder = new StringBuilder();
        String line;
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8))){
            while ((line = reader.readLine()) != null){
                builder.append(line).append("\n");
            }
        }catch (IOException e){
            LOGGER.error("An IOException occurred during loading input stream.", e);
            throw new RuntimeException(e);
        }
        return builder.toString();
    }
}
