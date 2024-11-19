package org.lee.sql.type.mapped;

import org.lee.common.utils.RandomUtils;
import org.lee.sql.literal.LiteralString;
import org.lee.sql.type.TypeTag;

public class MappedString extends MappedType<String> {
    private static final MappedString self = new MappedString();
    public static MappedString getInstance(){
        return self;
    }

    protected MappedString() {
        super(String.class, TypeTag.string);
    }

    @Override
    public LiteralString generate() {
        return new LiteralString(RandomUtils.randomStringByLength(10));
    }

    @Override
    public LiteralString generate(int partial) {
        partial = Math.min(partial, RandomUtils.ACTUALLY_MAX_STRINGS);
        return new LiteralString(RandomUtils.randomStringByLength(partial));
    }
}
