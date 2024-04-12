package org.lee.context.decription;

import org.lee.node.entry.relation.RelationalAdaptor;
import org.lee.node.statement.statement.SQLStatement;

public interface Convertible2Relation {
    RelationalAdaptor<SQLStatement> toRelation();
}
