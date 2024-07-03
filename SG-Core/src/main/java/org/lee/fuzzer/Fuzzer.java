package org.lee.fuzzer;

import org.lee.statement.node.Node;

public interface Fuzzer {
    Node fuzzy(Node node);
    Fuzzer next();
    boolean hasNext();
}
