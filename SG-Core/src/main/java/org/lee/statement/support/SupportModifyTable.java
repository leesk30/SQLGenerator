package org.lee.statement.support;

import org.lee.node.Node;
import org.lee.statement.clause.Clause;
import org.lee.statement.clause.modify.ModifyTableClause;

public interface SupportModifyTable extends Statement<Clause<? extends Node>> {

    ModifyTableClause getModifyTableClause();
}
