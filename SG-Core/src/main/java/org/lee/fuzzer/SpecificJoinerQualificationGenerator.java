package org.lee.fuzzer;

import org.lee.entry.FieldReference;
import org.lee.entry.RangeTableReference;
import org.lee.statement.expression.Qualification;
import org.lee.symbol.StaticSymbol;
import org.lee.util.FuzzUtil;

public class SpecificJoinerQualificationGenerator extends QualificationGenerator{

    private final RangeTableReference left;
    private final RangeTableReference right;
    public SpecificJoinerQualificationGenerator(RangeTableReference left, RangeTableReference right){
        this.left = left;
        this.right = right;
    }

    @Override
    public Qualification generate() {
        // todo:
        FieldReference leftReference = FuzzUtil.randomlyChooseFrom(left.getFieldReferences());
        FieldReference rightReference = FuzzUtil.randomlyChooseFrom(right.getFieldReferences());
        Qualification.Builder builder = new Qualification.Builder();
        builder.setCurrent(StaticSymbol.EQUAL_ASSIGN)
                .addChild(new Qualification.Builder().setCurrent(leftReference))
                .addChild(new Qualification.Builder().setCurrent(rightReference));
        index++;
        return builder.build();
    }
}
