package org.lee.type.literal.mapped;

import org.lee.common.Utility;
import org.lee.type.literal.LiteralLong;
import org.lee.type.TypeTag;

public final class MappedLong extends MappedType<Long>{
    private static final MappedLong self = new MappedLong();
    public static MappedLong getInstance(){
        return self;
    }
    private MappedLong() {
        super(Long.class, TypeTag.bigint);
    }

    @Override
    public LiteralLong generate() {
        return new LiteralLong(Utility.randomLongFromRange(0, 100));
    }

    @Override
    public LiteralLong generate(int partial) {
        return new LiteralLong(Utility.randomLongFromRange(partial, partial+100));
    }

}
