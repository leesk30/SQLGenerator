package org.lee.testutils;

import org.lee.common.config.InternalConfig;
import org.lee.common.config.InternalConfigs;
import org.lee.common.enumeration.Mode;
import org.lee.context.SQLGeneratorContext;

import java.io.InputStream;

public class Simple {

    public static SQLGeneratorContext getSQLGeneratorContext(String testResource){
        InputStream stream = Simple.class.getClassLoader().getResourceAsStream(testResource);
        InternalConfig config = InternalConfigs.create(Mode.diff, stream);
        return new SQLGeneratorContext(config);
    }
}
