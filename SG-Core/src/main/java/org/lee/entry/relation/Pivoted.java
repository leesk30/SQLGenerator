package org.lee.entry.relation;

import org.lee.entry.NormalizedEntryWrapper;
import org.lee.exception.Assertion;
import org.lee.fuzzer.Fuzzer;
import org.lee.entry.scalar.Field;
import org.lee.node.NodeTag;
import org.lee.util.FuzzUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pivoted implements NormalizedEntryWrapper<RangeTableEntry>, RangeTableEntry, Fuzzer {
    private final RangeTableEntry rawEntry;
    private final List<Field> fieldList;
    private boolean unpivotFlag = false;
    private String pivotedBody;
    public Pivoted(RangeTableEntry rawEntry){
        this.rawEntry = rawEntry;
        this.fieldList = new ArrayList<>(rawEntry.getFields().size());
    }

    @Override
    public List<Field> getFields() {
        return fieldList;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getString() {
        return null;
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.pivoted;
    }

    @Override
    public RangeTableEntry getWrapped() {
        return rawEntry;
    }

    @Override
    public void fuzz() {
        unpivotFlag = FuzzUtil.probability(50);

        if(unpivotFlag){
            unpivot();
        }else {
            pivot();
        }

        Assertion.requiredTrue(!fieldList.isEmpty());
    }

    private List<Field> getCandidate(){
        List<Field> rawFieldList = rawEntry.getFields();
        List<Field> candidateFieldList = new ArrayList<>(rawFieldList.size());
        Collections.copy(candidateFieldList, rawFieldList);
        return candidateFieldList;
    }

    private void unpivot(){
        List<Field> candidateFieldList = getCandidate();
        // todo:

    }

    private void pivot(){
        // todo:
    }
}
