package org.lee.statement.support;

import org.lee.common.Assertion;
import org.lee.entry.complex.TargetEntry;
import org.lee.entry.record.AdaptiveRecordScalar;
import org.lee.entry.relation.RangeTableEntry;
import org.lee.entry.scalar.Scalar;
import org.lee.entry.scalar.ScalarSubquery;
import org.lee.type.TypeTag;

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
