package org.lee.type.literal.mapped;

import org.lee.common.Utility;
import org.lee.type.TypeTag;
import org.lee.type.literal.LiteralString;

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
        return new LiteralString(Utility.randomStringByLength(10));
    }

    @Override
    public LiteralString generate(int partial) {
        partial = Math.min(partial, Utility.ACTUALLY_MAX_STRINGS);
        return new LiteralString(Utility.randomStringByLength(partial));
    }
}
