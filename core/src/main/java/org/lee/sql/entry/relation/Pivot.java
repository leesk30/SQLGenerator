package org.lee.sql.entry.relation;

import org.lee.base.VoidNode;
import org.lee.common.Assertion;
import org.lee.common.Utility;
import org.lee.common.config.Rule;
import org.lee.common.config.RuntimeConfigurationProvider;
import org.lee.common.global.SymbolTable;
import org.lee.sql.SQLGeneratorContext;
import org.lee.sql.entry.complex.Record;
import org.lee.sql.entry.scalar.Field;
import org.lee.sql.entry.scalar.Scalar;
import org.lee.sql.expression.Expression;
import org.lee.sql.literal.Literal;
import org.lee.sql.symbol.Function;
import org.lee.sql.symbol.Symbol;
import org.lee.sql.symbol.common.Aggregator;
import org.lee.sql.type.TypeTag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Pivot extends Pivoted {
    /* A pivot template:
     *
     * SELECT * FROM person
     *     PIVOT (
     *         SUM(age) AS a, AVG(class) AS c
     *         FOR (name, age) IN (('John', 30) AS c1, ('Mike', 40) AS c2)
     *     );
     * +------+-----------+-------+-------+-------+-------+
     * |  id  |  address  | c1_a  | c1_c  | c2_a  | c2_c  |
     * +------+-----------+-------+-------+-------+-------+
     * | 200  | Street 2  | NULL  | NULL  | NULL  | NULL  |
     * | 100  | Street 1  | 30    | 1.0   | NULL  | NULL  |
     * | 300  | Street 3  | NULL  | NULL  | NULL  | NULL  |
     * | 400  | Street 4  | NULL  | NULL  | NULL  | NULL  |
     * +------+-----------+-------+-------+-------+-------+
     * */
    private final List<PivotEntry> aggregations = new ArrayList<>();
    private final List<Field> forTarget = new ArrayList<>();
    private final List<PivotLiteralRecord> forValue = new ArrayList<>();
    private String cachedString = null;

    public Pivot(RangeTableEntry rawEntry) {
        super(rawEntry);
    }

    private static class PivotEntry implements VoidNode, Scalar {
        private final String name;
        private final Expression aggregation;
        private PivotEntry(Symbol aggregation, Field targetField, String name) {
            assert aggregation instanceof Aggregator && aggregation instanceof Function;
            this.aggregation = new Expression(aggregation).newChild(targetField);
            this.name = name;
        }

        private String getName(){
            return name;
        }

        @Override
        public String getString() {
            return aggregation.getString() + SPACE + AS + SPACE + name;
        }

        @Override
        public TypeTag getType() {
            return aggregation.getType();
        }
    }

    private static class PivotLiteralRecord extends Record {
        private final String name;

        private PivotLiteralRecord(List<Literal<?>> literals, String name){
            super(literals);
            this.name = name;
        }

        private PivotLiteralRecord(int initialCapacity, String name){
            super(initialCapacity);
            this.name = name;
        }

        @Override
        public String getString() {
            if(size() > 1){
                return LP + super.getString() + RP + SPACE + AS + SPACE + name;
            }
            return super.getString() + SPACE + AS + SPACE + name;
        }
    }

    private void concatToString(){
        if(cachedString != null){
            return;
        }
        String head = rawEntry.getString() + SPACE + PIVOT + LP // LP 1
                + nodeArrayToString(aggregations)
                + SPACE + FOR + SPACE;
        if(forTarget.size() > 1){
            head = head + LP + nodeArrayToString(forTarget) + RP; // LP 1-1 & END LP 1-1
        }else {
            head = head + forTarget.get(0).getString();
        }
        head = head + SPACE + IN + SPACE
                + LP + nodeArrayToString(forValue) + RP // LP 2 & END LP 2
                + RP; // END LP 1
        cachedString = head;
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
            List<Symbol> symbols = symbolTable.getAggregate(fieldToAggregation.getType());
            Symbol symbol = Utility.randomlyChooseFrom(symbols);
            Assertion.requiredNonNull(symbol);
            PivotEntry pivotEntry = new PivotEntry(symbol, fieldToAggregation, Utility.getRandomName("pa_"));
            aggregations.add(pivotEntry);
        }
    }

    private void generateFor(){
        final int elementNum = Utility.randomIntFromRange(2, 7);
        final RuntimeConfigurationProvider provider = SQLGeneratorContext.getCurrentConfigProvider();
        final boolean shouldConcatName = aggregations.size() >= 2 ||
                !provider.confirm(Rule.ENABLE_PIVOT_CONCAT_WHEN_SINGLE_AGGREGATION);
        final int initialCapacity = forTarget.size();

        for(int i=0; i<elementNum; i++){
            String newName = Utility.getRandomName("pf_");
            PivotLiteralRecord record = new PivotLiteralRecord(initialCapacity, newName);
            for(Field field: forTarget){
                record.add(field.getType().asMapped().generate());
            }
            forValue.add(record);

            for(PivotEntry pivotEntry: aggregations){
                Field pivotedField = getPivotField(newName, pivotEntry, shouldConcatName);
                fieldList.add(pivotedField);
            }
        }
    }

    private Field getPivotField(String renamedForPart, PivotEntry pivotEntry, boolean concatName){
        final String fieldName;
        if(concatName){
            fieldName = renamedForPart + "_" + pivotEntry.getName();
        }else {
            fieldName = renamedForPart;
        }
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