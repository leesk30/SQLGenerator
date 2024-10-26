package org.lee.statement.expression.abs;

import org.lee.common.Utility;
import org.lee.entry.scalar.Scalar;
import org.lee.symbol.Signature;
import org.lee.type.TypeCategory;
import org.lee.type.TypeTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public interface GeneratorStatistic {
    Logger LOGGER = LoggerFactory.getLogger("GeneratorStatistic");
    List<Scalar> getWholeScopeCandidates();
    boolean contains(TypeTag typeTag);
    List<Scalar> findMatch(TypeTag typeTag);
    List<Scalar> findMatch(TypeCategory typeTag);
    Scalar findAny();

    default Scalar findAny(TypeTag typeTag){
        return Utility.randomlyChooseFrom(findMatch(typeTag));
    }
}
