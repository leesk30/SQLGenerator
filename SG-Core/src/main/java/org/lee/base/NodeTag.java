package org.lee.base;

public enum NodeTag {
    __("nothing"),
    scalar("scalar"),
    record("record"),
    array("array"),
    keyword("keyword"),


    literal("literal", scalar),
    string("string", literal),

    relation("relation"),
    field("field"),
    targetEntry("targetEntry"),

    rangeTableReference("rangeTableReference"),

    signature("signature"),
    operator("operator", signature),
    combiner("combiner", signature),
    window("window", signature),
    aggregate("aggregate", signature),
    function("function", signature),

    expression("expression", scalar),
    assignment("assignment", scalar),
    qualification("qualification", scalar),

    statement("statement"),
    clause("clause"),

    withClause("withClause", clause),
    selectClause("selectClause", clause),
    whereClause("whereClause", clause),
    fromClause("fromClause", clause),
    joinClause("joinClause", clause),
    groupByClause("groupByClause", clause),
    sortByClause("sortByClause", clause),
    havingClause("havingClause", clause),
    startWithClause("startWithClause", clause),
    connectByClause("connectByClause", clause),
    limitOffset("limitOffset", clause),
    valuesClause("valuesClause", clause),
    modifyTableClause("modifyTableClause", clause),


    pivoted("pivoted"),
    rteJoin("rteJoin"),
    fieldReference("fieldReference"),
    cte("cte"),

    filter("filter"),
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
