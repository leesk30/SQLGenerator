package org.lee.statement.node;

public enum NodeTag {
    scalar("scalar"),
    record("record"),
    array("array"),


    literal("literal", scalar),
    string("string", literal),

    relation("relation"),
    field("field"),
    targetEntry("targetEntry"),

    rangeTableReference("rangeTableReference"),

    operator("operator"),
    window("window"),
    aggregate("aggregate"),
    function("function"),

    expression("expression"),
    assignment("assignment"),
    qualification("qualification"),

    statement("statement"),
    clause("clause"),

    pivoted("pivoted")

    ;

    private final String name;
    private final NodeTag[] parentKinds;

    NodeTag(String name){
        this.name = name;
        this.parentKinds = null;
    }

    NodeTag(String name, NodeTag... parentKinds){
        this.name = name;
        this.parentKinds = parentKinds;
    }

    public boolean isAnyKindOf(NodeTag... nodeTags){
        for(NodeTag each: nodeTags){
            if(each == this)
                return true;
        }
        return false;
    }

    public boolean isSubKindOf(NodeTag nodeTag){
        if(this == nodeTag)
            return true;

        if(parentKinds == null)
            return false;

        for(NodeTag parent: parentKinds){
            if(parent.isSubKindOf(nodeTag))
                return true;
        }
        return false;
    }

    public boolean isAnySubKindOf(NodeTag... nodeTags){
        for(NodeTag each: nodeTags){
            if(this.isSubKindOf(each))
                return true;
        }
        return false;
    }


}
