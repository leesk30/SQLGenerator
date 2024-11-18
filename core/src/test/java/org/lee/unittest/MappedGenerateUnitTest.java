package org.lee.unittest;

import org.lee.sql.type.TypeTag;
import org.lee.sql.type.mapped.MappedType;
import org.testng.annotations.Test;

public class MappedGenerateUnitTest {

    @Test
    public <T> void testBasicTypeGeneration(){
        for(TypeTag type: TypeTag.values()){
            MappedType<T> mappedType = MappedType.get(type);
            System.out.printf("Current type: %s, MappedType: %s, generate value: %s %n", type, mappedType.getJavaClass().getName(), mappedType.generate().getLiteralString());
        }
        // check if singleton
        for(TypeTag type: TypeTag.values()){
            assert MappedType.get(type) == MappedType.get(type);
            assert MappedType.get(type).getJavaClass() == MappedType.get(type).getJavaClass();
        }

    }
}
