package org.lee.sql.type.mapped;

import org.lee.common.Utility;
import org.lee.sql.literal.LiteralDouble;
import org.lee.sql.type.TypeTag;

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
        return new LiteralDouble(Utility.randomDoubleFromRange(0, 100));
    }

    @Override
    public LiteralDouble generate(int partial) {
        return new LiteralDouble(Utility.randomDoubleFromRange(partial, partial+100));
    }
}