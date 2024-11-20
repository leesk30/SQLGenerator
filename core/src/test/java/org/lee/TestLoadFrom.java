package org.lee;

import org.json.JSONObject;
import org.lee.resource.MetaEntry;
import org.lee.context.SQLGeneratorContext;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;

public class TestLoadFrom {
    @Test
    public static void testMatch(){
        String pattern = "^(varchar|decimal|char)\\s*\\(\\s*\\d+\\s*\\)$";
        String[] strings = {"varchar(255)", "varchar (255 )", "varchar( 255)", "decimal(5)"};
        for (String string: strings){
            System.out.println(string);
            assert string.matches(pattern);
        }
//        System.out.println(string.matches(pattern));
//        for(String pattern: TypeDescriptor.patternArr){
//            System.out.println(pattern + " is match:" + "varchar(255)".matches(pattern));
//        }
    }

    @Test
    public static void testLoad() throws IOException {

        InputStream inputStream = TestLoadFrom.class.getClassLoader().getResourceAsStream("table_schema.json");
        String jsonString = Utils.is2String(inputStream);
        JSONObject jsonObject = new JSONObject(jsonString);
        MetaEntry metaEntry = new MetaEntry();
        metaEntry.init(jsonObject);
        System.out.println(MetaEntry.class);
    }

}
