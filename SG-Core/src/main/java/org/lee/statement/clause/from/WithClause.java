package org.lee.statement.clause.from;

import org.lee.common.DevTempConf;
import org.lee.node.Node;
import org.lee.rules.RuleName;
import org.lee.statement.SQLStatement;
import org.lee.entry.relation.CTE;
import org.lee.node.NodeTag;
import org.lee.statement.clause.Clause;
import org.lee.statement.support.SupportGenerateProjectable;
import org.lee.util.FuzzUtil;
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
        if(!FuzzUtil.probability(DevTempConf.WITH_CTE_PROBABILITY)){
            return;
        }
        final int numOfCTEs = FuzzUtil.randomIntFromRange(0, 3);
        if(numOfCTEs == 0){
            return;
        }
        if(statement.confirmByRuleName(RuleName.SUPPORT_CTE_MATERIALIZED)
                && FuzzUtil.probability(DevTempConf.USING_MATERIALIZED_CTE_PROB)){
            materialized = false;
        }
        IntStream.range(0, numOfCTEs).parallel().forEach(i -> children.add(new CTE(generate(this.statement))));
    }
}
