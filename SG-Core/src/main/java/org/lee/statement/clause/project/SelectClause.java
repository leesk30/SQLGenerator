package org.lee.statement.clause.project;

import org.lee.common.SGException;
import org.lee.entry.scalar.Scalar;
import org.lee.fuzzer.Generator;
import org.lee.fuzzer.expr.GeneralExpressionGenerator;
import org.lee.rules.ConstRule;
import org.lee.rules.RuleName;
import org.lee.entry.FieldReference;
import org.lee.entry.RangeTableReference;
import org.lee.entry.complex.TargetEntry;
import org.lee.node.NodeTag;
import org.lee.statement.clause.Clause;
import org.lee.statement.clause.from.FromClause;
import org.lee.statement.expression.Expression;
import org.lee.statement.select.AbstractSimpleSelectStatement;
import org.lee.statement.select.SelectStatement;
import org.lee.statement.select.SelectType;
import org.lee.type.TypeTag;
import org.lee.util.ListUtil;
import org.lee.util.FuzzUtil;

import java.util.*;
import java.util.stream.IntStream;

public abstract class SelectClause extends Clause<TargetEntry> {
    public SelectClause(SelectStatement statement) {
        super(statement);
    }

    @Override
    public String getString() {
        return SELECT + SPACE + this.nodeArrayToString(children);
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.selectClause;
    }

    protected void processEntry(Scalar scalar){
        final Expression expression = scalar.toExpression();
        if(expression.isIncludingAggregation() && !statement.confirmByRuleName(RuleName.AGGREGATION_REQUIRED_GROUP_BY)){
            synchronized (statement.getRuleSet()){
                statement.getRuleSet().put(new ConstRule(RuleName.AGGREGATION_REQUIRED_GROUP_BY, true));
            }
        }
        TargetEntry entry = new TargetEntry(expression);
        entry.setAlias();
        children.add(entry);
    }

}
