package org.lee.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtil {
    public static String inputStreamToString(InputStream input){
        StringBuilder builder = new StringBuilder();
        String line;
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(input))){
            while ((line = reader.readLine()) != null){
                builder.append(line).append("\n");
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        return builder.toString();
    }
}
