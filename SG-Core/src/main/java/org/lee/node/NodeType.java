package org.lee.node;

import java.util.List;

public enum NodeType {
    scalar("scalar"),
    record("record"),
    array("array"),


    literal("literal", scalar),
    string("string", literal),

    relation("")
    ;

    private final String name;
    private final NodeType[] parentKinds;

    NodeType(String name){
        this.name = name;
        this.parentKinds = null;
    }

    NodeType(String name, NodeType ... parentKinds){
        this.name = name;
        this.parentKinds = parentKinds;
    }

    public boolean isAnyKindOf(NodeType ... nodeTypes){
        for(NodeType each: nodeTypes){
            if(each == this)
                return true;
        }
        return false;
    }

    public boolean isSubKindOf(NodeType nodeType){
        if(this == nodeType)
            return true;

        if(parentKinds == null)
            return false;

        for(NodeType parent: parentKinds){
            if(parent.isSubKindOf(nodeType))
                return true;
        }
        return false;
    }

    public boolean isAnySubKindOf(NodeType ... nodeTypes){
        for(NodeType each: nodeTypes){
            if(this.isSubKindOf(each))
                return true;
        }
        return false;
    }


}
