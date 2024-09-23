package org.lee.statement.select;

import org.lee.common.DevTempConf;
import org.lee.entry.complex.TargetEntry;
import org.lee.entry.relation.CTE;
import org.lee.statement.SQLStatement;
import org.lee.statement.ValuesStatement;
import org.lee.statement.clause.from.WithClause;
import org.lee.statement.clause.limit.LimitOffset;
import org.lee.statement.clause.limit.SelectLimitOffset;
import org.lee.statement.clause.sort.SelectOrderByClause;
import org.lee.statement.clause.sort.SortByClause;
import org.lee.statement.support.Projectable;
import org.lee.entry.relation.RangeTableEntry;
import org.lee.statement.support.Sortable;
import org.lee.statement.support.SupportCommonTableExpression;
import org.lee.statement.support.SupportGenerateProjectable;
import org.lee.util.FuzzUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class SelectSetopStatement
        extends SelectStatement
        implements Sortable, SupportCommonTableExpression, SupportGenerateProjectable {
    private Projectable left;
    private Projectable right;
    private SetOperation setop;
    private boolean all;

    public enum SetOperation {
        UNION,
        INTERSECT,
        MINUS,
        EXCEPT,
        ;
    }

    private final WithClause withClause = new WithClause(this);
    private final SortByClause sortByClause = new SelectOrderByClause(this);
    private final LimitOffset limitOffset = new SelectLimitOffset(this);


    public SelectSetopStatement() {
        super(SelectType.setop);
    }

    public SelectSetopStatement(SQLStatement parent) {
        super(SelectType.setop, parent);
    }

    private String wrappedStatementToPretty(String name, Projectable projectable){
        if(projectable instanceof ValuesStatement){
            // notice: cannot use 'values as v1(c1, c2)' here, use 'values v1(c1, c2)' instead.
            return projectable.getString() + SPACE + name + LP + ((ValuesStatement) left).toPrintPrettyNamedField() + RP;
        }else {
            return projectable.getString();
        }
    }

    private String setOperationToString(){
        return setop + (all ? SPACE + ALL : SPACE + EMPTY);
    }


    @Override
    public String body(){
        return wrappedStatementToPretty("v_left", left) + NEWLINE +
                setOperationToString() + NEWLINE +
                wrappedStatementToPretty("v_right", right);
    }

    private SelectType getSubSelectType(){
        SelectType[] candidate;
        if(setopDepth < DevTempConf.MAX_SETOP_RECURSION_DEPTH){
            candidate = new SelectType[]{SelectType.simple, SelectType.normal, SelectType.setop};
        }else {
            candidate = new SelectType[]{SelectType.simple, SelectType.normal};
        }
        return FuzzUtil.randomlyChooseFrom(candidate);
    }

    @Override
    public List<TargetEntry> project() {
        return left.project();
    }

    private void requireProjectionLeftSimilarWithRight(){
        if(left.project().size() != right.project().size()){
            throw new RuntimeException("The setop statement has difference project length. " +
                    "Left: " + left.project().size() + " Right: " + right.project().size());
        }
        IntStream.range(0, left.project().size()).parallel().forEach(
                i -> {
                    if(left.project().get(i).getType() != right.project().get(i).getType()){
                        throw new RuntimeException("The setop statement is mismatched type in both size. " +
                                "Left type: " + left.project().get(i).getType() +
                                " Right type: "+ right.project().get(i).getType() +" Index: " + i);
                    }
                }
        );
    }

    @Override
    public void fuzz() {
        withClause.fuzz();
        // with parent generate
        left = generate(this);
        right = generate(this, left.project().stream().map(TargetEntry::getType).collect(Collectors.toList()));
        requireProjectionLeftSimilarWithRight();
        setop = FuzzUtil.randomlyChooseFrom(SetOperation.values());
        all = FuzzUtil.probability(50);
        sortByClause.fuzz();
        limitOffset.fuzz();
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

    @Override
    public WithClause getWithClause() {
        return withClause;
    }
}
