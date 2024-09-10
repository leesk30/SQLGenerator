package org.lee.type;

import org.lee.type.base.SGType;
import org.lee.type.bdt.SGDate;

public enum TypeTag {
    string(TypeCategory.STRING, "string", "text"),
    int_(TypeCategory.NUMBER,"integer", "int1", "int2", "int4", "int"),
    bigint(TypeCategory.NUMBER, "int8", "long", "bigint"),
    date(TypeCategory.DATE,"date"),
    timestamp(TypeCategory.TIMESTAMP,"timestamp"),
    boolean_(TypeCategory.BOOLEAN,"boolean", "bool"),
    char_(TypeCategory.STRING,"character", "char"),
    decimal(TypeCategory.NUMBER,"decimal", "number", "numeric"),
    float_(TypeCategory.NUMBER,"double", "float", "float4", "float8", "real"),


    null_(TypeCategory.NIL,"null", "nan", "none", "nil"),
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
    public static TypeTag getEnum(String typeName){
        // todo: from literal string value get enum value (string format to enum kind)
        switch (typeName){
            case "string": case "text": case "varchar": return string;
            case "integer": case "int": case "int1": case "int2": case "int4": return int_;
            case "int8": case "bigint": case "long": return bigint;
            case "decimal": case "number": case "numeric": return decimal;
            case "date": return date;
            case "timestamp": return timestamp;
            case "char": return char_;
            case "float4": case "float8": case "double": case "real": return float_;
            case "boolean": case "bool": return boolean_;
            case "null": case "nan": case "none": case "nil": return null_;
            default: return null;
        }
    }

    public TypeCategory getCategory(){
        return category;
    }

    public boolean isSimilarWith(TypeTag typeTag){
        return typeTag != null && (typeTag == this || typeTag.category == this.category);
    }
}
