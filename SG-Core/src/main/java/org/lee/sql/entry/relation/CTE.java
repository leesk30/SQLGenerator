package org.lee.sql.entry.relation;

import org.lee.base.NodeTag;
import org.lee.common.Utility;
import org.lee.sql.statement.Projectable;

public class CTE extends SubRTE {
    protected String cteName;
    public CTE(Projectable statement){
        super(statement);
        this.cteName = Utility.getRandomName("CTE_");
    }

    @Override
    public String getString() {
        return cteName;
    }

    public String getCTEBody(){
        // `q1 (a, b, c) as (ProjectableStatement)`
        String statementWithParentheses = projectable.isWithLogicalParentheses()?projectable.getString(): (LP + projectable.getString() + RP);
        return cteName + LP + nodeArrayToString(fieldList) + RP + SPACE + AS + SPACE + statementWithParentheses;
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.cte;
    }

    @Override
    public String getName() {
        return cteName;
    }
}