package org.lee.sql.clause.limit;


import org.lee.common.enumeration.Conf;
import org.lee.common.enumeration.Rule;
import org.lee.common.utils.RandomUtils;
import org.lee.sql.statement.select.SelectNormalStatement;
import org.lee.sql.statement.select.SelectStatement;

public class SelectLimitOffset extends LimitOffset {

    public SelectLimitOffset(SelectStatement statement) {
        super(statement);
    }

    public void rewriteToScalar(){
        if(statement instanceof SelectNormalStatement){
            /*
            *  If there is a group by clause in the query, we cannot make sure it could be a scalar query.
            *  eg1 : select max(b) from t1 group by b
            *  The eg1 is not a scalar query in mostly when it is without `limit 1`.
            * */
            SelectNormalStatement normalStatement = (SelectNormalStatement) statement;
            final boolean isTargetEntryScalarStyle = normalStatement.getSelectClause().getChildNodes().get(0).isScalarStyle();
            final boolean isNotGroupBy = normalStatement.getGroupByClause().isEmpty();
            if(isNotGroupBy && isTargetEntryScalarStyle){
                if(config.probability(Conf.LIMIT_OFFSET_CLAUSE_FUZZ_PROBABILITY)){
                    limitNode.set(RandomUtils.randomIntFromRange(1, 100));
                }

                if(config.probability(Conf.LIMIT_OFFSET_WITH_OFFSET_PROBABILITY)){
                    offsetNode.set(RandomUtils.randomIntFromRange(1, 100));
                }
                // Else: do nothing is ok
                return;
            }
        }
        // Else: statement is a setop statement

        limitNode.set(1);

        if(config.probability(Conf.LIMIT_OFFSET_WITH_OFFSET_PROBABILITY)){
            offsetNode.set(RandomUtils.randomIntFromRange(1, 100));
        }
    }

    @Override
    public void fuzz() {
        if(confirm(Rule.REQUIRE_SCALA)){
            rewriteToScalar();
            return;
        }

        if(config.probability(Conf.LIMIT_OFFSET_CLAUSE_FUZZ_PROBABILITY)){
            limitNode.set(RandomUtils.randomIntFromRange(1, 100));
        }

        if(config.probability(Conf.LIMIT_OFFSET_WITH_OFFSET_PROBABILITY)){
            offsetNode.set(RandomUtils.randomIntFromRange(1, 100));
        }
    }
}
