package org.lee.statement.clause;

import org.lee.entry.RangeTableReference;
import org.lee.fuzzer.Generator;
import org.lee.fuzzer.qualification.JoinerQualificationGenerator;
import org.lee.statement.SQLStatement;
import org.lee.entry.complex.Filter;
import org.lee.node.NodeTag;
import org.lee.statement.expression.Qualification;
import org.lee.util.FuzzUtil;

import java.util.Iterator;
import java.util.Optional;
import java.util.stream.IntStream;

public abstract class WhereClause extends Clause<Filter> {
    protected final Filter filter = new Filter();
    protected WhereClause(SQLStatement statement) {
        super(statement, 1);
    }

    @Override
    public String getString() {
        return "\nWHERE " + nodeArrayToString(children);
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.whereClause;
    }

    @Override
    public Iterator<Filter> walk() {
        return null;
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
            final RangeTableReference left = fromClause.children.get(i);
            final RangeTableReference right = fromClause.children.get(i+1);
            final Generator<Qualification> generator = new JoinerQualificationGenerator(left, right);
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
