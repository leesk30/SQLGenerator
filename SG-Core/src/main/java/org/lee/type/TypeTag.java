package org.lee.type;

public enum TypeTag {
    STRING("string", "text"),
    INTEGER("integer", "int1", "int2", "int4", "int8", "int"),
    DATE("date"),
    BOOLEAN("boolean", "bool")

    ;
    private final String[] names;
    TypeTag(String ... names){
        this.names = names;
    }

    public String[] getNames() {
        return names;
    }
    public static TypeTag getEnum(){
        // todo: from literal string value get enum value (string format to enum kind)
        return TypeTag.BOOLEAN;
    }
}
