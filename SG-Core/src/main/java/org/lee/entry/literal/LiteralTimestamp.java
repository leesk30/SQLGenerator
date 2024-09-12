package org.lee.entry.literal;

import org.lee.type.TypeTag;

import java.sql.Timestamp;

public class LiteralTimestamp extends Literal<Timestamp> {
    public LiteralTimestamp(Timestamp literalValue) {
        super(TypeTag.timestamp, literalValue);
    }
}
