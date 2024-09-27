package org.lee.type.literal.mapped;

import org.lee.type.literal.LiteralString;
import org.lee.type.TypeTag;
import org.lee.common.util.FuzzUtil;

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
        return new LiteralString(FuzzUtil.randomStringByLength(10));
    }

    @Override
    public LiteralString generate(int partial) {
        partial = Math.min(partial, FuzzUtil.ACTUALLY_MAX_STRINGS);
        return new LiteralString(FuzzUtil.randomStringByLength(partial));
    }
}
