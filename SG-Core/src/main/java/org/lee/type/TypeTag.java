package org.lee.type;

public enum TypeTag {
    string(TypeCategory.STRING, "string", "text"),
    varchar(TypeCategory.STRING,"varchar"),
    int_(TypeCategory.NUMBER,"integer", "int1", "int2", "int4", "int8", "int"),
    date(TypeCategory.DATE,"date"),
    timestamp(TypeCategory.TIMESTAMP,"timestamp"),
    boolean_(TypeCategory.BOOLEAN,"boolean", "bool"),
    char_(TypeCategory.STRING,"character", "char"),
    decimal(TypeCategory.NUMBER,"decimal", "number", "numeric"),
    float_(TypeCategory.NUMBER,"double", "float", "float4", "float8", "real"),


    null_(TypeCategory.NIL,"null", "nan", "none", "nil")
    ;
    private final TypeCategory category;
    private final String[] names;
    TypeTag(TypeCategory category, String ... names){
        this.category = category;
        this.names = names;
    }

    public String[] getNames() {
        return names;
    }
    public static TypeTag getEnum(){
        // todo: from literal string value get enum value (string format to enum kind)
        return TypeTag.boolean_;
    }

    public TypeCategory getCategory(){
        return category;
    }
}
