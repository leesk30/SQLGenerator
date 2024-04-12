package org.lee.node.statement.statement;

import org.lee.context.decription.SQLContext;
import org.lee.node.base.Node;

public interface SQLStatement extends Node {
    SQLContext getContext();
}
