package org.lee.type;

import org.lee.type.precision.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class TypeDescriptor {
    private final TypeTag tag;
    private final TypePrecision precision;
    private static final Map<TypeTag, TypeDescriptor> stashDescriptor = new HashMap<>();
    private static final String pattern = "^(varchar|decimal|char)\\s*\\(\\s*\\d+\\s*\\)$";
    private static final String decimalPattern = "^(decimal)\\s*\\(\\s*\\d+\\s*,\\s*\\d+\\s*\\)$";

    public TypeDescriptor(TypeTag tag, TypePrecision precision){
        this.tag = tag;
        this.precision = precision;
    }

    public static TypeDescriptor byInferPrecision(TypeTag tag){
        if (stashDescriptor.containsKey(tag)){
            return stashDescriptor.get(tag);
        }
        final TypeDescriptor descriptor;
        TypePrecision precision;
        switch (tag){
            case bigint: case float_: precision = FixedLen.sizeof8; break;
            case string: precision = Varlena.unlimited; break;
            case char_: precision = FixedLen.sizeof1; break;
            case decimal: precision = NumericVarlena.defaultPrecision; break;
            case int_: precision = FixedLen.sizeof4; break;
            case timestamp: precision = FixedLen.sizeof6; break;
            default: precision = NonPrecision.instance;
        }
        descriptor = new TypeDescriptor(tag, precision);
        stashDescriptor.put(tag, descriptor);
        return descriptor;
    }

    public TypePrecision getPrecision() {
        return precision;
    }

    public TypeTag getTag() {
        return tag;
    }

    public static TypeDescriptor string2Descriptor(String typeName){
        TypeTag tag = TypeTag.getEnum(typeName);
        if(tag != null){
            return byInferPrecision(tag);
        }
        String[] typeAndPrecision = parseTypeAndPrecision(typeName);
        tag = TypeTag.getEnum(typeAndPrecision[0]);
        int[] precisions = getVarlenaInParentheses(typeAndPrecision[1]);
        if(precisions.length == 0 ||
                tag == null ||
                (precisions.length == 2 && tag != TypeTag.decimal)){
            throw new RuntimeException("Unable to parse type by name: " + typeName);
        }
        TypePrecision precision;
        if(tag == TypeTag.decimal){
            precision = precisions.length == 1 ? new NumericVarlena(precisions[0]) : new NumericVarlena(precisions[0], precisions[1]);
        }else {
            precision = new FixedLen(precisions[0]);
        }
        return new TypeDescriptor(tag, precision);
    }

    private static String[] parseTypeAndPrecision(String typeName){
        String[] typeAndPrecision = new String[2];
        if(typeName.matches(pattern) || typeName.matches(decimalPattern)){
            int lp = typeName.indexOf("(");
            int rp = typeName.indexOf(")");
            typeAndPrecision[0] = typeName.substring(0, lp);
            typeAndPrecision[1] = typeName.substring(lp+1, rp);
            return typeAndPrecision;
        }
        throw new RuntimeException("Unable to parse type by name: " + typeName);
    }

    private static int[] getVarlenaInParentheses(String precisionInString){
        final String[] precisionStringArr = precisionInString.split(",");
        if(precisionStringArr.length == 0){
            throw new RuntimeException("Unable to parse type by precision: " + precisionInString);
        }
        final int[] precisionArr = new int[precisionStringArr.length];
        IntStream.range(0, precisionStringArr.length).forEach(i ->
            {
                precisionArr[i] = Integer.parseInt(precisionStringArr[i].trim());
                if(precisionArr[i] <= 0){
                    throw new RuntimeException("Unable to parse type by precision: the precision must be greater than zero");
                }
            }
        );
        return precisionArr;

    }
}
