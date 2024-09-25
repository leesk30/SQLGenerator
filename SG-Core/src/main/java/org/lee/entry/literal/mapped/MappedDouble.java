package org.lee.entry.literal.mapped;

import org.lee.entry.FieldReference;
import org.lee.entry.literal.LiteralDouble;
import org.lee.type.TypeTag;
import org.lee.util.FuzzUtil;

public class MappedDouble extends MappedType<Double>{
    private static final MappedDouble self = new MappedDouble();
    public static MappedDouble getInstance(){
        return self;
    }

    protected MappedDouble() {
        super(Double.class, TypeTag.float_);
    }

    @Override
    public LiteralDouble generate() {
        return new LiteralDouble(FuzzUtil.randomDoubleFromRange(0, 100));
    }

    @Override
    public LiteralDouble generate(int partial) {
        return new LiteralDouble(FuzzUtil.randomDoubleFromRange(partial, partial+100));
    }
}
