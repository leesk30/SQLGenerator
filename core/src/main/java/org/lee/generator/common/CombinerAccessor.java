package org.lee.generator.common;

import org.lee.common.generator.Generator;
import org.lee.common.generator.WeightedAccessor;
import org.lee.sql.expression.Expression;

public class CombinerAccessor<T extends Expression> extends WeightedAccessor<Generator<T>, T> {

    public CombinerAccessor(int defaultWeight) {
        super(defaultWeight);
    }

    @Override
    protected T onHookedGenerate() {
        return get().generate();
    }
}
