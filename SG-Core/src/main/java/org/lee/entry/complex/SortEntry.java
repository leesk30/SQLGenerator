package org.lee.entry.complex;

import org.lee.base.Fuzzer;
import org.lee.base.NodeTag;
import org.lee.common.Utility;
import org.lee.common.config.Rule;
import org.lee.common.config.RuntimeConfiguration;
import org.lee.entry.NormalizedEntryWrapper;
import org.lee.entry.scalar.Scalar;
import org.lee.statement.support.Alias;
import org.lee.type.TypeTag;

public class SortEntry implements NormalizedEntryWrapper<Scalar>, Scalar, Fuzzer {

    private final Scalar scalar;
    private final boolean isDefaultAsc;
    private final boolean isDefaultNullLast;
    private final boolean supportWithProjectionAlias;
    // runtime mutable options
    private boolean orderOptionAsc;
    private boolean nullOptionLast;
    private boolean representOrderOption;
    private boolean representNullOption;

    public SortEntry(Scalar scalar, RuntimeConfiguration config){
        this.scalar = scalar;
        this.isDefaultAsc = !config.getRule(Rule.ORDER_DEFAULT_DESC);
        this.isDefaultNullLast = !config.getRule(Rule.ORDER_DEFAULT_NULL_FIRST);
        this.supportWithProjectionAlias = config.getRule(Rule.ENABLE_FILTER_USING_PROJECTION_ALIAS);
    }

    @Override
    public TypeTag getType() {
        return scalar.getType();
    }

    @Override
    public String getString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getSortEntryBody());
        if(representOrderOption){
            builder.append(orderOptionAsc ? SPACE + ASC : SPACE + DESC);
        }
        if(representNullOption){
            builder.append(nullOptionLast ? SPACE + NULLS + SPACE + LAST : SPACE + NULLS + SPACE + FIRST);
        }
        return builder.toString();
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.scalar;
    }

    private String getSortEntryBody(){
        if(scalar instanceof Alias){
            Alias alias = ((Alias) scalar);
            if(supportWithProjectionAlias){
                return alias.getAlias();
            }else {
                return alias.getStringWithoutAlias();
            }
        }
        return scalar.getString();
    }

    @Override
    public void fuzz() {
        orderOptionAsc = Utility.probability(50);
        nullOptionLast = Utility.probability(50);
        if((isDefaultAsc && orderOptionAsc) || (!isDefaultAsc && !orderOptionAsc)){
            representOrderOption = Utility.probability(50);
        }else {
            representOrderOption = true;
        }

        if((isDefaultNullLast && nullOptionLast) || (!isDefaultNullLast && !nullOptionLast)){
            representNullOption = Utility.probability(50);
        }else {
            representNullOption = true;
        }
    }

    public boolean isRepresentNullOption() {
        return representNullOption;
    }

    public boolean isRepresentOrderOption() {
        return representOrderOption;
    }

    public boolean isAsc(){
        return orderOptionAsc;
    }

    public boolean isNullLast(){
        return nullOptionLast;
    }

    @Override
    public Scalar getWrapped() {
        return scalar;
    }

}
