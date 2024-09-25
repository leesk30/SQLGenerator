package org.lee.statement.insert;

import org.lee.entry.relation.Relation;
import org.lee.statement.clause.modify.InsertModifyTableClause;
import org.lee.statement.clause.project.ValuesClauseForInsert;
import org.lee.util.FuzzUtil;

import java.util.ArrayList;
import java.util.Vector;

public final class InsertInitializedStatement extends InsertStatement{
    private final Relation targetRelation;
    public InsertInitializedStatement(Relation relation, int insertMaxCapacity) {
        super();
        this.targetRelation = relation;
        addClause(new StableInsertModifyTable());
        addClause(new StableInsertValuesClause(insertMaxCapacity));
    }

    private final class StableInsertModifyTable extends InsertModifyTableClause {
        public StableInsertModifyTable() {
            super(InsertInitializedStatement.this);
            this.target = InsertInitializedStatement.this.targetRelation;
            this.children.add(target);
            fuzz();
        }
        @Override
        public void fuzz() { insertFields.addAll(this.target.getFields()); }
    }

    private final class StableInsertValuesClause extends ValuesClauseForInsert{

        public StableInsertValuesClause(int maxLines) {
            super(InsertInitializedStatement.this, maxLines);
            fuzz();
        }
        @Override
        public void fuzz() {
            length = FuzzUtil.randomIntFromRange(minLines, maxLines);
            width = InsertInitializedStatement.this.targetRelation.getFields().size();
            generateRecord(getTargetTypeByWidth());
        }
    }

    @Override
    public void fuzz() {
        // do nothing
    }
}
