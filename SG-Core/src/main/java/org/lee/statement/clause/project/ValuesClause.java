package org.lee.statement.clause.project;

import org.lee.entry.complex.TargetEntry;
import org.lee.entry.record.Record;
import org.lee.node.NodeTag;
import org.lee.rules.RuleName;
import org.lee.statement.ValuesStatement;
import org.lee.statement.clause.Clause;
import org.lee.statement.support.Projectable;
import org.lee.type.TypeTag;
import org.lee.util.FuzzUtil;

import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ValuesClause extends Clause<Record> {

    private int length;
    private int width;
    private final List<TargetEntry> projection = new Vector<>();
    public ValuesClause(ValuesStatement statement) {
        super(statement);
    }

    @Override
    public void fuzz() {
        final List<TypeTag> limitations = ((Projectable)this.statement).getProjectTypeLimitation();
        final boolean withLimitations = !limitations.isEmpty();
        if(this.statement.confirmByRuleName(RuleName.REQUIRE_SCALA)){
            length = 1;
            width = withLimitations ? limitations.size(): 1;
            assert width == 1;
        }else {
            length = FuzzUtil.randomIntFromRange(1, 10);
            width = withLimitations ? limitations.size() : FuzzUtil.randomIntFromRange(1, 7);
        }
        assert length > 0 && width > 0;
        final List<TypeTag> targetType;
        if(withLimitations){
            targetType = limitations;
        }else {
            targetType = IntStream.range(0, width).parallel().mapToObj(
                    i -> FuzzUtil.randomlyChooseFrom(TypeTag.GENERATE_PREFER_CHOOSE)).collect(Collectors.toList()
            );
        }
        assert targetType.size() == width;
        IntStream.range(0, length).parallel().forEach(
                i -> {
                    final Record record = new Record(width);
                    targetType.forEach(t -> record.add(t.asMapped().generate()));
                    children.add(record);
                }
        );
        children.get(0).stream().parallel().forEachOrdered(l -> projection.add(TargetEntry.newNamedEntry(l.getType())));
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
}
