package org.lee.entry.literal.mapped;

import org.lee.entry.literal.LiteralTimestamp;
import org.lee.type.TypeTag;

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
        return null;
    }

    @Override
    public LiteralTimestamp generate(int partial) {
        return null;
    }
}
