package org.lee.type.literal;

import org.lee.type.TypeTag;

import java.sql.Timestamp;

public class LiteralTimestamp extends Literal<Timestamp> implements Inescapable {
    public LiteralTimestamp(Timestamp literalValue) {
        super(TypeTag.timestamp, literalValue);
    }

    @Override
    public String getInescapeString() {
        return String.format("TIMESTAMP'%s'", literalValue.toString());
    }
}
