package org.lee.statement.expression.abs;

import org.lee.common.Utility;
import org.lee.entry.scalar.Scalar;
import org.lee.type.TypeCategory;
import org.lee.type.TypeTag;

import java.util.List;

public interface GeneratorStatistic {

    List<Scalar> getWholeScopeCandidates();
    boolean contains(TypeTag typeTag);
    List<Scalar> findMatch(TypeTag typeTag);
    List<Scalar> findMatch(TypeCategory typeTag);
    Scalar findAny();

    default Scalar findAny(TypeTag typeTag){
        return Utility.randomlyChooseFrom(findMatch(typeTag));
    }
}
