package org.lee.statement.entry.relation;

import org.lee.statement.common.Projectable;
import org.lee.statement.entry.NormalizedEntryNode;
import org.lee.statement.entry.scalar.Field;
import org.lee.statement.node.Alias;
import org.lee.statement.node.NodeTag;

import java.util.List;

public class CTE implements NormalizedEntryNode<Projectable>, RangeTableEntry {
    protected String cteName;
    protected final Projectable projectableStatement;
    public CTE(Projectable statement){
        this.projectableStatement = statement;
        this.cteName = Alias.getRandomName("SC");
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
    public List<Field> getFields() {
        return projectableStatement.project();
    }

    @Override
    public String getName() {
        return cteName;
    }

    @Override
    public Projectable getRawNode() {
        return projectableStatement;
    }
}
