package org.lee.sql.type.mapped;

import org.lee.common.Utility;
import org.lee.sql.literal.LiteralChar;
import org.lee.sql.type.TypeTag;

public class MappedChar extends MappedType<String>{
    private static final MappedChar self = new MappedChar();
    public static MappedChar getInstance(){
        return self;
    }

    protected MappedChar() {
        super(String.class, TypeTag.string);
    }

    @Override
    public LiteralChar generate() {
        return new LiteralChar(Utility.randomStringByLength(5));
    }

    @Override
    public LiteralChar generate(int partial) {
        return new LiteralChar(Utility.randomStringByLength(partial));
    }
}