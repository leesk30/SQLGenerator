package org.lee.type.literal.mapped;

import org.lee.type.literal.LiteralDate;
import org.lee.type.TypeTag;
import org.lee.common.util.FuzzUtil;

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
        return new LiteralDate(FuzzUtil.randomDate(partial));
    }
}