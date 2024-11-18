package org.lee.sql.statement.insert;

import org.lee.common.utils.RandomUtils;
import org.lee.sql.clause.modify.InsertModifyTableClause;
import org.lee.sql.clause.project.ValuesClauseForInsert;
import org.lee.sql.entry.complex.Record;
import org.lee.sql.entry.complex.TargetEntry;
import org.lee.sql.entry.relation.Relation;
import org.lee.sql.entry.scalar.Field;
import org.lee.sql.type.TypeDescriptor;

import java.util.List;
import java.util.stream.Collectors;

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

        private void generateRecordByTypeDescriptor(){
            final List<TypeDescriptor> typeDescriptors = InsertInitializedStatement.this.targetRelation.getFields().stream().map(Field::getDescriptor).collect(Collectors.toList());
            requiredReadyToGenerateRecord(typeDescriptors.size());
            for(int i=0; i<length; i++){
                final Record record = new Record(width);
                typeDescriptors.forEach(td -> record.add(td.generate()));
                children.add(record);
                if(i == 0){
                    record.forEach(l -> projection.add(TargetEntry.newNamedEntry(l.getType())));
                }
            }
        }

        @Override
        public void fuzz() {
            length = RandomUtils.randomIntFromRange(minLines, maxLines);
            width = InsertInitializedStatement.this.targetRelation.getFields().size();
            generateRecordByTypeDescriptor();
        }
    }

    @Override
    public void fuzz() {
        // do nothing
    }
}
