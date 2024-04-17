package org.lee.symbol;

import org.lee.node.NodeTag;
import org.lee.type.TypeTag;
import org.lee.type.base.SGType;

import java.util.List;

public class SignatureBaseImpl implements Signature{
    public SignatureBaseImpl(){

    }

    @Override
    public String getString() {
        return null;
    }

    @Override
    public NodeTag getNodeTag() {
        return null;
    }

    @Override
    public List<List<SGType>> getArgType() {
        return null;
    }

    @Override
    public boolean hasOverwrite() {
        return false;
    }

    @Override
    public SGType getReturnType() {
        return null;
    }

    @Override
    public TypeTag getReturnTypeTag() {
        return Signature.super.getReturnTypeTag();
    }
}
