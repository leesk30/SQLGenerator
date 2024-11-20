package org.lee.sql.clause.from;

import org.lee.common.Assertion;
import org.lee.common.enumeration.NodeTag;
import org.lee.common.generator.Generator;
import org.lee.generator.rte.RangeTableReferenceGenerator;
import org.lee.sql.clause.Clause;
import org.lee.sql.entry.RangeTableReference;
import org.lee.sql.entry.complex.RTEJoin;
import org.lee.sql.entry.relation.RangeTableEntry;
import org.lee.sql.statement.SQLStatement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class FromClause extends Clause<RangeTableReference> {
    protected final boolean enableSubquery;
    protected final List<RangeTableEntry> rawEntryList = new ArrayList<>();
//    protected List<List<RangeTableReference>> candidatesList;
    protected FromClause(SQLStatement statement) {
        super(statement);
        enableSubquery = statement.enableSubquery();
    }

    public List<RangeTableEntry> getRawEntryList() {
        return rawEntryList;
    }

    @Override
    public String getString() {
        return FROM + SPACE + nodeArrayToString(children);
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.fromClause;
    }

    protected void merge(RangeTableReference[][] candidatesList){
        for(RangeTableReference[] joinCandidate: candidatesList){
            final List<RangeTableReference> template = Arrays.asList(joinCandidate);
            final RangeTableReference reference = template.stream().reduce(
                    (left, right) -> {
                        RTEJoin join = new RTEJoin(this.statement, left, right);
                        join.fuzz();
                        return new RangeTableReference(join);
                    })
                    .orElseThrow(Assertion.IMPOSSIBLE);
            children.add(reference);
        }
    }

    protected Generator<RangeTableReference> createRangeTableReferenceGenerator(){
        return new RangeTableReferenceGenerator(statement, rawEntryList);
    }
}
