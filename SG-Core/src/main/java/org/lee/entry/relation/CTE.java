package org.lee.entry.relation;

import org.lee.statement.support.Projectable;
import org.lee.statement.support.Alias;
import org.lee.node.NodeTag;

public class CTE extends SubEntry {
    protected String cteName;
    public CTE(Projectable statement){
        super(statement);
        this.cteName = Alias.getRandomName("SC");
    }

    @Override
    public String getString() {
        return null;
    }

    @Override
    public NodeTag getNodeTag() {
        return null;
    }

    @Override
    public String getName() {
        return cteName;
    }
}
