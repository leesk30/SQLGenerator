package org.lee.fuzzer.qualification;

import org.lee.entry.FieldReference;
import org.lee.entry.literal.Literal;
import org.lee.fuzzer.Generator;
import org.lee.statement.expression.Qualification;
import org.lee.symbol.StaticSymbol;
import org.lee.type.TypeTag;

public abstract class QualificationGenerator implements Generator<Qualification> {
    protected int index = 0;
    abstract public Qualification generate();

    protected Qualification compareToLiteral(FieldReference fieldReference){
        TypeTag typeTag = fieldReference.getType();
        Literal<?> literal = Literal.fromType(typeTag);
        return (Qualification) new Qualification(StaticSymbol.EQUAL_ASSIGN).newChild(fieldReference).newChild(literal);
    }

    protected Qualification compareToRangeLiteral(FieldReference fieldReference){
        TypeTag typeTag = fieldReference.getType();
        Literal<?> b1 = Literal.fromType(typeTag);
        Literal<?> b2 = Literal.fromType(typeTag);
        Literal<?> lhs = ((Comparable) b1.getLiteralString()).compareTo((Comparable) b2.getLiteralString()) > 0 ? b1 : b2;
//      return (Qualification) new Qualification.Builder().setCurrent(StaticSymbol.BETWEEN).addChild(fieldReference).addChild(lhs).addChild(rhs).build();
        return null;
    }

}
