package org.lee.entry.complex;

import org.apache.commons.lang3.StringUtils;
import org.lee.entry.FieldReference;
import org.lee.entry.scalar.Field;
import org.lee.entry.scalar.NameProxy;
import org.lee.entry.scalar.Scalar;
import org.lee.statement.expression.Expression;
import org.lee.statement.support.Alias;
import org.lee.node.NodeTag;
import org.lee.type.TypeTag;
import org.lee.util.FuzzUtil;

public class TargetEntry implements Scalar, Alias {
    private String alias = null;
    private final Scalar target;
    private final boolean isTargetEntryExpression;
    private final boolean isTargetEntryAggregate;

    public TargetEntry(FieldReference targetScalar){
        assert targetScalar != null;
        this.target = targetScalar;
        this.isTargetEntryExpression = false;
        this.isTargetEntryAggregate = false;
    }

    public TargetEntry(NameProxy targetScalar){
        assert targetScalar != null;
        this.target = targetScalar;
        this.isTargetEntryExpression = false;
        this.isTargetEntryAggregate = false;
    }

    public TargetEntry(Expression expression){
        assert expression != null;
        this.target = expression;
        this.isTargetEntryExpression = true;
        this.isTargetEntryAggregate = expression.isIncludingAggregation();
    }

    public static TargetEntry newNamedEntry(TypeTag typeTag){
        return new TargetEntry(new NameProxy(
                FuzzUtil.getRandomName("n_"),
                typeTag
        ));
    }

    @Override
    public boolean hasAlias() {
        return alias != null && !(StringUtils.isBlank(alias) || StringUtils.isEmpty(alias));
    }

    @Override
    public String getAlias() {
        return alias != null ? alias: "";
    }

    @Override
    public void setAlias() {
        if(hasAlias()){
            throw new RuntimeException("The alias has already been set.");
        }
        alias = FuzzUtil.getRandomName("te_");
    }

    @Override
    public String getString() {
        if(hasAlias()){
            return String.format("%s as %s", target.getString(), alias);
        }
        return target.getString();
    }

    @Override
    public String getStringWithoutAlias() {
        return target.getString();
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.targetEntry;
    }

    public Field toField(){
        String fieldName = hasAlias() ? alias : target.getString();
        TypeTag fieldType = target.getType();
        return new Field(fieldName, fieldType);
    }

    @Override
    public TypeTag getType() {
        return target.getType();
    }

    public Scalar getTarget(){
        return target;
    }

    public boolean isTargetEntryAggregate() {
        return isTargetEntryAggregate;
    }

    public boolean isTargetEntryExpression() {
        return isTargetEntryExpression;
    }
}
