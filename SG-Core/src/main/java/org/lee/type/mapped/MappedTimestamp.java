package org.lee.type.literal.mapped;

import org.lee.common.Utility;
import org.lee.type.TypeTag;
import org.lee.type.literal.LiteralTimestamp;

import java.sql.Timestamp;

public final class MappedTimestamp extends MappedType<Timestamp> {

    private static final MappedTimestamp self = new MappedTimestamp();
    public static MappedTimestamp getInstance(){
        return self;
    }

    private MappedTimestamp() {
        super(Timestamp.class, TypeTag.timestamp);
    }

    @Override
    public LiteralTimestamp generate() {
        return generate(0);
    }

    @Override
    public LiteralTimestamp generate(int partial) {
        return new LiteralTimestamp(Utility.randomTimestamp(partial));
    }
}
