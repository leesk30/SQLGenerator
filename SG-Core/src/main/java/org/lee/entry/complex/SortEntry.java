package org.lee.entry.complex;

import org.lee.entry.NormalizedEntryWrapper;
import org.lee.entry.scalar.Scalar;
import org.lee.fuzzer.Fuzzer;
import org.lee.node.NodeTag;
import org.lee.rules.RuleName;
import org.lee.rules.RuleSet;
import org.lee.statement.support.Alias;
import org.lee.type.TypeTag;
import org.lee.util.FuzzUtil;

public class SortEntry implements NormalizedEntryWrapper<Scalar>, Scalar, Fuzzer {

    private final Scalar scalar;
    private final boolean isDefaultAsc;
    private final boolean isDefaultNullLast;
    private final boolean supportWithProjectionAlias;

    private boolean orderOptionAsc;
    private boolean nullOptionLast;
    private boolean representOrderOption;
    private boolean representNullOption;

    public SortEntry(Scalar scalar, RuleSet bySyntax){
        this.scalar = scalar;
        this.isDefaultAsc = !bySyntax.confirm(RuleName.ORDER_DEFAULT_DESC);
        this.isDefaultNullLast = !bySyntax.confirm(RuleName.ORDER_DEFAULT_NULL_FIRST);
        this.supportWithProjectionAlias = bySyntax.confirm(RuleName.ENABLE_FILTER_USING_PROJECTION_ALIAS);
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
        orderOptionAsc = FuzzUtil.probability(50);
        nullOptionLast = FuzzUtil.probability(50);
        if((isDefaultAsc && orderOptionAsc) || (!isDefaultAsc && !orderOptionAsc)){
            representOrderOption = FuzzUtil.probability(50);
        }else {
            representOrderOption = true;
        }

        if((isDefaultNullLast && nullOptionLast) || (!isDefaultNullLast && !nullOptionLast)){
            representNullOption = FuzzUtil.probability(50);
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
