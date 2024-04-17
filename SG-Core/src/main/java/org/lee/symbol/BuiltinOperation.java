package org.lee.symbol;

import org.lee.node.NodeTag;
import org.lee.type.base.SGType;

import java.util.List;

public enum BuiltinOperation implements Signature{
    ADD("+"){
        @Override
        public List<List<SGType>> getArgType() {
            return null;
        }

        @Override
        public boolean hasOverwrite() {
            return false;
        }

        @Override
        public NodeTag getNodeTag(){
            return NodeTag.operator;
        }

    },
    MINUS("-"){
        @Override
        public List<List<SGType>> getArgType() {
            return null;
        }

        @Override
        public boolean hasOverwrite() {
            return false;
        }

        @Override
        public NodeTag getNodeTag(){
            return NodeTag.operator;
        }
    }
    ;

    private final String body;

    BuiltinOperation(String body){
        this.body = body;
    }

    @Override
    public String getString() {
        return this.body;
    }

    @Override
    public SGType getReturnType() {
        return null;
    }
}
