package org.lee.entry.literal;

import org.lee.type.TypeTag;

import java.sql.Date;

public class LiteralDate extends Literal<Date> {
    public LiteralDate(Date literalValue) {
        super(TypeTag.date, literalValue);
    }
}
