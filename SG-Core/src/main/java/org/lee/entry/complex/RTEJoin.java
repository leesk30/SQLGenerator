package org.lee.entry.complex;

import org.apache.commons.lang3.StringUtils;
import org.lee.fuzzer.Generator;
import org.lee.fuzzer.expr.JoinerQualificationGenerator;
import org.lee.statement.SQLStatement;
import org.lee.entry.RangeTableReference;
import org.lee.entry.scalar.Field;
import org.lee.statement.expression.Qualification;
import org.lee.node.NodeTag;
import org.lee.entry.relation.RangeTableEntry;
import org.lee.statement.clause.from.JoinClause;

import java.util.List;
import java.util.Vector;

public class RTEJoin extends JoinClause implements RangeTableEntry {
    private final RangeTableReference left;
    private final RangeTableReference right;
    private final Filter filter = new Filter();
    private final Pattern pattern = getRandomJoinPattern();
    private final List<Field> fieldList;  // lazy

    public RTEJoin(SQLStatement statement, RangeTableReference left, RangeTableReference right){
        super(statement);
        this.left = left;
        this.right = right;
        this.children.add(left);
        this.children.add(right);
        this.fieldList = new Vector<>(left.getFieldReferences().size() + right.getFieldReferences().size());
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
        Generator<Qualification> generator = new JoinerQualificationGenerator(left, right);
        filter.put(generator.generate());
        filter.fuzz();
    }
}
