package org.lee.generator.common;

import org.lee.common.generator.WeightedAccessor;
import org.lee.sql.symbol.Symbol;

public class WeightedSymbolAccessor<T extends Symbol> extends WeightedAccessor<T, T> {

    public WeightedSymbolAccessor(int defaultWeight) {
        super(defaultWeight);
    }

    @Override
    protected T onHookedGenerate() {
        return get();
    }
}
