package org.lee.statement.expression.statistic;

import org.lee.common.Utility;
import org.lee.common.structure.TrieTree;
import org.lee.entry.scalar.Scalar;
import org.lee.statement.expression.abs.GeneratorStatistic;
import org.lee.symbol.Signature;
import org.lee.type.TypeCategory;
import org.lee.type.TypeTag;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class UnrelatedStatistic implements GeneratorStatistic {
    private final List<Scalar> candidateList;
    private final int totalSize;
    private final Map<TypeTag, List<Scalar>> groupByType = new EnumMap<>(TypeTag.class);
    private final TrieTree<TypeTag, Integer> signatureCalculateResultCache = new TrieTree<>();
    private final AtomicInteger hit = new AtomicInteger(0);
    private final AtomicInteger attach = new AtomicInteger(0);

    public UnrelatedStatistic(List<? extends Scalar> candidateList){
        this.candidateList = Collections.unmodifiableList(candidateList);
        this.totalSize = candidateList.size();
        collect();
    }


    public void collect(){
        if(candidateList.isEmpty()){
            return;
        }
        candidateList.forEach(scalar -> {
            TypeTag type = scalar.getType();
            if(!groupByType.containsKey(type)){
                groupByType.put(type, new ArrayList<>());
            }
            groupByType.get(type).add(scalar);
        });
    }

    private Map<TypeTag, Integer> getSignatureNumOfType(Signature signature){
        Map<TypeTag, Integer> result = new HashMap<>();
        signature.getArgumentsTypes().forEach(
                type -> {
                    if (!result.containsKey(type)){
                        result.put(type, 1);
                    }else {
                        result.put(type, result.get(type)+1);
                    }
                }
        );
        return result;
    }

    private double getAverageDistinctFactor(Signature signature){
        double avgValue = 0D;
        Map<TypeTag, Integer> numOfType = getSignatureNumOfType(signature);
        for(TypeTag typeTag: numOfType.keySet()){
            int typeNum = numOfType.get(typeTag);
            int fieldNum = groupByType.containsKey(typeTag)?groupByType.get(typeTag).size():0;
            avgValue += (double) fieldNum / (double) typeNum;
        }
        return avgValue / numOfType.keySet().size();
    }

    private double getChooseFactor(Signature signature){
        return (double) totalSize / (double) signature.argsNum();
    }

    private int calculateSuitableFactor(Signature signature){
        return (int)((getAverageDistinctFactor(signature)/getChooseFactor(signature)) * 100);
    }

    public int suitableFactorProb(Signature signature){
        attach.incrementAndGet();
        if(signatureCalculateResultCache.get(signature.getArgumentsTypes()).isEmpty()){
            int cached = calculateSuitableFactor(signature);
            signatureCalculateResultCache.put(signature.getArgumentsTypes(), cached);
            return cached;
        }
        hit.incrementAndGet();
        return signatureCalculateResultCache.get(signature.getArgumentsTypes()).get(0);
    }

    public double getCacheHitRate(){
        return (double) hit.get() / (double) attach.get();
    }

    @Override
    public List<Scalar> findMatch(TypeTag targetType){
        if(groupByType.containsKey(targetType)){
            return groupByType.get(targetType);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Scalar> findMatch(TypeCategory category){
        List<Scalar> candidates = new ArrayList<>();
        for(TypeTag key: groupByType.keySet()){
            if(key.getCategory() == category){
                candidates.addAll(groupByType.get(key));
            }
        }
        return candidates;
    }

    @Override
    public Scalar findAny(){
        assert !groupByType.isEmpty();
        Set<TypeTag> keys = groupByType.keySet();
        TypeTag anyKey = Utility.randomlyChooseFrom(keys);
        List<Scalar> candidate = groupByType.get(anyKey);
        assert !candidate.isEmpty();
        return Utility.randomlyChooseFrom(candidate);
    }

    public Scalar[] findMatchedForSignature(Signature signature){
        final int argumentNumber = signature.argsNum();
        Scalar[] result = new Scalar[argumentNumber];
        if(argumentNumber == 0){
            return result;
        }
        Map<TypeTag, Integer> counter = getSignatureNumOfType(signature);
        Set<TypeTag> oneCandidateMarker = new HashSet<>();
        for (int i=0; i < argumentNumber; i++){
            final TypeTag required = signature.getArgumentsTypes().get(i);
            if(!groupByType.containsKey(required)){
                continue;
            }

            final List<? extends Scalar> localCandidate = groupByType.get(required);
            final boolean onlyOneCandidate = localCandidate.size() == 1;
            if (localCandidate.size() >= counter.get(required)) {
                result[i] = Utility.randomlyChooseFrom(groupByType.get(required));
                continue;
            }

            if(onlyOneCandidate){
                oneCandidateMarker.add(required);
                if(!oneCandidateMarker.contains(required) || Utility.probability(50)){
                    result[i] = groupByType.get(required).get(0);
                }
            }
        }
        return result;
    }

    public List<Scalar> findMatched(TypeCategory category){
        List<Scalar> candidatesResult = new ArrayList<>();
        for(TypeTag typeTag: groupByType.keySet()){
            if(typeTag.getCategory() == category){
                candidatesResult.addAll(groupByType.get(typeTag));
            }
        }
        return candidatesResult;
    }

    public Set<TypeTag> getGroupedType() {
        return groupByType.keySet();
    }

    public Set<TypeCategory> getGroupedCategory(){
        Set<TypeCategory> categorySet = new HashSet<>();
        for(TypeTag key: groupByType.keySet()){
            categorySet.add(key.getCategory());
        }
        return categorySet;
    }

    @Override
    public List<Scalar> getWholeScopeCandidates() {
        return candidateList;
    }

    @Override
    public boolean contains(TypeTag typeTag) {
        return groupByType.containsKey(typeTag);
    }

}
