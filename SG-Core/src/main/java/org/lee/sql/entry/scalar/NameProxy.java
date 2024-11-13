package org.lee.sql.entry.scalar;

import org.lee.base.NodeTag;
import org.lee.type.TypeTag;

public class NameProxy implements Scalar {
    private final String name;
    private final TypeTag type;
    public NameProxy(String name, TypeTag type){
        this.name = name;
        this.type = type;
    }

    @Override
    public TypeTag getType() {
        return type;
    }

    @Override
    public String getString() {
        return name;
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.scalar;
    }
}
