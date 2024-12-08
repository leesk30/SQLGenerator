package org.lee.sql.entry;

import org.apache.commons.lang3.StringUtils;
import org.lee.common.enumeration.NodeTag;
import org.lee.common.utils.RandomUtils;
import org.lee.sql.entry.complex.RTEJoin;
import org.lee.sql.entry.relation.RangeTableEntry;
import org.lee.sql.entry.relation.ValuesRelation;
import org.lee.sql.entry.scalar.Field;
import org.lee.sql.support.Alias;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class RangeTableReference implements Normalized<RangeTableEntry>, Alias {

    private String refName;
    private final RangeTableEntry entry;
    private final boolean isEntryNeedPrintPrettyName;
    private final List<FieldReference> fieldReferences = new ArrayList<>();
    private final UUID uniqueName = UUID.randomUUID();

    public RangeTableReference(RangeTableEntry rangeTableEntry){
        this(null, rangeTableEntry);
    }

    public RangeTableReference(String refName, RangeTableEntry rangeTableEntry){
        this.entry = rangeTableEntry;
        this.refName = refName;
        this.isEntryNeedPrintPrettyName = (entry instanceof ValuesRelation);
        if(entry instanceof RTEJoin){
            fieldReferences.addAll(((RTEJoin) entry).getFieldReferences());
        }else {
            for(Field field : entry.getFields()){
                fieldReferences.add(new FieldReference(this, field));
            }
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
        refName = RandomUtils.getRandomName("r_");
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
