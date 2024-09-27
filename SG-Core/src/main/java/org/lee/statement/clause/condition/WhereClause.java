package org.lee.statement.clause.condition;

import org.lee.entry.RangeTableReference;
import org.lee.common.exception.Assertion;
import org.lee.fuzzer.Generator;
import org.lee.statement.generator.JoinerQualificationGenerator;
import org.lee.statement.SQLStatement;
import org.lee.entry.complex.Filter;
import org.lee.node.NodeTag;
import org.lee.statement.clause.Clause;
import org.lee.statement.clause.from.FromClause;
import org.lee.statement.expression.Qualification;
import org.lee.common.util.FuzzUtil;

import java.util.stream.IntStream;

public abstract class WhereClause extends Clause<Filter> {
    protected final Filter filter = new Filter();
    protected WhereClause(SQLStatement statement) {
        super(statement, 1);
    }

    @Override
    public String getString() {
        Assertion.requireEquals(children.size(), 1);
        return WHERE + SPACE + nodeArrayToString(children);
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.whereClause;
    }



    protected void joinCondInWhere(){
        final FromClause fromClause = (FromClause) statement.getClause(NodeTag.fromClause);
        final int length = fromClause.size();
        if(length <= 1){
            return;
        }

        IntStream.range(0, fromClause.size()).parallel().forEach(i -> {
            if(i+1 >= length){
                return;
            }
            final RangeTableReference left = fromClause.getChildNodes().get(i);
            final RangeTableReference right = fromClause.getChildNodes().get(i+1);
            final Generator<Qualification> generator = new JoinerQualificationGenerator(config, left, right);
            // todo: add counting randomly
            final int generateCount = FuzzUtil.randomIntFromRange(1, 2);
            IntStream.range(0, generateCount).parallel().forEach(
                    j -> {
                        final Qualification qualification = generator.generate();
                        if(qualification != null){
                            filter.put(qualification);
                        }
                    }
            );
        });
    }
}
