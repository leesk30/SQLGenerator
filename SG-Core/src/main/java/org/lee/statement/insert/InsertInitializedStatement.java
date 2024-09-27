package org.lee.statement.insert;

import org.lee.entry.complex.TargetEntry;
import org.lee.entry.record.Record;
import org.lee.entry.relation.Relation;
import org.lee.entry.scalar.Field;
import org.lee.statement.clause.modify.InsertModifyTableClause;
import org.lee.statement.clause.project.ValuesClauseForInsert;
import org.lee.type.TypeDescriptor;
import org.lee.common.util.FuzzUtil;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
            IntStream.range(0, length).sequential().forEach(
                    i -> {
                        final Record record = new Record(width);
                        typeDescriptors.forEach(td -> record.add(td.generate()));
                        children.add(record);
                    }
            );
            children.get(0).forEach(l -> projection.add(TargetEntry.newNamedEntry(l.getType())));
        }

        @Override
        public void fuzz() {
            length = FuzzUtil.randomIntFromRange(minLines, maxLines);
            width = InsertInitializedStatement.this.targetRelation.getFields().size();
            generateRecordByTypeDescriptor();
        }
    }

    @Override
    public void fuzz() {
        // do nothing
    }
}
