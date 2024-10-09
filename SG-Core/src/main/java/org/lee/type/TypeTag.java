package org.lee.type;

import org.lee.common.Utility;
import org.lee.type.literal.mapped.MappedType;
import org.lee.base.NodeTag;
import org.lee.symbol.Parentheses;
import org.lee.symbol.Signature;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public enum TypeTag {
    string(TypeCategory.STRING, 2, "string", "text"),
    int_(TypeCategory.NUMBER,1, "integer", "int1", "int2", "int4", "int"),
    bigint(TypeCategory.NUMBER, 2, "bigint", "long", "int8"),
    date(TypeCategory.DATE, 1,"date"),
    timestamp(TypeCategory.TIMESTAMP, 1,"timestamp"),
    boolean_(TypeCategory.BOOLEAN, 1,"boolean", "bool"),
    char_(TypeCategory.STRING, 1,"char", "character"),
    decimal(TypeCategory.NUMBER, 4,"decimal", "number", "numeric"),
    float_(TypeCategory.NUMBER,3,"double", "float", "float4", "float8", "real"),


    null_(TypeCategory.NIL,1, "null", "nan", "none", "nil"),
    ;

    public final static TypeTag[] ALL = {
            string, int_, bigint, date, timestamp, boolean_, char_, float_, decimal, null_,
    };

    public final static TypeTag[] GENERATE_PREFER_CHOOSE = {
            string, int_, bigint, date, timestamp, char_, float_, decimal,
    };

    private final TypeCategory category;
    private final String[] names;
    private final int priority;
    private final EmptySymbol emptySymbol;
    private final Parentheses parenthesesSymbol;

    TypeTag(TypeCategory category, int priority,  String ... names){
        this.category = category;
        this.names = names;
        this.priority = priority;
        this.emptySymbol = new EmptySymbol(this);
        this.parenthesesSymbol = new Parentheses(emptySymbol);
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
            case "float": case "float4": case "float8": case "double": case "real": return float_;
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

    public <T> MappedType<T> asMapped(){
        return MappedType.get(this);
    }

    public Parentheses getParenthesesSymbol() {
        return parenthesesSymbol;
    }

    public Signature getEmptySymbol() {
        return emptySymbol;
    }

    public static TypeTag randomGenerateTarget(){
        return Utility.randomlyChooseFrom(GENERATE_PREFER_CHOOSE);
    }

    @Override
    public String toString() {
        return names[0];
    }

    private final static class EmptySymbol implements Signature {
        private final List<TypeTag> arguments;
        private final TypeTag returnType;
        public EmptySymbol(TypeTag target){
            this.arguments = Collections.singletonList(target);
            this.returnType = target;
            check();
        }

        @Override
        public final String getString() {
            return "%s";
        }

        @Override
        public final NodeTag getNodeTag() {
            return NodeTag.operator;
        }

        @Override
        public final int argsNum() {
            return 1;
        }

        @Override
        public final TypeTag getReturnType() {
            return returnType;
        }

        @Override
        public final List<TypeTag> getArgumentsTypes() {
            return arguments;
        }
    }
}
