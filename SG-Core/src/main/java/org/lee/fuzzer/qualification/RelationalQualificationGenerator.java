package org.lee.fuzzer.qualification;

import org.lee.entry.FieldReference;
import org.lee.entry.RangeTableReference;
import org.lee.statement.expression.Qualification;
import org.lee.symbol.StaticSymbol;
import org.lee.util.FuzzUtil;
import org.lee.util.Pair;

import java.util.*;
import java.util.stream.IntStream;

public abstract class RelationalQualificationGenerator extends QualificationGenerator{
    private final List<RangeTableReference> candidateRelations;

    private List<Pair<FieldReference, FieldReference>> similarTypePairCache = null;


    public RelationalQualificationGenerator(RangeTableReference ... references){
        candidateRelations = new Vector<>(references.length);
        candidateRelations.addAll(Arrays.asList(references));
    }

    public RelationalQualificationGenerator(List<RangeTableReference> references){
        candidateRelations = new Vector<>(references.size());
        candidateRelations.addAll(references);
    }

    public List<RangeTableReference> getCandidate(){
        return candidateRelations;
    }

    private synchronized void doCache(){
        if(similarTypePairCache != null){
            return;
        }

        similarTypePairCache = new Vector<>();
        final int size = candidateRelations.size();
        final int benchmarkIndex = FuzzUtil.randomIntFromRange(0, size);
        final RangeTableReference benchmark = candidateRelations.get(benchmarkIndex);
        IntStream.range(0, size - 1).map(i -> i >= benchmarkIndex ? i + 1 : i).forEach(
                i -> candidateRelations
                        .get(i)
                        .getFieldReferences()
                        .forEach(
                                cur ->
                                benchmark.getFieldReferences()
                                        .stream()
                                        .filter(benchRef -> cur.getType().isSimilarWith(benchRef.getType()))
                                        .forEach(benchRef -> similarTypePairCache.add(new Pair<>(cur, benchRef)))
                        )
        );
    }

    protected Pair<FieldReference, FieldReference> consumPair(){
        doCache();
        final List<Pair<FieldReference, FieldReference>> safetyRef = similarTypePairCache;
        assert safetyRef != null;
        synchronized (safetyRef){
            if(safetyRef.isEmpty())
                return null;
            final int index = FuzzUtil.randomIntFromRange(0, safetyRef.size());
            return safetyRef.remove(index);
        }
    }


    private Qualification fallbackToLiteralHashEquals(){
        final RangeTableReference randomRTE = FuzzUtil.randomlyChooseFrom(candidateRelations);
        final FieldReference randomFieldReference = FuzzUtil.randomlyChooseFrom(randomRTE.getFieldReferences());
        return compareToLiteral(randomFieldReference);
    }

    public Qualification simplifiedHashEquals(){
        Pair<FieldReference, FieldReference> pair = consumPair();
        if(pair == null || !pair.getFirst().isPresent() || !pair.getSecond().isPresent()){
            return fallbackToLiteralHashEquals();
        }
        Qualification qualification = new Qualification(StaticSymbol.EQUALS);
        qualification.newChild(pair.getFirst().get());
        qualification.newChild(pair.getSecond().get());
        return qualification;
    }

}
