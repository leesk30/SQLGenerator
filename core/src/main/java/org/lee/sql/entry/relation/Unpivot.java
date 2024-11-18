package org.lee.sql.entry.relation;

import org.apache.commons.lang3.StringUtils;
import org.lee.common.Assertion;
import org.lee.common.Utility;
import org.lee.common.debug.Printer;
import org.lee.sql.entry.scalar.Field;
import org.lee.sql.type.TypeTag;

import java.util.*;

public final class Unpivot extends Pivoted{

    private final boolean excludedNulls = Utility.probability(50);
    private final boolean representExcludedNulls = excludedNulls && Utility.probability(50);
    private int groupSize;

    private UnpivotNameField unpivotNameField = null;
    private final List<UnpivotField> unpivotFieldList = new ArrayList<>();
    private final List<List<Field>> forInFieldGroups = new ArrayList<>();
    public Unpivot(RangeTableEntry rawEntry) {
        super(rawEntry);
    }

    private static class UnpivotField extends Field{

        public UnpivotField(String fieldName, TypeTag fieldType) {
            super(fieldName, fieldType);
        }
    }

    private static class UnpivotNameField extends Field{

        public UnpivotNameField(String fieldName, TypeTag fieldType) {
            super(fieldName, fieldType);
        }
    }

    private List<List<Field>> getUnpivotCandidate(){
        Map<TypeTag, List<Field>> groupBy = new EnumMap<>(TypeTag.class);
        Utility.groupByType(getCandidateList(), groupBy);
        Set<TypeTag> groupByKey = groupBy.keySet();
        int i=0, maxSize = 0;
        int[] sizeArr = new int[groupByKey.size()];
        for(TypeTag type: groupByKey){
            sizeArr[i] = groupBy.get(type).size();
            maxSize = Math.max(sizeArr[i], maxSize);
            i++;
        }
        // todo: optimize group size
        groupSize = Utility.randomIntFromRange(1, sizeArr.length);
        int minSize = Arrays.stream(sizeArr).min().orElse(-1);
        List<List<Field>> candidates = new ArrayList<>();
        for(TypeTag type: groupByKey){
            List<Field> candidate = groupBy.get(type);
            int size = candidate.size();
            if(size < minSize){
                continue;
            }
            // shuffle and return
            Collections.shuffle(candidate);
            candidates.add(candidate);
        }
        Collections.shuffle(candidates);
        return candidates;
    }

    private void processField(final List<Field> shouldRemovedField){
        for(Field raw: getCandidateList()){
            int cur=-1;

            for(int i=0; i < shouldRemovedField.size(); i++){
                Field remove = shouldRemovedField.get(i);
                if(remove == raw){
                    cur = i;
                    break;
                }
                if(remove.getString().equals(raw.getString())){
                    cur = i;
                    break;
                }
            }

            if(cur == -1){
                fieldList.add(raw);
            }else {
                shouldRemovedField.remove(cur);
            }
        }

        if(!shouldRemovedField.isEmpty()){
            LOGGER.error("The field should be removed is not empty after process!");
            LOGGER.error("ShouldRemoved: " + Printer.formatNodeList(shouldRemovedField));
            LOGGER.error("FieldList: " + Printer.formatNodeList(fieldList));
            LOGGER.error("OriginalFieldList: " + Printer.formatNodeList(getCandidateList()));
        }

        fieldList.add(unpivotNameField);
        fieldList.addAll(unpivotFieldList);
    }

    @Override
    public void fuzz() {
        final List<List<Field>> candidate = getUnpivotCandidate();
        final List<Field> shouldRemovedField = new ArrayList<>();
        final int maxGrouped = candidate.size() / groupSize;
        int prob = 100;
        boolean isEmpty = false;

        do{
            List<Field> group = new ArrayList<>();
            for(List<Field> choose: candidate){
                Field field = choose.remove(0);
                group.add(field);
                shouldRemovedField.add(field);
                if(!isEmpty && choose.isEmpty()){
                    isEmpty = true;
                }
            }
            int factor = Math.max(0, forInFieldGroups.size() - maxGrouped);
            prob = factor == 0 ? 100 : prob /factor;
            forInFieldGroups.add(group);
        }while (!isEmpty && Utility.probability(prob));

        Assertion.requiredFalse(forInFieldGroups.isEmpty());
        unpivotNameField = new UnpivotNameField(Utility.getRandomName("upn_"), TypeTag.string);
        for(Field raw: forInFieldGroups.get(0)){
            unpivotFieldList.add(new UnpivotField(Utility.getRandomName("up_"), raw.getType()));
        }
        processField(shouldRemovedField);
    }

    private String getOption(){
        if(representExcludedNulls){
            return "EXCLUDE NULLS ";
        }
        if(excludedNulls){
            return "";
        }
        return "INCLUDE NULLS ";
    }

    private String getUnpivotFieldString(){
        if(unpivotFieldList.size() > 1){
            return LP + nodeArrayToString(unpivotFieldList) + RP;
        }
        return unpivotFieldList.get(0).getString();
    }

    private String getForLoopString(){
        String loop = StringUtils.joinWith(", ", forInFieldGroups.stream().map(element -> {
            final String asNewName = SPACE + AS + SPACE + Utility.getRandomName("UPF_");
            if(element.size() > 1){
                return LP + nodeArrayToString(element) + RP + asNewName;
            }
            return nodeArrayToString(element) + asNewName;
        }).toArray());
        return FOR + SPACE + unpivotNameField.getString() + SPACE + IN + SPACE + LP + loop + RP;
    }

    @Override
    public String getString() {
        return rawEntry.getString() + SPACE + UNPIVOT + SPACE + getOption() + LP + getUnpivotFieldString() + SPACE + getForLoopString() + RP;
    }
}
