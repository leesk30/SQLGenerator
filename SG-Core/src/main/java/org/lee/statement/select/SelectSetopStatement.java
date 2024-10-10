package org.lee.statement.select;

import org.lee.common.Assertion;
import org.lee.common.Utility;
import org.lee.common.exception.ValueCheckFailedException;
import org.lee.entry.complex.TargetEntry;
import org.lee.entry.relation.CTE;
import org.lee.entry.relation.RangeTableEntry;
import org.lee.statement.ValuesStatement;
import org.lee.statement.clause.from.WithClause;
import org.lee.statement.clause.limit.LimitOffset;
import org.lee.statement.clause.limit.SelectLimitOffset;
import org.lee.statement.clause.sort.SelectOrderByClause;
import org.lee.statement.clause.sort.SortByClause;
import org.lee.statement.generator.ProjectableGenerator;
import org.lee.statement.support.Projectable;
import org.lee.statement.support.SQLStatement;
import org.lee.statement.support.Sortable;
import org.lee.statement.support.SupportCommonTableExpression;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class SelectSetopStatement
        extends SelectStatement
        implements Sortable, SupportCommonTableExpression {
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
            return projectable.getString() + SPACE + ((ValuesStatement) projectable).toPrintPrettyNamedField(name);
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

    @Override
    public List<TargetEntry> project() {
        return left.project();
    }

    private void requireProjectionLeftSimilarWithRight(){
        if(left.project().size() != right.project().size()){
            String message = "The setop statement has difference project length. " +
                    "Left: " + left.project().size() + " Right: " + right.project().size();

            logger.error(message);
            logger.debug(String.format("Left class name: %s Right class name: %s", left.getClass().getName(), right.getClass().getName()));
            throw new ValueCheckFailedException(message);
        }
        for(int i = 0; i < left.project().size(); i++){
            if(left.project().get(i).getType() != right.project().get(i).getType()){
                String message = "The setop statement is mismatched type in both size. " +
                        "Left type: " + left.project().get(i).getType() +
                        " Right type: "+ right.project().get(i).getType() +" Index: " + i;
                logger.error(message);
                logger.debug(String.format("Left class name: %s Right class name: %s", left.getClass().getName(), right.getClass().getName()));
                throw new ValueCheckFailedException(message);
            }
        }
    }

    private void generateChildStatement(){
        final ProjectableGenerator generator = new ProjectableGenerator(this);
        if(this.getProjectTypeLimitation().isEmpty()){
            if(Utility.probability(50)){
                left = generator.generate();
                right = generator.generate(left.project().stream().map(TargetEntry::getType).collect(Collectors.toList()));
            }else {
                right = generator.generate();
                left = generator.generate(right.project().stream().map(TargetEntry::getType).collect(Collectors.toList()));
            }
            // When parent required statement withTypeLimitation shelled statement should tell its children the limitations.
        }else {
            left = generator.generate(this.getProjectTypeLimitation());
            right = generator.generate(this.getProjectTypeLimitation());
        }
        requireProjectionLeftSimilarWithRight();
    }

    @Override
    public void fuzz() {
        withClause.fuzz();
        generateChildStatement();
        setop = Utility.randomlyChooseFrom(SetOperation.values());
        all = Utility.probability(50);
        sortByClause.fuzz();
        limitOffset.fuzz();
    }

    @Override
    public boolean isScalar() {
        return !limitOffset.isEmpty() && width() == 1 && limitOffset.getLimit() == 1;
    }

    @Override
    public List<RangeTableEntry> getRawRTEList() {
        Assertion.requiredNonNull(left);
        Assertion.requiredNonNull(right);

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
