package org.lee.context.decription;

import org.lee.node.entry.relation.RelationalAdaptor;
import org.lee.node.statement.statement.SQLStatement;

public class SubqueryContext extends SelectContext implements Convertible2Relation {
    @Override
    public RelationalAdaptor<SQLStatement> toRelation() {
        return null;
    }
}
