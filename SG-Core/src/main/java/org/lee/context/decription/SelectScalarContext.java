package org.lee.context.decription;

import org.lee.node.entry.scalar.ScalarAdaptor;

public class SelectScalarContext extends SubqueryContext implements Convertible2Scalar{
    @Override
    public ScalarAdaptor<SQLContext> toScalar() {
        return null;
    }
}
