package org.lee.sql.entry.complex;

import org.lee.base.Fuzzer;
import org.lee.base.Node;
import org.lee.base.NodeTag;
import org.lee.common.Assertion;
import org.lee.common.Utility;
import org.lee.sql.entry.Normalized;
import org.lee.sql.entry.scalar.Scalar;
import org.lee.sql.expression.Expression;
import org.lee.sql.expression.IExpression;
import org.lee.sql.expression.Qualification;
import org.lee.sql.symbol.Parentheses;
import org.lee.sql.symbol.common.PredicateCombiner;
import org.lee.sql.symbol.basic.Symbol;
import org.lee.type.TypeTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Filter extends ArrayList<Qualification> implements Normalized<IExpression<Expression>>, IExpression<Expression>, Fuzzer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Filter.class);
    private Qualification combined = null;
    public Filter(){}

    @Override
    public TypeTag getType() {
        return TypeTag.boolean_;
    }

    @Override
    public String getString() {
        if(combined == null){
            LOGGER.error("The filter combined result cannot be empty or null");
        }
        return combined.getString();
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.filter;
    }

    @Override
    public void fuzz() {
        if(this.isEmpty()){
            return;
        }

        List<Qualification> merged = new ArrayList<>(this);
        while (merged.size() > 1){
            merged = randomlyMerge(merged);
        }
        assert merged.size() == 1;
        combined = merged.get(0);
    }

    private List<Qualification> randomlyMerge(List<Qualification> qualifications){
        if(qualifications.size() < 2){
            return qualifications;
        }
        if(qualifications.size() == 2){
            return Collections.singletonList(combine(qualifications.get(0), qualifications.get(1)));
        }
        final List<Qualification> result = new ArrayList<>();
        Collections.shuffle(qualifications);
        final int splitIndex = Utility.randomIntFromRange(1, qualifications.size() - 1);
        final List<Qualification> lhs = qualifications.subList(0, splitIndex);
        final List<Qualification> rhs = qualifications.subList(splitIndex, qualifications.size());
        result.addAll(randomlyMerge(lhs));
        result.addAll(randomlyMerge(rhs));
        return result;
    }

    private Qualification combine(Qualification left, Qualification right){
        Qualification qualification = new Qualification(Utility.probability(50) ? PredicateCombiner.AND : PredicateCombiner.OR);
        final AtomicInteger counter = new AtomicInteger(0);
        java.util.function.Function<Qualification, Qualification> tryToNegative = (qual) -> {
            if(Utility.probability(3 - counter.get())){
                counter.set(counter.get() + 1);
                return qual.toNegative();
            }else {
                return qual;
            }
        };
        return tryToNegative.apply(
                qualification.newChild(tryToNegative.apply(left))
                        .newChild(tryToNegative.apply(right))
        );
    }

    @Override
    public Qualification getWrapped() {
        return combined;
    }

    @Override
    public boolean isTerminateNode() {
        return combined != null && combined.isTerminateNode();
    }

    @Override
    public List<IExpression<Expression>> getChildNodes() {
        if(combined == null){
            return Collections.emptyList();
        }
        return new ArrayList<>(combined.getChildNodes());
    }

    @Override
    public Node getCurrent() {
        if(combined == null){
            return null;
        }
        return combined.getCurrent();
    }

    public boolean add(Filter filter) {
        if(filter.combined == null){
            return false;
        }
        return super.add(filter.combined);
    }

    @Override
    public List<Expression> getLeafs() {
        if(combined == null){
            return Collections.emptyList();
        }
        return combined.getLeafs();
    }

    @Override
    public Qualification newChild(Node current) {
        final Qualification qualification;
        if(current instanceof Expression){
            Expression expr = (Expression) current;
            qualification = expr.toCompleteQualification();
            this.add(qualification);
            return qualification;
        }

        if(current instanceof Symbol){
            Symbol symbol = (Symbol) current;
            qualification = symbol.toCompleteQualification();
            this.add(qualification);
            return qualification;
        }

        if(current instanceof Scalar){
            Scalar scalar = (Scalar) current;
            qualification = scalar.toCompleteExpression().toCompleteQualification();
            this.add(qualification);
            return qualification;
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public Filter toWithParentheses() {
        if(!(this.combined.getCurrent() instanceof Parentheses)){
            this.combined = this.combined.toWithParentheses();
        }
        return this;
    }

    @Override
    public Expression toCompleteExpression() {
        Assertion.requiredNonNull(combined);
        Assertion.requiredTrue(combined.isComplete());
        return combined;
    }

    public Qualification toCompleteQualification() {
        Assertion.requiredNonNull(combined);
        Assertion.requiredTrue(combined.isComplete());
        return combined;
    }
}
