package org.lee.type.mapped;

import org.lee.common.Utility;
import org.lee.type.TypeTag;
import org.lee.type.literal.LiteralInt;

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
        return new LiteralInt(Utility.randomIntFromRange(0, 100));
    }

    @Override
    public LiteralInt generate(int partial) {
        return new LiteralInt(Utility.randomIntFromRange(partial, 100 + partial));
    }

}
