package org.lee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Utils {
    public static String is2String(InputStream is){
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        try {
            while ((line = reader.readLine()) != null){
                builder.append(line).append("\n");
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        return builder.toString();
    }
}
