package org.lee.sql.type.literal;

import org.lee.sql.type.TypeTag;

import java.sql.Date;

public class LiteralDate extends Literal<Date> implements Inescapable{
    public LiteralDate(Date literalValue) {
        super(TypeTag.date, literalValue);
    }

    @Override
    public String getInescapeString() {
        return String.format("DATE'%s'", literalValue.toString());
    }
}
