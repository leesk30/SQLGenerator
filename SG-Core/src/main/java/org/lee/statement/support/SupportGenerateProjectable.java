package org.lee.statement.support;

import org.lee.fuzzer.Generator;
import org.lee.type.TypeTag;

import java.util.List;

public interface SupportGenerateProjectable extends Generator<Projectable> {

    @Override
    default Projectable generate(){
        Projectable projectable = Projectable.newRandomlyProjectable();
        projectable.fuzz();
        return projectable;
    }

    default Projectable generate(List<TypeTag> limitations){
        Projectable projectable = Projectable.newRandomlyProjectable();
        projectable.withProjectTypeLimitation(limitations);
        projectable.fuzz();
        return projectable;
    }
}
