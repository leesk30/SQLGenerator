package org.lee.type.literal.mapped;

import org.lee.type.literal.LiteralBoolean;
import org.lee.type.TypeTag;
import org.lee.common.util.FuzzUtil;

public class MappedBoolean extends MappedType<Boolean>{
    private static final MappedBoolean self = new MappedBoolean();
    public static MappedBoolean getInstance(){
        return self;
    }
    protected MappedBoolean() {
        super(Boolean.class, TypeTag.boolean_);
    }

    @Override
    public LiteralBoolean generate() {
        return FuzzUtil.probability(50) ? LiteralBoolean.TRUE : LiteralBoolean.FALSE;
    }

    @Override
    public LiteralBoolean generate(int partial) {
        return FuzzUtil.probability(50) ? LiteralBoolean.TRUE : LiteralBoolean.FALSE;
    }
}
