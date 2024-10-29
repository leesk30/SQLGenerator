package org.lee.entry.relation;

import org.apache.commons.lang3.StringUtils;
import org.lee.SQLGeneratorContext;
import org.lee.common.Assertion;
import org.lee.common.Utility;
import org.lee.common.global.SymbolTable;
import org.lee.common.structure.Pair;
import org.lee.entry.complex.TargetEntry;
import org.lee.entry.scalar.Field;
import org.lee.statement.expression.Expression;
import org.lee.symbol.Aggregator;
import org.lee.symbol.Signature;
import org.lee.type.TypeTag;
import org.lee.type.literal.Literal;

import java.util.*;

public final class Pivot extends Pivoted {
    private final List<PivotEntry> aggregations = new ArrayList<>();
    private final List<Field> forTarget = new ArrayList<>();
    private final List<Pair<List<Literal<?>>, String>> forValue = new ArrayList<>();
    private String cachedString = null;

    public Pivot(RangeTableEntry rawEntry) {
        super(rawEntry);
    }

    private static class PivotEntry extends TargetEntry{
        private PivotEntry(Signature aggregation, Field targetField) {
            super(Expression.newExpression(aggregation).newChild(targetField));
            assert aggregation instanceof Aggregator;
        }

        private void setAlias(String rename) {
            this.alias = rename;
        }
    }

    private void concatToString(){
        if(cachedString != null){
            return;
        }
        StringBuilder builder = new StringBuilder(rawEntry.getString());
        builder.append(SPACE)
                .append(PIVOT)
                .append(LP)
                .append(nodeArrayToString(aggregations))
                .append(SPACE)
                .append(FOR)
                .append(SPACE);
        if(forTarget.size() > 1){
            builder.append(LP).append(nodeArrayToString(forTarget)).append(RP);
        }else {
            builder.append(forTarget.get(0).getString());
        }
        builder.append(SPACE).append(IN).append(SPACE).append(LP);

        final String[] forPart = new String[forValue.size()];
        for(int i=0; i< forValue.size();i++){
            Pair<List<Literal<?>>, String> pair = forValue.get(i);
            List<Literal<?>> literalPart = pair.getFirst();
            String namePart = pair.getSecond();
            StringBuilder localPartBuilder = new StringBuilder();
            if(literalPart.size() > 1){
                localPartBuilder.append(LP)
                        .append(nodeArrayToString(literalPart))
                        .append(RP);
            }else {
                localPartBuilder.append(literalPart.get(0).getString());
            }
            localPartBuilder.append(SPACE).append(AS).append(SPACE).append(namePart);
            forPart[i] = localPartBuilder.toString();
        }
        builder.append(StringUtils.joinWith(", ", forPart)).append(RP);
        cachedString = builder.toString();
    }

    @Override
    public String getString() {
        if(cachedString == null){
            concatToString();
        }
        return cachedString;
    }

    private void generateAgg(final List<Field> aggregationCandidates){
        final SymbolTable symbolTable = SQLGeneratorContext.getCurrentSymbolTable();
        for(Field fieldToAggregation: aggregationCandidates){
            List<Signature> signatures = symbolTable.getAggregate(fieldToAggregation.getType());
            Signature signature = Utility.randomlyChooseFrom(signatures);
            Assertion.requiredNonNull(signature);
            PivotEntry pivotEntry = new PivotEntry(signature, fieldToAggregation);
            aggregations.add(pivotEntry);
            pivotEntry.setAlias(Utility.getRandomName("pa_"));
        }
    }

    private void generateFor(){
        int elementNum = Utility.randomIntFromRange(2, 7);
        for(int i=0; i<elementNum; i++){
            List<Literal<?>> literalList = new ArrayList<>();
            for(Field field: forTarget){
                literalList.add(field.getType().asMapped().generate());
            }
            String newName = Utility.getRandomName("pf_");
            forValue.add(new Pair<>(literalList, newName));
            for(PivotEntry pivotEntry: aggregations){
                Field pivotedField = getPivotField(newName, pivotEntry);
                fieldList.add(pivotedField);
            }
        }
    }

    private Field getPivotField(String renamedForPart, PivotEntry pivotEntry){
        final String fieldName = renamedForPart + "_" + pivotEntry.getAlias();
        final TypeTag fieldType = pivotEntry.getType();
        return new Field(fieldName, fieldType);
    }

    @Override
    public void fuzz() {
        final Set<Field> candidates = new HashSet<>(getCandidateList());
        final List<Field> aggregationCandidates = new ArrayList<>();

        // todo: add parameter to control
        assert candidates.size() >= 2;
        do{
            aggregationCandidates.add(Utility.randomlyPop(candidates));
        }while (candidates.size() > 1 && Utility.probability(3));

        do {
            forTarget.add(Utility.randomlyPop(candidates));
        }while (!candidates.isEmpty() && Utility.probability(3));

        generateAgg(aggregationCandidates);
        generateFor();

        // add left field to project
        fieldList.addAll(candidates);
    }
}
