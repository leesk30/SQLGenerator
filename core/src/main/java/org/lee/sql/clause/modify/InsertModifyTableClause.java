package org.lee.sql.clause.modify;

import org.lee.sql.entry.relation.Relation;
import org.lee.sql.entry.scalar.Field;
import org.lee.sql.statement.SQLStatement;

import java.util.ArrayList;
import java.util.List;

public class InsertModifyTableClause extends ModifyTableClause{
    protected Relation target;
    protected final List<Field> insertFields = new ArrayList<>();
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
