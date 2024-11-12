package org.lee.type.mapped;

import org.lee.type.TypeTag;
import org.lee.type.literal.Literal;
import org.lee.type.literal.LiteralNull;

public class MappedNull extends MappedType<NullType>{
    private static final MappedNull self = new MappedNull();
    public static MappedNull getInstance(){
        return self;
    }

    protected MappedNull() {
        super(NullType.class, TypeTag.null_);
    }

    @Override
    public LiteralNull generate() {
        return Literal.asNull();
    }

    @Override
    public LiteralNull generate(int partial) {
        return Literal.asNull();
    }

    @Override
    public TypeTag getTypeTag() {
        return TypeTag.null_;
    }
}
