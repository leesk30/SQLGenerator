package org.lee.symbol;

import org.lee.node.NodeTag;
import org.lee.type.TypeTag;

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
    public int argsNum() {
        return 0;
    }

    @Override
    public TypeTag getReturnType() {
        return null;
    }
}
