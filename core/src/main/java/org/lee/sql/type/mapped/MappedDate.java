package org.lee.sql.type.mapped;

import org.lee.common.utils.RandomUtils;
import org.lee.sql.literal.LiteralDate;
import org.lee.sql.type.TypeTag;

import java.sql.Date;

public class MappedDate extends MappedType<Date> {
    private static final MappedDate self = new MappedDate();
    public static MappedDate getInstance(){
        return self;
    }
    protected MappedDate() {
        super(Date.class, TypeTag.date);
    }

    @Override
    public LiteralDate generate() {
        return generate(0);
    }

    @Override
    public LiteralDate generate(int partial) {
        return new LiteralDate(RandomUtils.randomDate(partial));
    }
}
