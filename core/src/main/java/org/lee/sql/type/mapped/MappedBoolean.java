package org.lee.sql.type.mapped;

import org.lee.common.utils.RandomUtils;
import org.lee.sql.literal.LiteralBoolean;
import org.lee.sql.type.TypeTag;

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
        return RandomUtils.probability(50) ? LiteralBoolean.TRUE : LiteralBoolean.FALSE;
    }

    @Override
    public LiteralBoolean generate(int partial) {
        return RandomUtils.probability(50) ? LiteralBoolean.TRUE : LiteralBoolean.FALSE;
    }
}
