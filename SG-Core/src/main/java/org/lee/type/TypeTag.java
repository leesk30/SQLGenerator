package org.lee.type;

import org.lee.type.base.SGType;
import org.lee.type.bdt.SGDate;

import java.util.Arrays;
import java.util.stream.Stream;

public enum TypeTag {
    string(TypeCategory.STRING, 2, "string", "text"),
    int_(TypeCategory.NUMBER,1, "integer", "int1", "int2", "int4", "int"),
    bigint(TypeCategory.NUMBER, 2, "int8", "long", "bigint"),
    date(TypeCategory.DATE, 1,"date"),
    timestamp(TypeCategory.TIMESTAMP, 1,"timestamp"),
    boolean_(TypeCategory.BOOLEAN, 1,"boolean", "bool"),
    char_(TypeCategory.STRING, 1,"character", "char"),
    decimal(TypeCategory.NUMBER, 4,"decimal", "number", "numeric"),
    float_(TypeCategory.NUMBER,3,"double", "float", "float4", "float8", "real"),


    null_(TypeCategory.NIL,1, "null", "nan", "none", "nil"),
    ;

    private final static TypeTag[] ALL = {
            string, int_, bigint, date, timestamp, boolean_, char_, float_, decimal, null_,
    };

    private final TypeCategory category;
    private final String[] names;
    private final int priority;
    TypeTag(TypeCategory category, int priority,  String ... names){
        this.category = category;
        this.names = names;
        this.priority = priority;
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

    public boolean isComparable() {
        return category.isComparable();
    }

    public boolean isComputable() {
        return category.isComputable();
    }

    public int getPriority() {
        return priority;
    }

    public static Stream<TypeTag> stream(){
        return Arrays.stream(ALL);
    }
}
