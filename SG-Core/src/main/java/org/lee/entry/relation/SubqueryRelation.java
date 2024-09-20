package org.lee.entry.relation;

import org.lee.statement.support.Projectable;
import org.lee.node.NodeTag;

public class SubqueryRelation extends SubEntry {
    public SubqueryRelation(Projectable projectable){
        super(projectable);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getString() {
        return projectable.getString();
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.relation;
    }
}
