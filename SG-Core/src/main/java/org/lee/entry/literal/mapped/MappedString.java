package org.lee.entry.literal.mapped;

import org.lee.entry.literal.LiteralString;
import org.lee.type.TypeTag;
import org.lee.util.FuzzUtil;

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
        return new LiteralString(FuzzUtil.getRandomName(""));
    }

    @Override
    public LiteralString generate(int partial) {
        return new LiteralString(FuzzUtil.getRandomName(""));
    }
}