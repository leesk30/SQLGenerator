package org.lee.generator.common;

import org.lee.generator.expression.common.ExpressionLocation;
import org.lee.sql.symbol.Comparator;

public class ComparatorAccessor extends WeightedSymbolAccessor<Comparator> {

    private final ExpressionLocation location;

    public ComparatorAccessor(ExpressionLocation location) {
        super(Comparator.DEFAULT_WEIGHT);
        this.location = location;
    }

    @Override
    public void add(Comparator weighted) {
        add(weighted, weighted.getWeight(location));
    }
}
