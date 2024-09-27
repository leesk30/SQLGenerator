package org.lee.type;

import org.lee.type.literal.Literal;
import org.lee.type.literal.mapped.MappedDecimal;
import org.lee.type.literal.mapped.MappedType;
import org.lee.fuzzer.Generator;
import org.lee.node.Node;
import org.lee.type.precision.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class TypeDescriptor implements Generator<Literal<?>> {
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
            case string: precision = Varlena.UNLIMITED_VARLENA_PRECISION; break;
            case char_: precision = CharVarlena.DEFAULT_CHAR_PRECISION; break;
            case decimal: precision = NumericVarlena.DEFAULT_NUMERIC_PRECISION; break;
            case int_: precision = FixedLen.sizeof4; break;
            case timestamp: precision = FixedLen.sizeof6; break;
            default: precision = NonPrecision.NON_PRECISION;
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
        switch (tag){
            case decimal:
                precision = precisions.length == 1 ? new NumericVarlena(precisions[0]) : new NumericVarlena(precisions[0], precisions[1]);
                break;
            case char_:
                precision = new CharVarlena(precisions[0]);
                break;
            case string:
                precision = new Varlena(precisions[0]);
                break;
            default:
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

    @Override
    public String toString() {
        String precisionPart = precision.toString();
        if(precisionPart.isEmpty() && tag == TypeTag.char_){
            return this.tag + Node.LP + 1 + Node.RP;
        } else if (!precisionPart.isEmpty() && tag == TypeTag.string) {
            return "varchar" + precisionPart;
        }
        return this.tag.toString() + precisionPart;
    }


    @Override
    public Literal<?> generate() {
        TypeCategory category = this.tag.getCategory();
        if(category == TypeCategory.STRING && precision.getPrecision() > 0){
            return tag.asMapped().generate(precision.getPrecision());
        } else if (category == TypeCategory.NUMBER && tag == TypeTag.decimal) {
            MappedType<BigDecimal> mapped = tag.asMapped();
            MappedDecimal mappedDecimal = (MappedDecimal) mapped;
            if(precision instanceof NumericVarlena){
                NumericVarlena numericVarlena = (NumericVarlena)precision;
                return mappedDecimal.generate(numericVarlena.getIntDigitLength(), numericVarlena.getFloatDigitLength());
            }
        }
        return tag.asMapped().generate();

    }
}
