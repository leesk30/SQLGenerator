package org.lee.sql.type.mapped;

import org.lee.common.utils.RandomUtils;
import org.lee.sql.literal.LiteralLong;
import org.lee.sql.type.TypeTag;

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
        return new LiteralLong(RandomUtils.randomLongFromRange(0, 100));
    }

    @Override
    public LiteralLong generate(int partial) {
        return new LiteralLong(RandomUtils.randomLongFromRange(partial, partial+100));
    }

}
