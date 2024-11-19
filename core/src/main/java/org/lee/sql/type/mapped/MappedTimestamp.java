package org.lee.sql.type.mapped;

import org.lee.common.utils.RandomUtils;
import org.lee.sql.literal.LiteralTimestamp;
import org.lee.sql.type.TypeTag;

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
        return new LiteralTimestamp(RandomUtils.randomTimestamp(partial));
    }
}
