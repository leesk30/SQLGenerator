package org.lee.sql.type.mapped;

import org.lee.common.utils.RandomUtils;
import org.lee.sql.literal.LiteralInt;
import org.lee.sql.type.TypeTag;

public final class MappedInt extends MappedType<Integer>{

    private static final MappedInt self = new MappedInt();
    public static MappedInt getInstance(){
        return self;
    }

    private MappedInt() {
        super(Integer.class, TypeTag.int_);
    }

    @Override
    public LiteralInt generate() {
        return new LiteralInt(RandomUtils.randomIntFromRange(0, 100));
    }

    @Override
    public LiteralInt generate(int partial) {
        return new LiteralInt(RandomUtils.randomIntFromRange(partial, 100 + partial));
    }

}
