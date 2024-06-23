package org.lee.fuzzer;

import org.lee.node.base.Node;

public interface IFuzzer {
    Node fuzzy();
    IFuzzer next();
    boolean hasNext();
    void terminate();
}
