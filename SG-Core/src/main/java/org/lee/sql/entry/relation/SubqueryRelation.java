package org.lee.sql.entry.relation;

import org.lee.base.NodeTag;
import org.lee.sql.support.Projectable;

public class SubqueryRelation extends SubRTE {
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
