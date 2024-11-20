package org.lee.base;

import org.lee.common.generator.Fuzzer;
import org.lee.sql.support.SupportRuntimeConfiguration;

import java.util.UUID;

public interface Statement<T extends Node>
        extends TreeNode<T>,
        SupportRuntimeConfiguration,
        Fuzzer {
    Statement<?> getParent();
    UUID getUniqueTraceID();

    UUID getUniqueStatementID();
}
