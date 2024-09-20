package org.lee.fuzzer.expr;

import org.lee.entry.scalar.Scalar;
import org.lee.fuzzer.Generator;
import org.lee.statement.expression.Expression;
import org.lee.symbol.Finder;
import org.lee.symbol.Signature;
import org.lee.symbol.TrieTree;
import org.lee.type.TypeTag;
import org.lee.util.FuzzUtil;
import org.lee.util.ListUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public abstract class UnrelatedGenerator<T> implements Generator<T> {
    protected final List<Expression> candidateList;
    protected final List<Expression> replicated;
    protected final Statistic statistic;
    protected final Finder finder = Finder.getFinder();

    protected UnrelatedGenerator(Scalar ... scalars){
        candidateList = new Vector<>(scalars.length);
        IntStream.range(0, scalars.length).parallel().forEach(i -> candidateList.add(scalars[i].toExpression()));
        replicated = ListUtil.copyFrozenList(candidateList);
        statistic = new Statistic(replicated);
    }

    protected UnrelatedGenerator(List<? extends Scalar> expresssionList){
        candidateList = new Vector<>(expresssionList.size());
        IntStream.range(0, expresssionList.size()).parallel().forEach(
                i -> candidateList.add(expresssionList.get(i).toExpression()));
        replicated = ListUtil.copyFrozenList(candidateList);
        statistic = new Statistic(replicated);
    }

    @Override
    public T generate(){
        return generate(FuzzUtil.randomlyChooseFrom(TypeTag.GENERATE_PREFER_CHOOSE));
    }
    abstract T generate(TypeTag require);

    public Statistic getStatistic() {
        return statistic;
    }

    public static class Statistic {
        private final List<? extends Scalar> candidateList;
        private final int totalSize;
        private final Map<TypeTag, List<Scalar>> groupByType = new ConcurrentHashMap<>();
        private final TrieTree<TypeTag, Integer> signatureCalculateResultCache = new TrieTree<>();
        private final AtomicInteger hit = new AtomicInteger(0);
        private final AtomicInteger attach = new AtomicInteger(0);

        public Statistic(List<? extends Scalar> candidateList){
            this.candidateList = Collections.unmodifiableList(candidateList);
            this.totalSize = candidateList.size();
            collect();
        }

        private void collect(){
            if(candidateList.isEmpty()){
                return;
            }
            candidateList.stream().parallel().forEach(scalar -> {
                TypeTag type = scalar.getType();
                if(!groupByType.containsKey(type)){
                    groupByType.put(type, new Vector<>());
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
                synchronized (signatureCalculateResultCache) {
                    signatureCalculateResultCache.put(signature.getArgumentsTypes(), cached);
                }
                return cached;
            }
            hit.incrementAndGet();
            return signatureCalculateResultCache.get(signature.getArgumentsTypes()).get(0);
        }

        public double getCacheHitRate(){
            return (double) hit.get() / (double) attach.get();
        }

        public Scalar findMatchedForTargetType(TypeTag targetType){
            if(groupByType.containsKey(targetType)){
                return FuzzUtil.randomlyChooseFrom(groupByType.get(targetType));
            }
            return null;
        }

        public Scalar findAny(){
            assert !groupByType.isEmpty();
            Set<TypeTag> keys = groupByType.keySet();
            TypeTag anyKey = FuzzUtil.randomlyChooseFrom(keys);
            List<Scalar> candidate = groupByType.get(anyKey);
            assert !candidate.isEmpty();
            return FuzzUtil.randomlyChooseFrom(candidate);
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
                if(groupByType.containsKey(required)){
                    final List<? extends Scalar> localCandidate = groupByType.get(required);
                    final boolean onlyOneCandidate = localCandidate.size() == 1;
                    if (localCandidate.size() >= counter.get(required)) {
                        result[i] = FuzzUtil.randomlyChooseFrom(groupByType.get(required));
                        continue;
                    }

                    if(onlyOneCandidate){
                        oneCandidateMarker.add(required);
                        if(!oneCandidateMarker.contains(required) || FuzzUtil.probability(50)){
                            result[i] = groupByType.get(required).get(0);
                            continue;
                        }
                    }
                }
                result[i] = null;
            }
            return result;
        }
    }
}
