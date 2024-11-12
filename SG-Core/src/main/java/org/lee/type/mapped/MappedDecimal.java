package org.lee.type.literal.mapped;

import org.lee.common.Utility;
import org.lee.type.TypeTag;
import org.lee.type.literal.LiteralDecimal;

import java.math.BigDecimal;

public class MappedDecimal extends MappedType<BigDecimal> {
    private static final MappedDecimal self = new MappedDecimal();
    public static MappedDecimal getInstance(){
        return self;
    }

    protected MappedDecimal() {
        super(BigDecimal.class, TypeTag.decimal);
    }

    @Override
    public LiteralDecimal generate() {
        return new LiteralDecimal(Utility.randomDecimalFromRange(0, 100));
    }

    @Override
    public LiteralDecimal generate(int partial) {
        return new LiteralDecimal(Utility.randomDecimalFromRange(partial, partial+100));
    }

    public LiteralDecimal generate(int intDigitLength, int floatDigitLength) {
        intDigitLength = Math.min(3, intDigitLength);
        floatDigitLength = Math.min(8, floatDigitLength);
        long integerPart = Utility.randomLongFromRange(0, (int)Math.pow(10, intDigitLength));
        long floatPart = Utility.randomLongFromRange(0, (int)Math.pow(10, floatDigitLength));
        return toDecimal(integerPart, floatPart);
    }

    private LiteralDecimal toDecimal(long integerPart, long floatPart){
        String intPartString = String.valueOf(integerPart);
        if(floatPart > 0){
            String floatPartString = new StringBuffer(String.valueOf(floatPart)).reverse().toString();
            return new LiteralDecimal(new BigDecimal(intPartString + "." + floatPartString));
        }
        return new LiteralDecimal(new BigDecimal(integerPart));
    }

    @Override
    public TypeTag getTypeTag() {
        return TypeTag.decimal;
    }
}
