package org.lee.entry.literal.mapped;

import org.lee.entry.literal.LiteralDate;
import org.lee.type.TypeTag;

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
        return null;
    }

    @Override
    public LiteralDate generate(int partial) {
        return null;
    }
}
