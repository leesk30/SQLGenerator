package org.lee.expression.statistic;

import org.lee.common.Utility;
import org.lee.common.structure.Pair;
import org.lee.entry.scalar.Scalar;
import org.lee.expression.basic.IExpressionGenerator;
import org.lee.type.TypeCategory;
import org.lee.type.TypeTag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class RelatedStatistic implements GeneratorStatistic {
    private final IExpressionGenerator<?> parent;
    private final List<Scalar> leftHandSideCandidates;
    private final List<Scalar> rightHandSideCandidates;
    private final List<Scalar> summaryCandidates;
    private final UnrelatedStatistic leftHandSideStatistic;
    private final UnrelatedStatistic rightHandSideStatistic;
    private final UnrelatedStatistic summaryUnrelatedStatistic;

    public RelatedStatistic(IExpressionGenerator<?> parent, List<? extends Scalar> left, List<? extends Scalar> right){
        this.parent = parent;
        leftHandSideCandidates = Collections.unmodifiableList(left);
        rightHandSideCandidates = Collections.unmodifiableList(right);
        summaryCandidates = new ArrayList<Scalar>(leftHandSideCandidates.size() + rightHandSideCandidates.size()){
            {
                addAll(leftHandSideCandidates);
                addAll(rightHandSideCandidates);
            }
        };
        leftHandSideStatistic = new UnrelatedStatistic(this.parent, leftHandSideCandidates);
        rightHandSideStatistic = new UnrelatedStatistic(this.parent, rightHandSideCandidates);
        summaryUnrelatedStatistic = new UnrelatedStatistic(this.parent, summaryCandidates);
        collect();
    }

    private void collect(){
    }

    @Override
    public Pair<Scalar, Scalar> findSimilarPair() {
        if(Utility.probability(50)){
            return this.getRelatedCategoryPair();
        }else {
            return this.getRelatedTypePair();
        }
    }

    @Override
    public Pair<Scalar, Scalar> tryFindSimilarPair() {
        Pair<Scalar, Scalar> pair = findSimilarPair();
        if(pair == null){
            Scalar left = leftHandSideStatistic.findAny();
            Scalar right = rightHandSideStatistic.findAny();
            TypeTag target = TypeTag.minimal(left.getType(), right.getType());
            return new Pair<>(tryFastConvert(left, target), tryFastConvert(right, target));
        }
        return pair;
    }

    @Override
    public Pair<Scalar, Scalar> findAnyPair() {
        Scalar left = leftHandSideStatistic.findAny();
        Scalar right = rightHandSideStatistic.findAny();
        return new Pair<>(left, right);
    }


    @Override
    public Pair<Scalar, Scalar> findAnyPair(TypeTag target) {
        Scalar left = leftHandSideStatistic.findAny(target);
        Scalar right = rightHandSideStatistic.findAny(target);
        if(left == null || right == null){
            return null;
        }
        return new Pair<>(left, right);
    }

    @Override
    public Pair<Scalar, Scalar> tryFindAnyPair(TypeTag target) {
        Pair<Scalar, Scalar> pair = findAnyPair();
        if(pair == null){
            Scalar any1 = leftHandSideStatistic.findAny(target);
            Scalar any2 = rightHandSideStatistic.findAny(target);
            Scalar left = leftHandSideStatistic.tryFastConvert(any1, target);
            Scalar right = rightHandSideStatistic.tryFastConvert(any2, target);
            return new Pair<>(left, right);
        }
        return pair;
    }

    public Pair<Scalar, Scalar> getRelatedCategoryPair(){
        Set<TypeCategory> left = leftHandSideStatistic.getGroupedCategory();
        Set<TypeCategory> right = rightHandSideStatistic.getGroupedCategory();
        Set<TypeCategory> intersection = Utility.intersect(left, right);
        if(intersection.isEmpty()){
            return null;
        }
        TypeCategory target = Utility.randomlyChooseFrom(intersection);
        Scalar s1 = Utility.randomlyChooseFrom(leftHandSideStatistic.findMatched(target));
        Scalar s2 = Utility.randomlyChooseFrom(rightHandSideStatistic.findMatched(target));
        return new Pair<>(s1, s2);
    }

    public Pair<Scalar, Scalar> getRelatedCategoryPair(TypeCategory category){
        Set<TypeCategory> left = leftHandSideStatistic.getGroupedCategory();
        Set<TypeCategory> right = rightHandSideStatistic.getGroupedCategory();
        if(left.contains(category) && right.contains(category)){
            Scalar s1 = Utility.randomlyChooseFrom(leftHandSideStatistic.findMatched(category));
            Scalar s2 = Utility.randomlyChooseFrom(rightHandSideStatistic.findMatched(category));
            return new Pair<>(s1, s2);
        }
        return null;
    }

    public Pair<Scalar, Scalar> getRelatedTypePair(){
        Set<TypeTag> left = leftHandSideStatistic.getGroupedType();
        Set<TypeTag> right = rightHandSideStatistic.getGroupedType();
        Set<TypeTag> intersection = Utility.intersect(left, right);
        if(intersection.isEmpty()){
            return null;
        }
        TypeTag target = Utility.randomlyChooseFrom(intersection);
        Scalar s1 = Utility.randomlyChooseFrom(leftHandSideStatistic.findMatch(target));
        Scalar s2 = Utility.randomlyChooseFrom(rightHandSideStatistic.findMatch(target));
        return new Pair<>(s1, s2);
    }

    public Pair<Scalar, Scalar> getRelatedTypePair(TypeTag targetType){
        Set<TypeTag> left = leftHandSideStatistic.getGroupedType();
        Set<TypeTag> right = rightHandSideStatistic.getGroupedType();
        if(left.contains(targetType) && right.contains(targetType)){
            Scalar s1 = Utility.randomlyChooseFrom(leftHandSideStatistic.findMatch(targetType));
            Scalar s2 = Utility.randomlyChooseFrom(rightHandSideStatistic.findMatch(targetType));
            return new Pair<>(s1, s2);
        }
        return null;
    }

    @Override
    public List<Scalar> getWholeScopeCandidates() {
        return summaryCandidates;
    }

    @Override
    public boolean contains(TypeTag typeTag) {
        return summaryUnrelatedStatistic.contains(typeTag);
    }

    @Override
    public List<Scalar> findMatch(TypeTag typeTag) {
        return summaryUnrelatedStatistic.findMatch(typeTag);
    }

    @Override
    public List<Scalar> findMatch(TypeCategory category) {
        return summaryUnrelatedStatistic.findMatch(category);
    }

    @Override
    public Scalar findAny() {
        return summaryUnrelatedStatistic.findAny();
    }

    @Override
    public Scalar findAny(TypeTag typeTag) {
        return summaryUnrelatedStatistic.findAny(typeTag);
    }

    @Override
    public IExpressionGenerator<?> getParent() {
        return parent;
    }
}