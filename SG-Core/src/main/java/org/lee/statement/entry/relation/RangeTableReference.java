package org.lee.statement.entry.relation;

import org.apache.commons.lang3.StringUtils;
import org.lee.statement.node.NodeTag;
import org.lee.statement.node.AliasNameable;
import org.lee.statement.entry.scalar.ColumnReference;
import org.lee.statement.entry.scalar.Scalar;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RangeTableReference implements RangeTableEntry, AliasNameable {

    private String refName = null;
    private final RangeTableEntry entry;
    private final List<ColumnReference> columnReferences = new ArrayList<>();

    public RangeTableReference(RangeTableEntry rangeTableEntry){
        this.entry = rangeTableEntry;
        for(Scalar scalar: entry.getFields()){
            columnReferences.add(new ColumnReference(this, scalar));
        }
    }

    public RangeTableReference(String refName, RangeTableEntry rangeTableEntry){
        this.refName = refName;
        this.entry = rangeTableEntry;
        for(Scalar scalar: entry.getFields()){
            columnReferences.add(new ColumnReference(this, scalar));
        }
    }

    @Override
    public String getString() {
        if(hasAlias()){
            return String.format("%s as %s", entry.getString(), refName);
        }
        return entry.getString();
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.rangeTableReference;
    }

    @Override
    public List<ColumnReference> getFields() {
        return columnReferences;
    }

    @Override
    public String getName() {
        if(hasAlias()){
            return refName;
        }
        return entry.getName();
    }

    public boolean hasAlias(){
        return !StringUtils.isEmpty(refName) && !StringUtils.isBlank(refName);
    }

    @Override
    public String getAlias() {
        return refName;
    }

    @Override
    public void setAlias() {
        refName = "R_" + UUID.randomUUID().toString().replace("-", "").substring(0, 8);
    }
}
