package org.lee.statement.node;

import java.util.List;

public interface TreeNode extends Node {
    List<? extends Node> getChildNodes();
}
