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

    public void withScalaRules(){
        if(statement instanceof SelectNormalStatement){
            SelectNormalStatement normalStatement = (SelectNormalStatement) statement;
            if(normalStatement.getSelectClause().getChildNodes().get(0).isScalarStyle()){
                if(config.probability(Conf.LIMIT_OFFSET_CLAUSE_FUZZ_PROBABILITY)){
                    limitNode.set(RandomUtils.randomIntFromRange(1, 100));
                }
                // Else: do nothing is ok
                return;
            }
        }
        limitNode.set(1);

        if(config.probability(Conf.LIMIT_OFFSET_WITH_OFFSET_PROBABILITY)){
            offsetNode.set(RandomUtils.randomIntFromRange(1, 100));
        }
    }

    @Override
    public void fuzz() {
        if(confirm(Rule.REQUIRE_SCALA)){
            withScalaRules();
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
