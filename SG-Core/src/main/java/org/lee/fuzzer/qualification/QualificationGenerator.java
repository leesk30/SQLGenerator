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
        return new Qualification(StaticSymbol.EQUALS).newChild(fieldReference).newChild(literal);
    }

    protected Qualification compareToRangeLiteral(FieldReference fieldReference){
        TypeTag typeTag = fieldReference.getType();
        Literal<?> b1 = Literal.fromType(typeTag);
        Literal<?> b2 = Literal.fromType(typeTag);
        Literal<?> lhs;
        Literal<?> rhs;
        if(!(b1.getLiteral() instanceof Comparable)){
            // fallback to
            return compareToLiteral(fieldReference);
        }
        if(((Comparable) b1.getLiteral()).compareTo(b2.getLiteral()) > 0){
            lhs = b2;
            rhs = b1;
        }else {
            lhs = b1;
            rhs = b2;
        }
        return new Qualification(StaticSymbol.BETWEEN).newChild(fieldReference).newChild(lhs).newChild(rhs);
    }

}
