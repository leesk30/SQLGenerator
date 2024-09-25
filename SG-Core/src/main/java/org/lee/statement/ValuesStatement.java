package org.lee.statement;

import org.lee.entry.complex.TargetEntry;
import org.lee.entry.relation.RangeTableEntry;
import org.lee.entry.relation.ValuesRelation;
import org.lee.node.Node;
import org.lee.node.NodeTag;
import org.lee.statement.clause.limit.LimitOffset;
import org.lee.statement.clause.limit.ValuesLimitOffset;
import org.lee.statement.clause.project.ValuesClause;
import org.lee.statement.clause.project.ValuesClauseForValues;
import org.lee.statement.clause.sort.SortByClause;
import org.lee.statement.clause.sort.ValuesOrderByClause;
import org.lee.statement.support.Projectable;
import org.lee.statement.support.Sortable;
import org.lee.type.TypeTag;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ValuesStatement extends SQLStatement implements Projectable, Sortable {
    protected final boolean withLogicalParentheses;
    protected final ValuesClause valuesClause = new ValuesClauseForValues(this);
    protected final SortByClause sortByClause = new ValuesOrderByClause(this);
    protected final LimitOffset limitOffset = new ValuesLimitOffset(this);
    protected final List<TypeTag> limitations = new Vector<>();

    public ValuesStatement() {
        this(null);
    }

    public ValuesStatement(SQLStatement parentStatement) {
        super(SQLType.values, parentStatement);
        withLogicalParentheses = (parentStatement != null && parentStatement.getSqlType() == SQLType.select);
        addClause(valuesClause);
        addClause(sortByClause);
        addClause(limitOffset);
    }

    public static ValuesStatement newStatement(){
        return new ValuesStatement();
    }

    @Override
    public boolean isWithLogicalParentheses() {
        return withLogicalParentheses;
    }

    @Override
    public void fuzz() {
        valuesClause.fuzz();
        sortByClause.fuzz();
        limitOffset.fuzz();
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.statement;
    }

    @Override
    public List<? extends Node> getChildNodes() {
        return new ArrayList<>(childrenMap.values());
    }

    @Override
    public List<TargetEntry> project() {
        return valuesClause.getProjection();
    }

    @Override
    public RangeTableEntry toRelation() {
        return new ValuesRelation(this);
    }

    @Override
    public boolean isScalar() {
        if(valuesClause.getWidth() != 1){
            return false;
        }
        return valuesClause.getLength() == 1 || limitOffset.getLimit() == 1;
    }

    @Override
    public void withProjectTypeLimitation(List<TypeTag> limitation) {
        if(limitations.isEmpty()){
            limitations.addAll(limitation);
        }else {
            throw new RuntimeException("Duplicate type limitations");
        }
    }

    @Override
    public List<TypeTag> getProjectTypeLimitation() {
        return limitations;
    }

    @Override
    public SortByClause getSortByClause() {
        return sortByClause;
    }

    @Override
    public LimitOffset getLimitOffset() {
        return limitOffset;
    }

    public ValuesClause getValuesClause() {
        return valuesClause;
    }

    public String toPrintPrettyNamedField(String name){
        return name + LP + nodeArrayToString(this.project()) + RP;
    }
}
