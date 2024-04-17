package org.lee.node.tree.statement;

import org.lee.node.entry.normalized.NormalizedRelation;
import org.lee.node.tree.statement.SQLStatement;

public interface Convertible2Relation {
    NormalizedRelation<SQLStatement> toRelation();
}
