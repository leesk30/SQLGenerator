package org.lee.sql.clause.from;

import org.lee.common.enumeration.Conf;
import org.lee.common.enumeration.NodeTag;
import org.lee.common.enumeration.Rule;
import org.lee.common.utils.RandomUtils;
import org.lee.sql.clause.Clause;
import org.lee.sql.entry.relation.CTE;
import org.lee.sql.statement.SQLStatement;
import org.lee.sql.support.ProjectableGenerator;

public class WithClause extends Clause<CTE> {
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
        final int numOfCTEs = RandomUtils.randomIntFromRange(0, 3);
        if(numOfCTEs == 0){
            return;
        }
        if(config.confirm(Rule.SUPPORT_CTE_MATERIALIZED) && config.probability(Conf.USING_MATERIALIZED_CTE_PROB)){
            materialized = false;
        }
        ProjectableGenerator generator = new ProjectableGenerator(this.statement);
        for (int i = 0; i < numOfCTEs; i++) {
            children.add(new CTE(generator.generate()));
        }
    }
}
