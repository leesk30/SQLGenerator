package org.lee.entry;

import org.apache.commons.lang3.StringUtils;
import org.lee.entry.relation.RangeTableEntry;
import org.lee.entry.complex.RTEJoin;
import org.lee.entry.relation.SubqueryRelation;
import org.lee.entry.relation.ValuesRelation;
import org.lee.node.NodeTag;
import org.lee.statement.ValuesStatement;
import org.lee.statement.support.Alias;
import org.lee.util.FuzzUtil;

import java.util.List;
import java.util.UUID;
import java.util.Vector;

public final class RangeTableReference implements NormalizedEntryWrapper<RangeTableEntry>, Alias {

    private String refName;
    private final RangeTableEntry entry;
    private final boolean isEntryNeedPrintPrettyName;
    private final List<FieldReference> fieldReferences = new Vector<>();
    private final UUID uniqueName = UUID.randomUUID();

    public RangeTableReference(RangeTableEntry rangeTableEntry){
        this(null, rangeTableEntry);
    }

    public RangeTableReference(String refName, RangeTableEntry rangeTableEntry){
        this.entry = rangeTableEntry;
        this.refName = refName;
        this.isEntryNeedPrintPrettyName = (entry instanceof ValuesRelation);
        if(entry instanceof RTEJoin){
            ((RTEJoin) entry).getChildNodes().parallelStream().forEach(child -> fieldReferences.addAll(child.getFieldReferences()));
        }else {
            entry.getFields().forEach(scalar -> fieldReferences.add(new FieldReference(this, scalar)));
        }
    }

    @Override
    public String getString() {
        if(hasAlias()){
            if(isEntryNeedPrintPrettyName){
                return entry.getString() + SPACE + ((ValuesRelation)entry).getWrapped().toPrintPrettyNamedField(getAlias());
            }
            return entry.getString() + SPACE + AS + SPACE + getAlias();
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
        if(this.entry instanceof RTEJoin){
            return;
        }
        if(hasAlias()){
            throw new RuntimeException("The alias has already been set.");
        }
        refName = FuzzUtil.getRandomName("r_");
    }

    @Override
    public RangeTableEntry getWrapped() {
        return entry;
    }

    public String getBody() {
        return entry.getString();
    }

    public UUID getUniqueName(){
        return uniqueName;
    }
}
