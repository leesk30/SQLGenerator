package org.lee.statement.clause.project;

import org.lee.entry.complex.TargetEntry;
import org.lee.entry.record.Record;
import org.lee.exception.Assertion;
import org.lee.node.NodeTag;
import org.lee.statement.SQLStatement;
import org.lee.statement.clause.Clause;
import org.lee.type.TypeTag;
import org.lee.util.FuzzUtil;

import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class ValuesClause extends Clause<Record> {
    protected int length;
    protected int width;
    protected final List<TargetEntry> projection = new Vector<>();

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
                .parallel()
                .mapToObj(
                        i -> FuzzUtil.randomlyChooseFrom(TypeTag.GENERATE_PREFER_CHOOSE))
                .collect(Collectors.toList());
    }

    protected void requiredReadyToGenerateRecord(final int targetWidth){
        Assertion.requiredTrue(length > 0);
        Assertion.requiredTrue(width> 0);
        Assertion.requireEquals(targetWidth, width);
    }

    protected void generateRecord(final List<TypeTag> targetType){
        requiredReadyToGenerateRecord(targetType.size());

        IntStream.range(0, length).parallel().forEach(
                i -> {
                    final Record record = new Record(width);
                    targetType.forEach(t -> record.add(t.asMapped().generate()));
                    children.add(record);
                }
        );
        children.get(0).stream().parallel().forEachOrdered(l -> projection.add(TargetEntry.newNamedEntry(l.getType())));
    }
}
