package org.lee.sql.clause.predicate;

import org.lee.common.enumeration.NodeTag;
import org.lee.common.generator.Generator;
import org.lee.common.utils.RandomUtils;
import org.lee.generator.expression.CommonQualificationGenerator;
import org.lee.generator.expression.common.ExpressionLocation;
import org.lee.sql.entry.RangeTableReference;
import org.lee.sql.expression.Qualification;
import org.lee.sql.statement.SQLStatement;

public abstract class JoinClause extends PredicateClause {
    protected Pattern pattern = Pattern.INNER;
    protected final RangeTableReference left;
    protected final RangeTableReference right;
    public JoinClause(SQLStatement statement, RangeTableReference left, RangeTableReference right) {
        super(ExpressionLocation.join, statement);
        this.left = left;
        this.right = right;
    }

    public enum Pattern {
        LEFT,
        INNER,
        RIGHT,
        NATUAL,
        ;

        public static final Pattern[] IGNORE_NATUAL = new Pattern[]{LEFT, INNER, RIGHT};
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
    public NodeTag getNodeTag() {
        return NodeTag.joinClause;
    }

    @Override
    protected Generator<Qualification> createPredicateGenerator() {
        return new CommonQualificationGenerator(this.predicateLocation, statement, left.getFieldReferences(), right.getFieldReferences());
    }

    @Override
    public void fuzz() {
        Generator<Qualification> generator = createPredicateGenerator();
        int generateNum = RandomUtils.randomIntFromRange(1, 2);
        for(int i = 0; i < generateNum; i++){
            filter.add(generator.generate());
        }
        filter.fuzz();
    }
}
