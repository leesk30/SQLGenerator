package org.lee.context.decription;

import org.lee.node.entry.scalar.ScalarAdaptor;

public interface Convertible2Scalar {
    ScalarAdaptor<SQLContext> toScalar();
}
