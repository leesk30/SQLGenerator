package org.lee.statement.clause;

import org.lee.statement.SQLStatement;
import org.lee.entry.literal.Literal;
import org.lee.entry.literal.LiteralNumber;
import org.lee.node.NodeTag;
import org.lee.type.TypeTag;

import java.util.Iterator;

public abstract class LimitOffset extends Clause<Literal<Number>>{
    protected final Literal<Number> limitNode = new LiteralNumber(TypeTag.int_, 0);
    protected final Literal<Number> offsetNode = new LiteralNumber(TypeTag.int_, 0);
    protected LimitOffset(SQLStatement statement) {
        super(statement, 2);
        children.add(limitNode);
        children.add(offsetNode);
    }

    public int getOffset(){
        return offsetNode.getLiteral().intValue();
    }
    public int getLimit(){
        return limitNode.getLiteral().intValue();
    }

    @Override
    public boolean isEmpty() {
        return children.isEmpty() || (!hasLimit() && !hasOffset());
    }

    public boolean hasLimit(){
        return getLimit() > 0;
    }

    public boolean hasOffset(){
        return getOffset() > 0;
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.limitOffset;
    }


    @Override
    public String getString(){
        // todo: implement represent
        StringBuilder builder = new StringBuilder();
        final boolean hasLimit = hasLimit();
        if(hasLimit){
            builder.append("LIMIT ");
            builder.append(getLimit());
        }
        if(hasOffset()){
            builder.append(hasLimit ? " OFFSET ": "OFFSET ");
            builder.append(getOffset());
        }
        return builder.toString();
    }

    @Override
    public Iterator<Literal<Number>> walk(){
        return children.iterator();
    }
}
