package org.lee.entry.complex;

import org.apache.commons.lang3.StringUtils;
import org.lee.base.Generator;
import org.lee.base.NodeTag;
import org.lee.entry.RangeTableReference;
import org.lee.entry.relation.RangeTableEntry;
import org.lee.entry.scalar.Field;
import org.lee.statement.clause.from.JoinClause;
import org.lee.statement.expression.Qualification;
import org.lee.statement.expression.generator.JoinQualificationGenerator;
import org.lee.statement.support.SQLStatement;

import java.util.ArrayList;
import java.util.List;

public class RTEJoin extends JoinClause implements RangeTableEntry {
    private final RangeTableReference left;
    private final RangeTableReference right;
    private final Filter filter = new Filter();
    private final List<Field> fieldList;  // lazy

    public RTEJoin(SQLStatement statement, RangeTableReference left, RangeTableReference right){
        super(statement);
        this.left = left;
        this.right = right;
        this.children.add(left);
        this.children.add(right);
        this.fieldList = new ArrayList<>(left.getFieldReferences().size() + right.getFieldReferences().size());
    }

    @Override
    public String getString() {
        return StringUtils.joinWith(" ", left.getString(), pattern.toString(), "JOIN", right.getString(), "ON", filter.getString());
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.joinClause;
    }

    @Override
    public List<Field> getFields() {
        if(fieldList.isEmpty()){
            left.getFieldReferences().forEach(fieldReference -> fieldList.add(fieldReference.toField()));
            right.getFieldReferences().forEach(fieldReference -> fieldList.add(fieldReference.toField()));
        }
        return fieldList;
    }

    @Override
    public String getName() {
        return null;
    }

    public RangeTableReference getLeft() {
        return left;
    }

    public RangeTableReference getRight(){
        return right;
    }

    @Override
    public void fuzz() {
        Generator<Qualification> generator = new JoinQualificationGenerator(this.statement, left, right);
        filter.put(generator.generate());
        filter.fuzz();
    }
}
