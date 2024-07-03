package org.lee.statement.expression;

import com.sun.istack.internal.Nullable;
import org.apache.commons.lang3.StringUtils;
import org.lee.statement.node.NodeTag;
import org.lee.statement.node.AliasNameable;
import org.lee.statement.node.Node;
import org.lee.type.TypeTag;

import java.util.ArrayList;
import java.util.List;

public class Expression implements IExpression, AliasNameable {

    private final Node current;
    private final List<Expression> childNodes;
    private String alias;

    protected Expression(Node current, List<Expression> childNodes){
        this.current = current;
        this.childNodes = childNodes;
        this.alias = null;
    }

    public Expression newLeafExpr(Node current){
        return new Expression(current, null);
    }

    private static class Builder extends ExpressionBuilder {
        @Override
        Expression build() {
            List<Expression> childNodes = new ArrayList<>();
            for(ExpressionBuilder builder: childExprBuilders){
                childNodes.add((Expression)builder.build());
            }
            return new Expression(this.currentNode, childNodes);
        }
    }

    @Nullable
    @Override
    public List<Expression> getChildNodes() {
        return childNodes;
    }

    @Override
    public boolean hasAlias() {
        return alias != null && !StringUtils.isEmpty(alias) && !StringUtils.isBlank(alias);
    }

    @Override
    public String getAlias() {
        return null;
    }

    @Override
    public void setAlias() {
        // todo rename
    }

    @Override
    public String getString() {
        return null;
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.expression;
    }

    @Override
    public TypeTag getType() {
        return null;
    }

    @Override
    public ExpressionBuilder newBuilder() {
        return new Builder();
    }

    @Override
    public Node getCurrentNode() {
        return current;
    }

    @Override
    public IExpression safeShallowCopy() {
        return newBuilder().setCurrent(this).build();
    }

}
