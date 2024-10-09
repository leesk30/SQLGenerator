package org.lee.statement.clause.from;

import org.lee.base.NodeTag;
import org.lee.common.Utility;
import org.lee.common.config.Conf;
import org.lee.common.config.Rule;
import org.lee.entry.relation.CTE;
import org.lee.statement.SQLStatement;
import org.lee.statement.clause.Clause;
import org.lee.statement.support.SupportGenerateProjectable;

import java.util.stream.IntStream;

public class WithClause extends Clause<CTE> implements SupportGenerateProjectable {
    private boolean materialized = false;

    public WithClause(SQLStatement statement) {
        super(statement);
    }

    public WithClause(SQLStatement statement, int initialCapacity) {
        super(statement, initialCapacity);
    }

    @Override
    public String getString() {
        if(children.isEmpty()){
            return "";
        }
        return (materialized ? WITH + SPACE + MATERIALIZED : WITH + SPACE) + nodeArrayToString(",\n", children, CTE::getCTEBody);
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.withClause;
    }

    @Override
    public void fuzz() {
        if(!config.probability(Conf.WITH_CTE_PROBABILITY)){
            return;
        }
        final int numOfCTEs = Utility.randomIntFromRange(0, 3);
        if(numOfCTEs == 0){
            return;
        }
        if(config.confirm(Rule.SUPPORT_CTE_MATERIALIZED) && config.probability(Conf.USING_MATERIALIZED_CTE_PROB)){
            materialized = false;
        }
        IntStream.range(0, numOfCTEs).sequential().forEach(i -> children.add(new CTE(generate(this.statement))));
    }
}
