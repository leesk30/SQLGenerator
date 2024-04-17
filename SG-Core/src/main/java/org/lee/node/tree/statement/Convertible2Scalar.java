package org.lee.node.tree.statement;

import org.lee.node.entry.normalized.NormalizedScalar;
import org.lee.node.tree.statement.SQLStatement;

public interface Convertible2Scalar {
    NormalizedScalar<SQLStatement> toScalar();
}
