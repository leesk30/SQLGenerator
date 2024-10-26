package org.lee.statement.expression.statistic;

import org.lee.common.Utility;
import org.lee.common.structure.Pair;
import org.lee.entry.scalar.Scalar;
import org.lee.statement.expression.abs.GeneratorStatistic;
import org.lee.type.TypeCategory;
import org.lee.type.TypeTag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class RelatedStatistic implements GeneratorStatistic {
    private final List<Scalar> leftHandSideCandidates;
    private final List<Scalar> rightHandSideCandidates;
    private final List<Scalar> summaryCandidates;
    private final UnrelatedStatistic leftHandSideStatistic;
    private final UnrelatedStatistic rightHandSideStatistic;
    private final UnrelatedStatistic summaryUnrelatedStatistic;

    public RelatedStatistic(List<? extends Scalar> left, List<? extends Scalar> right){
        leftHandSideCandidates = Collections.unmodifiableList(left);
        rightHandSideCandidates = Collections.unmodifiableList(right);
        summaryCandidates = new ArrayList<Scalar>(leftHandSideCandidates.size() + rightHandSideCandidates.size()){
            {
                addAll(leftHandSideCandidates);
                addAll(rightHandSideCandidates);
            }
        };
        leftHandSideStatistic = new UnrelatedStatistic(leftHandSideCandidates);
        rightHandSideStatistic = new UnrelatedStatistic(rightHandSideCandidates);
        summaryUnrelatedStatistic = new UnrelatedStatistic(summaryCandidates);
        collect();
    }

    private void collect(){
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
}
