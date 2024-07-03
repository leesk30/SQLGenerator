package org.lee.statement.clause;

import org.lee.fuzzer.FuzzGenerator;
import org.lee.statement.node.TreeNode;

public interface Clause extends TreeNode, FuzzGenerator {
    boolean isEmptyClause();
}
