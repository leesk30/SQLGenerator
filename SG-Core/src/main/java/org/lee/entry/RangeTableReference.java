package org.lee.entry;

import org.apache.commons.lang3.StringUtils;
import org.lee.entry.relation.RangeTableEntry;
import org.lee.entry.complex.RTEJoin;
import org.lee.node.NodeTag;
import org.lee.statement.support.Alias;
import org.lee.util.FuzzUtil;

import java.util.ArrayList;
import java.util.List;

public final class RangeTableReference implements NormalizedEntryNode<RangeTableEntry>, Alias {

    private String refName = null;
    private final RangeTableEntry entry;
    private final List<FieldReference> fieldReferences = new ArrayList<>();

    public RangeTableReference(RangeTableEntry rangeTableEntry){
        this(null, rangeTableEntry);
    }

    public RangeTableReference(String refName, RangeTableEntry rangeTableEntry){
        this.refName = refName;
        this.entry = rangeTableEntry;
        if(entry instanceof RTEJoin){
            ((RTEJoin) entry).getChildNodes().parallelStream().forEach(child -> fieldReferences.addAll(child.getFieldReferences()));
        }else {
            entry.getFields().forEach(scalar -> fieldReferences.add(new FieldReference(this, scalar)));
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
    public String getStringWithoutAlias(){
        return entry.getString();
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.rangeTableReference;
    }

    public List<FieldReference> getFieldReferences() {
        return fieldReferences;
    }

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
        if(hasAlias()){
            throw new RuntimeException("The alias has already been set.");
        }
        refName = FuzzUtil.getRandomName("r");
    }

    @Override
    public RangeTableEntry getRawNode() {
        return null;
    }
}
