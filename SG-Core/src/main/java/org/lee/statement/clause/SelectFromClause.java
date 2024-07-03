package org.lee.statement.clause;

import org.lee.statement.entry.relation.RangeTableReference;
import org.lee.statement.node.NodeTag;
import org.lee.statement.node.Node;
import org.lee.statement.complex.RTEJoin;
import org.lee.statement.SQLStatement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class SelectFromClause implements FromClause{
    private List<RangeTableReference> fromList;
    private List<List<RangeTableReference>> fromCandidate;

    public SelectFromClause(){

    }

    @Override
    public String getString() {
        return null;
    }

    @Override
    public NodeTag getNodeTag() {
        return null;
    }

    @Override
    public List<? extends Node> getChildNodes() {
        return null;
    }

    @Override
    public void generate(SQLStatement scopeContext) {
        return;
    }

    @Override
    public boolean isEmptyClause() {
        return false;
    }

    private void merge(){
        for (List<RangeTableReference> joinCandidate: fromCandidate){
            List<RangeTableReference> template = new ArrayList<>();
            Collections.copy(template, joinCandidate);
            while (template.size() > 1){
                Collections.shuffle(template);
                RangeTableReference left = template.remove(0);
                RangeTableReference right = template.remove(0);
                RTEJoin join = new RTEJoin(left, right);
                RangeTableReference refJoin = new RangeTableReference(join);
                template.add(refJoin);
            }
            fromList.add(template.get(0));
        }
    }
}
