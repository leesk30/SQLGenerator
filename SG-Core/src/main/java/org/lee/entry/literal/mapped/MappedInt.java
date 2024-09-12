package org.lee.entry.literal.mapped;

import org.lee.entry.literal.LiteralInt;
import org.lee.type.TypeTag;
import org.lee.util.FuzzUtil;

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
        return new LiteralInt(FuzzUtil.randomIntFromRange(0, 100));
    }

    @Override
    public LiteralInt generate(int partial) {
        return new LiteralInt(FuzzUtil.randomIntFromRange(partial, 100 + partial));
    }

}
