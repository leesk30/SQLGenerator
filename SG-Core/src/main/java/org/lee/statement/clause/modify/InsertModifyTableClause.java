package org.lee.statement.clause.modify;

import org.lee.entry.relation.Relation;
import org.lee.entry.scalar.Field;
import org.lee.statement.SQLStatement;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class InsertModifyTableClause extends ModifyTableClause{
    protected Relation target;
    protected final List<Field> insertFields = new Vector<>();
    public InsertModifyTableClause(SQLStatement statement) {
        super(statement, 1);
    }

    private Relation findInsertTarget(){
        return null;
    }
    @Override
    public void fuzz() {
        target = findInsertTarget();
        insertFields.addAll(target.getFields());
    }

    @Override
    public String getString() {
        if(insertFields.isEmpty()){
            return concatWithSpace(INSERT, INTO, target.getName());
        }
        return concatWithSpace(INSERT, INTO, target.getName()) + LP + nodeArrayToString(insertFields) + RP;
    }
}
