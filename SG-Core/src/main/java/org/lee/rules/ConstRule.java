package org.lee.rules;

public class ConstRule implements Rule{
    private final RuleName name;
    private final boolean optionValue;
    public ConstRule(final RuleName name, final boolean optionValue){
        this.name = name;
        this.optionValue = optionValue;
    }

    @Override
    public boolean pass() {
        return optionValue;
    }

    @Override
    public RuleName getName() {
        return name;
    }

}
