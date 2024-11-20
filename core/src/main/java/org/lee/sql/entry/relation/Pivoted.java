package org.lee.sql.entry.relation;

import org.lee.common.NamedLoggers;
import org.lee.common.enumeration.NodeTag;
import org.lee.common.generator.Fuzzer;
import org.lee.common.utils.RandomUtils;
import org.lee.context.SQLGeneratorContext;
import org.lee.sql.entry.Normalized;
import org.lee.sql.entry.scalar.Field;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

public abstract class Pivoted implements Normalized<RangeTableEntry>, RangeTableEntry, Fuzzer {
    protected static final Logger LOGGER = NamedLoggers.getCoreLogger(Pivoted.class);
    protected final RangeTableEntry rawEntry;
    protected final List<Field> fieldList;
    protected final SQLGeneratorContext context;
    public Pivoted(SQLGeneratorContext context, RangeTableEntry rawEntry){
        this.context = context;
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

    public static Pivoted fuzzy(SQLGeneratorContext context, RangeTableEntry entry){
        Pivoted converted;
        // todo: add configuration for pivot
        if(RandomUtils.probability(70)){
            converted = new Pivot(context, entry);
        }else {
            converted = new Unpivot(context, entry);
        }
        converted.fuzz();
        return converted;
    }

    @Override
    public String getName() {
        return "PIVOTED_" + rawEntry.getName();
    }
}
