package org.lee.statement.clause.project;

import org.lee.common.Utility;
import org.lee.entry.complex.TargetEntry;
import org.lee.entry.record.Record;
import org.lee.common.Assertion;
import org.lee.base.NodeTag;
import org.lee.statement.SQLStatement;
import org.lee.statement.clause.Clause;
import org.lee.type.TypeTag;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class ValuesClause extends Clause<Record> {
    protected int length;
    protected int width;
    protected final List<TargetEntry> projection = new ArrayList<>();

    protected ValuesClause(SQLStatement statement) {
        super(statement);
    }

    @Override
    public String getString() {
        return VALUES + nodeArrayToString(COMMA, children);
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.valuesClause;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public List<TargetEntry> getProjection() {
        return projection;
    }

    protected List<TypeTag> getTargetTypeByWidth(){
        Assertion.requiredTrue(width > 0);
        return IntStream.range(0, width)
                .sequential()
                .mapToObj(
                        i -> Utility.randomlyChooseFrom(TypeTag.GENERATE_PREFER_CHOOSE))
                .collect(Collectors.toList());
    }

    protected void requiredReadyToGenerateRecord(final int targetWidth){
        Assertion.requiredTrue(length > 0);
        Assertion.requiredTrue(width> 0);
        Assertion.requiredEquals(targetWidth, width);
    }

    protected void generateRecord(final List<TypeTag> targetType){
        requiredReadyToGenerateRecord(targetType.size());

        for(int i=0; i < length; i++){
            final Record record = new Record(width);
            targetType.forEach(t -> record.add(t.asMapped().generate()));
            children.add(record);
        }
        children.get(0).forEach(l -> projection.add(TargetEntry.newNamedEntry(l.getType())));
    }
}
