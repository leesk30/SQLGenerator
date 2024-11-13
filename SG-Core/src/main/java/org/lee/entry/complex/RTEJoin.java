package org.lee.entry.complex;

import org.apache.commons.lang3.StringUtils;
import org.lee.common.Utility;
import org.lee.entry.FieldReference;
import org.lee.entry.RangeTableReference;
import org.lee.entry.relation.RangeTableEntry;
import org.lee.entry.scalar.Field;
import org.lee.statement.clause.predicate.JoinClause;
import org.lee.statement.support.SQLStatement;

import java.util.ArrayList;
import java.util.List;

public class RTEJoin extends JoinClause implements RangeTableEntry {
    private final List<Field> fieldList;  // lazy
    private final List<FieldReference> fieldReferenceList; // not lazy

    public RTEJoin(SQLStatement statement, RangeTableReference left, RangeTableReference right){
        super(statement, left, right);
        int capacity = this.left.getWrapped().capacity() + this.right.getWrapped().capacity();
        this.fieldList = new ArrayList<>(capacity);
        this.fieldReferenceList = new ArrayList<>(capacity);
        fieldReferenceList.addAll(left.getFieldReferences());
        fieldReferenceList.addAll(right.getFieldReferences());
    }

    @Override
    public List<Field> getFields() {
        if(fieldList.isEmpty()){
            fieldReferenceList.forEach(fieldReference -> fieldList.add(fieldReference.toField()));
        }
        return fieldList;
    }

    public List<FieldReference> getFieldReferences(){
        return fieldReferenceList;
    }

    @Override
    public String getString() {
        String join = StringUtils.joinWith(" ", left.getString(), pattern, JOIN, right.getString());
        if(filter.isEmpty()){
            return join;
        }
        return StringUtils.joinWith(" ", join, ON, filter.getString());
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public void fuzz() {
        pattern = Utility.randomlyChooseFrom(Pattern.values());
        super.fuzz();
    }
}
