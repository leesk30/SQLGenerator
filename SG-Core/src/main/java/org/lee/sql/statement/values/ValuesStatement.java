package org.lee.sql.statement;

import org.lee.base.Node;
import org.lee.base.NodeTag;
import org.lee.sql.entry.complex.TargetEntry;
import org.lee.sql.entry.relation.RangeTableEntry;
import org.lee.sql.entry.relation.ValuesRelation;
import org.lee.sql.statement.common.SQLType;
import org.lee.sql.clause.Clause;
import org.lee.sql.clause.limit.LimitOffset;
import org.lee.sql.clause.limit.ValuesLimitOffset;
import org.lee.sql.clause.project.ValuesClause;
import org.lee.sql.clause.project.ValuesClauseForValues;
import org.lee.sql.clause.sort.SortByClause;
import org.lee.sql.clause.sort.ValuesOrderByClause;
import org.lee.sql.support.Projectable;
import org.lee.sql.support.Sortable;
import org.lee.sql.type.TypeTag;

import java.util.ArrayList;
import java.util.List;

public class ValuesStatement extends AbstractSQLStatement implements Projectable, Sortable {
    protected final boolean withLogicalParentheses;
    protected final ValuesClause valuesClause = new ValuesClauseForValues(this);
    protected final SortByClause sortByClause = new ValuesOrderByClause(this);
    protected final LimitOffset limitOffset = new ValuesLimitOffset(this);
    protected final List<TypeTag> limitations = new ArrayList<>();

    public ValuesStatement() {
        this(null);
    }

    public ValuesStatement(SQLStatement parentStatement) {
        super(SQLType.values, parentStatement);
        withLogicalParentheses = (parentStatement != null && parentStatement.getSQLType() == SQLType.select);
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
    public List<Clause<? extends Node>> getChildNodes() {
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
