package org.lee.entry.relation;

import org.lee.common.Utility;
import org.lee.statement.support.Projectable;
import org.lee.base.NodeTag;

public class CTE extends SubEntry {
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
        return cteName + LP + nodeArrayToString(fieldList) + RP + SPACE + AS + SPACE + projectable.getString();
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
