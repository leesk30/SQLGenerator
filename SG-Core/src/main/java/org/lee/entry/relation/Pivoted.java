package org.lee.entry.relation;

import org.lee.base.Fuzzer;
import org.lee.base.NodeTag;
import org.lee.common.Utility;
import org.lee.entry.Normalized;
import org.lee.entry.scalar.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public abstract class Pivoted implements Normalized<RangeTableEntry>, RangeTableEntry, Fuzzer {
    protected static final Logger LOGGER = LoggerFactory.getLogger(Pivoted.class);
    protected final RangeTableEntry rawEntry;
    protected final List<Field> fieldList;
    public Pivoted(RangeTableEntry rawEntry){
        this.rawEntry = rawEntry;
        this.fieldList = new ArrayList<>(rawEntry.getFields().size());
    }

    @Override
    public List<Field> getFields() {
        return fieldList;
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.pivoted;
    }

    @Override
    public RangeTableEntry getWrapped() {
        return rawEntry;
    }

    List<Field> getCandidateList(){
        // The result is always stable here
        return new ArrayList<>(rawEntry.getFields());
    }

    public static Pivoted fuzzy(RangeTableEntry entry){
        Pivoted converted;
        // todo: add configuration for pivot
        if(Utility.probability(70)){
            converted = new Pivot(entry);
        }else {
            converted = new Unpivot(entry);
        }
        converted.fuzz();
        return converted;
    }

    @Override
    public String getName() {
        return "PIVOTED_" + rawEntry.getName();
    }
}
