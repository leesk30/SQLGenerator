package org.lee.sql.support;

import org.lee.common.Assertion;
import org.lee.sql.entry.complex.TargetEntry;
import org.lee.sql.entry.complex.AdaptiveRecordScalar;
import org.lee.sql.entry.relation.RangeTableEntry;
import org.lee.sql.entry.scalar.Scalar;
import org.lee.sql.entry.scalar.ScalarSubquery;
import org.lee.sql.statement.SQLStatement;
import org.lee.sql.type.TypeTag;

import java.util.List;

public interface Projectable extends SQLStatement {
    List<TargetEntry> project();
    RangeTableEntry toRelation();

    default Scalar toScalar(){
        Assertion.requiredTrue(isScalar());
        return new ScalarSubquery(this);
    }

    default AdaptiveRecordScalar toAdaptiveRecordScalar(){
        return AdaptiveRecordScalar.adaptProjectable(this);
    }

    boolean isWithLogicalParentheses();

    @Override
    default String getString(){
        if(this.isWithLogicalParentheses()){
            return LP + body() + RP;
        }
        if(this.isFinished()){
            return body() + ENDING;
        }
        return body();
    }

    boolean isScalar();

    default int width(){
        return project().size();
    }

    default String body(){
        return nodeArrayToString(SPACE, this.walk());
    }

    void withProjectTypeLimitation(List<TypeTag> limitation);
    List<TypeTag> getProjectTypeLimitation();
}
