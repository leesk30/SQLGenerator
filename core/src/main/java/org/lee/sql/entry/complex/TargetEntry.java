package org.lee.sql.entry.complex;

import org.apache.commons.lang3.StringUtils;
import org.lee.common.Assertion;
import org.lee.common.enumeration.NodeTag;
import org.lee.common.structure.Pair;
import org.lee.common.utils.RandomUtils;
import org.lee.sql.entry.FieldReference;
import org.lee.sql.entry.Normalized;
import org.lee.sql.entry.scalar.Field;
import org.lee.sql.entry.scalar.NameProxy;
import org.lee.sql.entry.scalar.Scalar;
import org.lee.sql.expression.Expression;
import org.lee.sql.support.Alias;
import org.lee.sql.type.TypeTag;

import java.util.Collections;
import java.util.List;

public class TargetEntry implements Normalized<Scalar>, Scalar, Alias {
    private final static Pair<List<FieldReference>, List<FieldReference>> EMPTY = new Pair<>(Collections.emptyList(), Collections.emptyList());
    protected String alias = null;
    protected final Scalar target;
    private final boolean isTargetEntryExpression;
    private final boolean isTargetEntryAggregate;
    private final boolean isTargetEntryIncludingAggregate;
    private final Pair<List<FieldReference>, List<FieldReference>> cachedExtracted;

    public TargetEntry(FieldReference targetScalar){
        Assertion.requiredNonNull(targetScalar);
        this.target = targetScalar;
        this.isTargetEntryExpression = false;
        this.isTargetEntryAggregate = false;
        this.isTargetEntryIncludingAggregate = false;
        this.cachedExtracted = EMPTY;
    }

    public TargetEntry(NameProxy targetScalar){
        Assertion.requiredNonNull(targetScalar);
        this.target = targetScalar;
        this.isTargetEntryExpression = false;
        this.isTargetEntryAggregate = false;
        this.isTargetEntryIncludingAggregate = false;
        this.cachedExtracted = EMPTY;
    }

    public TargetEntry(Expression expression){
        this(expression, false);
    }

    public TargetEntry(Expression expression, boolean rename){
        Assertion.requiredNonNull(expression);
        Assertion.requiredTrue(expression.isComplete());
        this.target = expression;
        this.isTargetEntryExpression = true;
        this.isTargetEntryAggregate = expression.isCurrentAggregation();
        this.isTargetEntryIncludingAggregate = expression.isIncludingAggregation();
        this.cachedExtracted = expression.extractAggregate();
        if(rename){
            this.setAlias();
        }
    }

    public static TargetEntry newNamedEntry(TypeTag typeTag){
        return new TargetEntry(new NameProxy(
                RandomUtils.getRandomName("n_"),
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
        alias = RandomUtils.getRandomName("te_");
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

    public boolean isTargetEntryAggregate() {
        return isTargetEntryAggregate;
    }

    public boolean isTargetEntryExpression() {
        return isTargetEntryExpression;
    }

    public boolean isTargetEntryIncludingAggregate() {
        return isTargetEntryIncludingAggregate;
    }

    public Pair<List<FieldReference>, List<FieldReference>> getCachedExtracted() {
        return cachedExtracted;
    }

    public boolean isScalarStyle(){
        if(isTargetEntryAggregate){
            return true;
        }
        if(!isTargetEntryIncludingAggregate){
            return false;
        }
        // For example: the column style like 'max(a) + b' is not an illegal scalar style
        //  But for 'max(a) + 1' is a legal scalar style
        if(cachedExtracted.getSecond() != null){
            return cachedExtracted.getSecond().isEmpty();
        }
        return false;
    }

    @Override
    public Scalar getWrapped() {
        return target;
    }
}
