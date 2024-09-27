package org.lee.statement.support;

import org.lee.base.Generator;
import org.lee.statement.SQLStatement;
import org.lee.type.TypeTag;

import java.util.List;

public interface SupportGenerateProjectable extends Generator<Projectable> {

    @Override
    default Projectable generate(){
        if(this instanceof SQLStatement){
            return generate((SQLStatement) this);
        }
        throw new UnsupportedOperationException("The default method of SupportGenerateProjectable only support in SQLStatement");
    }


    default Projectable generate(SQLStatement parent){
        Projectable projectable = Projectable.newRandomlyProjectable(parent);
        projectable.fuzz();
        return projectable;
    }

    default Projectable generate(SQLStatement parent, List<TypeTag> limitations){
        Projectable projectable = Projectable.newRandomlyProjectable(parent);
        projectable.withProjectTypeLimitation(limitations);
        projectable.fuzz();
        return projectable;
    }
}
