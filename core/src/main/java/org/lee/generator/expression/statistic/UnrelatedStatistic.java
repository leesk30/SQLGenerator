package org.lee.generator.expression.statistic;

import org.lee.common.Utility;
import org.lee.common.structure.Pair;
import org.lee.common.structure.TrieTree;
import org.lee.sql.entry.scalar.Scalar;
import org.lee.sql.symbol.Symbol;
import org.lee.sql.type.TypeCategory;
import org.lee.sql.type.TypeTag;

import java.util.*;
import java.util.stream.Collectors;

class UnrelatedStatistic implements GeneratorStatistic{

    protected static final UnrelatedStatistic EMPTY = new UnrelatedStatistic();

    private final List<Scalar> candidateList;
    private final int totalSize;
    private final Map<TypeTag, List<Scalar>> groupByType = new EnumMap<>(TypeTag.class);
    private final TrieTree<TypeTag, Integer> signatureCalculateResultCache = new TrieTree<>();
    private int hit = 0;
    private int attach = 0;

    private RelatedStatistic relatedCache = null;

    // empty
    private UnrelatedStatistic(){
        this.candidateList = Collections.emptyList();
        this.totalSize = 0;
    }

    protected UnrelatedStatistic(List<? extends Scalar> candidateList){
        this.candidateList = Collections.unmodifiableList(candidateList);
        this.totalSize = candidateList.size();
        collect();
    }

    public void collect(){
        if(candidateList.isEmpty()){
            return;
        }
        Utility.groupByType(candidateList, groupByType);
    }

    private Map<TypeTag, Integer> getSignatureNumOfType(Symbol symbol){
        Map<TypeTag, Integer> result = new HashMap<>();
        symbol.getArgumentsTypes().forEach(
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

    private double getAverageDistinctFactor(Symbol symbol){
        double avgValue = 0D;
        Map<TypeTag, Integer> numOfType = getSignatureNumOfType(symbol);
        for(TypeTag typeTag: numOfType.keySet()){
            int typeNum = numOfType.get(typeTag);
            int fieldNum = groupByType.containsKey(typeTag)?groupByType.get(typeTag).size():0;
            avgValue += (double) fieldNum / (double) typeNum;
        }
        return avgValue / numOfType.keySet().size();
    }

    private double getChooseFactor(Symbol symbol){
        return (double) totalSize / (double) symbol.argsNum();
    }

    private int calculateSuitableFactor(Symbol symbol){
        return (int)((getAverageDistinctFactor(symbol)/getChooseFactor(symbol)) * 100);
    }

    public int suitableFactorProb(Symbol symbol){
        attach++;
        if(signatureCalculateResultCache.get(symbol.getArgumentsTypes()).isEmpty()){
            int cached = calculateSuitableFactor(symbol);
            signatureCalculateResultCache.put(symbol.getArgumentsTypes(), cached);
            return cached;
        }
        hit++;
        return signatureCalculateResultCache.get(symbol.getArgumentsTypes()).get(0);
    }

    public double getCacheHitRate(){
        if(attach == 0){
            return 0D;
        }
        return (double) hit / (double) attach;
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

    @Override
    public Pair<Scalar, Scalar> findSimilarPair() {
        Set<TypeTag> moreThanOneCandidates = this.getGroupedType()
                .stream()
                .filter(t -> this.findMatch(t).size() >= 2)
                .collect(Collectors.toSet());
        if(!moreThanOneCandidates.isEmpty()){
            List<Scalar> candidates = Utility.copyList(this.findMatch(Utility.randomlyChooseFrom(moreThanOneCandidates)));
            List<Scalar> choose = Utility.randomlyChooseManyFrom(2, candidates);
            return Pair.fromCollection(choose);
        }
        return null;
    }

    @Override
    public Pair<Scalar, Scalar> tryFindSimilarPair() {
        Pair<Scalar, Scalar> pair = findSimilarPair();
        if(pair == null){
            Scalar left = this.findAny();
            Scalar right = this.findAny();
            TypeTag target = TypeTag.minimal(left.getType(), right.getType());
            return new Pair<>(tryFastConvert(left, target), tryFastConvert(right, target));
        }
        return pair;
    }

    @Override
    public Scalar findAny(TypeTag typeTag) {
        if(groupByType.containsKey(typeTag)){
            return Utility.randomlyChooseFrom(groupByType.get(typeTag));
        }
        return null;
    }

    @Override
    public Pair<Scalar, Scalar> findAnyPair() {
        Scalar left = this.findAny();
        Scalar right = this.findAny();
        return new Pair<>(left, right);
    }

    @Override
    public Pair<Scalar, Scalar> findAnyPair(TypeTag target) {
        Scalar left = this.findAny(target);
        Scalar right = this.findAny(target);
        if(left == null || right == null){
            return null;
        }
        return new Pair<>(left, right);
    }

    @Override
    public Pair<Scalar, Scalar> tryFindAnyPair(TypeTag target) {
        Pair<Scalar, Scalar> pair = findAnyPair();
        if(pair == null){

        }
        return null;
    }

    public Scalar[] findMatchedForSignature(Symbol symbol){
        final int argumentNumber = symbol.argsNum();
        Scalar[] result = new Scalar[argumentNumber];
        if(argumentNumber == 0){
            return result;
        }
        Map<TypeTag, Integer> counter = getSignatureNumOfType(symbol);
        Set<TypeTag> oneCandidateMarker = EnumSet.noneOf(TypeTag.class);
        for (int i=0; i < argumentNumber; i++){
            final TypeTag required = symbol.getArgumentsTypes().get(i);
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
    public List<Scalar> getAllCandidates() {
        return candidateList;
    }

    @Override
    public boolean contains(TypeTag typeTag) {
        return groupByType.containsKey(typeTag);
    }

    @Override
    protected void finalize() throws Throwable {
        // todo: debug to log it, we will delete this when release
        if(attach > 0){
            LOGGER.debug(String.format("UnrelatedStatistic: attach: %d hit: %d rate: %f%%", attach, hit, getCacheHitRate()*100));
        }
        super.finalize();
    }

    @Override
    public GeneratorStatistic toRelated() {
        if(relatedCache == null){
            List<? extends  Scalar> all = getAllCandidates();
            if(all.size() <= 1){
                return this;
            }
            int spliter = all.size() / 2;
            List<? extends Scalar> left = all.subList(0, spliter);
            List<? extends Scalar> right = all.subList(spliter, all.size() - 1);
            relatedCache = new RelatedStatistic(this, left, right);
        }
        return relatedCache;
    }
}
