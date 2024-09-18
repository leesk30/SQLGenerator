package org.lee.statement.clause.limit;

import org.lee.entry.literal.LiteralInt;
import org.lee.statement.SQLStatement;
import org.lee.entry.literal.Literal;
import org.lee.entry.literal.LiteralNumber;
import org.lee.node.NodeTag;
import org.lee.statement.clause.Clause;
import org.lee.type.TypeTag;

import java.util.Iterator;
import java.util.stream.Stream;

public abstract class LimitOffset extends Clause<Literal<Integer>> {
    protected final Literal<Integer> limitNode = new LiteralInt(0);
    protected final Literal<Integer> offsetNode = new LiteralInt(0);
    protected LimitOffset(SQLStatement statement) {
        super(statement, 2);
        children.add(limitNode);
        children.add(offsetNode);
    }

    public int getOffset(){
        return offsetNode.asJava();
    }
    public int getLimit(){
        return limitNode.asJava();
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
            builder.append(LIMIT);
            builder.append(SPACE);
            builder.append(getLimit());
        }
        if(hasOffset()){
            builder.append(hasLimit ? SPACE + OFFSET + SPACE: OFFSET + SPACE);
            builder.append(getOffset());
        }
        return builder.toString();
    }
}
