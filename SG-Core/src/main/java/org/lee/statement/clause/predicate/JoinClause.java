package org.lee.statement.clause.predicate;

import org.lee.base.Generator;
import org.lee.base.NodeTag;
import org.lee.common.Utility;
import org.lee.entry.RangeTableReference;
import org.lee.expression.Qualification;
import org.lee.expression.common.Location;
import org.lee.expression.generator.JoinQualificationGenerator;
import org.lee.statement.support.SQLStatement;

public abstract class JoinClause extends PredicateClause {
    protected Pattern pattern = Pattern.INNER;
    protected final RangeTableReference left;
    protected final RangeTableReference right;
    public JoinClause(SQLStatement statement, RangeTableReference left, RangeTableReference right) {
        super(Location.join, statement);
        this.left = left;
        this.right = right;
    }

    public enum Pattern {
        LEFT,
        INNER,
        RIGHT,
        NATUAL
    }

    public Pattern getPattern() {
        return pattern;
    }

    public RangeTableReference getLeft() {
        return left;
    }

    public RangeTableReference getRight(){
        return right;
    }

    @Override
    public String toString() {
        return getString();
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.joinClause;
    }

    @Override
    protected Generator<Qualification> createPredicateGenerator() {
        return new JoinQualificationGenerator(statement, left, right);
    }

    @Override
    public void fuzz() {
        Generator<Qualification> generator = createPredicateGenerator();
        int generateNum = Utility.randomIntFromRange(1, 2);
        for(int i = 0; i < generateNum; i++){
            filter.add(generator.generate());
        }
        filter.fuzz();
    }
}
