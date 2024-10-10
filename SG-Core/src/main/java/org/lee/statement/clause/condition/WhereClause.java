package org.lee.statement.clause.condition;

import org.lee.base.Generator;
import org.lee.base.NodeTag;
import org.lee.common.Assertion;
import org.lee.common.Utility;
import org.lee.entry.RangeTableReference;
import org.lee.entry.complex.Filter;
import org.lee.statement.clause.Clause;
import org.lee.statement.clause.from.FromClause;
import org.lee.statement.expression.Qualification;
import org.lee.statement.expression.generator.JoinerQualificationGenerator;
import org.lee.statement.support.SQLStatement;

public abstract class WhereClause extends Clause<Filter> {
    protected final Filter filter = new Filter();
    protected WhereClause(SQLStatement statement) {
        super(statement, 1);
    }

    @Override
    public String getString() {
        Assertion.requiredEquals(children.size(), 1);
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

        for(int i = 0; i < fromClause.size(); i++){
            if(i+1 >= length){
                return;
            }
            final RangeTableReference left = fromClause.getChildNodes().get(i);
            final RangeTableReference right = fromClause.getChildNodes().get(i+1);
            final Generator<Qualification> generator = new JoinerQualificationGenerator(this.statement, left, right);
            // todo: add counting randomly
            final int generateCount = Utility.randomIntFromRange(1, 2);
            for(int j = 0; j < generateCount; j++){
                final Qualification qualification = generator.generate();
                if(qualification != null){
                    filter.put(qualification);
                }
            }
        }
    }
}
