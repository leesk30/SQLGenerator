package org.lee.entry.literal.mapped;

import org.lee.entry.literal.LiteralDecimal;
import org.lee.entry.literal.LiteralDouble;
import org.lee.type.TypeTag;
import org.lee.util.FuzzUtil;

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
        return new LiteralDecimal(FuzzUtil.randomDecimalFromRange(0, 100));
    }

    @Override
    public LiteralDecimal generate(int partial) {
        return new LiteralDecimal(FuzzUtil.randomDecimalFromRange(partial, partial+100));
    }

    @Override
    public TypeTag getTypeTag() {
        return TypeTag.decimal;
    }
}
