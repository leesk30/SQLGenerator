package org.lee.rules;

import java.util.function.Supplier;

public class DynamicRule implements Rule{
    private final RuleName name;
    private final Supplier<Boolean> dynamicState;

    public DynamicRule(RuleName name, Supplier<Boolean> dynamicState){
        this.name = name;
        this.dynamicState = dynamicState;
    }

    @Override
    public boolean get() {
        return dynamicState.get();
    }

    @Override
    public RuleName getName() {
        return name;
    }

}
