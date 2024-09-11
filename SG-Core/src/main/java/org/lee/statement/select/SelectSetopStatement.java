package org.lee.statement.select;

import org.lee.entry.complex.TargetEntry;
import org.lee.entry.relation.CTE;
import org.lee.statement.SQLStatement;
import org.lee.statement.clause.*;
import org.lee.statement.support.Projectable;
import org.lee.entry.relation.RangeTableEntry;
import org.lee.entry.scalar.Field;
import org.lee.statement.support.Sortable;
import org.lee.statement.support.SupportCommonTableExpression;

import java.util.ArrayList;
import java.util.List;

public final class SelectSetopStatement extends SelectStatement implements Sortable, SupportCommonTableExpression {
    private Projectable left;
    private Projectable right;
    private SetOperation setop;
    private boolean all;
    private final WithClause withClause = new WithClause(this);
    private final SortByClause sortByClause = new SelectOrderByClause(this);
    private final LimitOffset limitOffset = new SelectLimitOffset(this);


    public SelectSetopStatement() {
        super(SelectType.setop);
    }

    public SelectSetopStatement(SQLStatement parent) {
        super(SelectType.setop, parent);
    }

    @Override
    public String getString() {
        return left.getString() + "\n" + setop.toString(all, context.getParameter().syntaxMode) + "\n" + right.getString();
    }

    @Override
    public List<TargetEntry> project() {
        return left.project();
    }

    @Override
    public void fuzz() {
        if(left instanceof AbstractNormalSelectStatement){
            withClause.fuzz();
        }

    }

    @Override
    public boolean isScalar() {
        return !limitOffset.isEmpty() && width() == 1 && limitOffset.getLimit() == 1;
    }

    @Override
    public List<RangeTableEntry> getRawRTEList() {
        assert left != null && right != null;
        List<RangeTableEntry> rawRTEList = new ArrayList<>();
        if(left instanceof SelectStatement){
            rawRTEList.addAll(((SelectStatement) left).getRawRTEList());
        }
        if(right instanceof SelectStatement){
            rawRTEList.addAll(((SelectStatement) right).getRawRTEList());
        }
        return rawRTEList;
    }

    @Override
    public SortByClause getSortByClause() {
        return sortByClause;
    }

    @Override
    public LimitOffset getLimitOffset() {
        return limitOffset;
    }

    @Override
    public List<CTE> getCTEs() {
        return withClause.getChildNodes();
    }
}
