package org.lee.base;

import org.lee.sql.support.Logging;
import org.lee.sql.support.SupportRuntimeConfiguration;

import java.util.UUID;

public interface Statement<T extends Node>
        extends TreeNode<T>,
        SupportRuntimeConfiguration,
        Fuzzer,
        Logging {
    Statement<?> getParent();
    UUID getUUID();
}