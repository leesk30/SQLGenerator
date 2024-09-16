package org.lee.fuzzer.expr;

import org.lee.entry.scalar.Scalar;
import org.lee.statement.expression.Expression;
import org.lee.symbol.Finder;
import org.lee.symbol.Function;
import org.lee.symbol.Operator;
import org.lee.symbol.Signature;
import org.lee.type.TypeCategory;
import org.lee.type.TypeTag;
import org.lee.util.FuzzUtil;
import org.lee.util.ListUtil;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface ExpressionGenerator extends IExpressionGenerator<Expression> {

    Expression generate(TypeTag required);
    Expression fallback(TypeTag required);

    default List<Expression> tryMergeToExpression(Scalar ... scalars){
        return tryMergeToExpression(Arrays.asList(scalars));
    }

    default Expression fallback(Scalar ... anyInputs){
        return fallback(Arrays.asList(anyInputs));
    }

    default Expression fallback(List<? extends Scalar> anyInputs){
        if(anyInputs != null && !anyInputs.isEmpty()){
            Scalar choose = FuzzUtil.randomlyChooseFrom(anyInputs);
            return choose.toExpression();
        }
        return getLiteral().toExpression();
    }

    default Expression operateUnit(Scalar left, Scalar right){
        final Finder finder = Finder.getFinder();
        Operator op = (Operator) FuzzUtil.randomlyChooseFrom(finder.getOperator(left.getType(), right.getType()));
        if(op != null){
            return new Expression(op).newChild(left).newChild(right);
        }else {
            return fallback(left, right);
        }
    }

    default Expression functionUnit(Scalar ... scalars){
        return functionUnit(Arrays.asList(scalars));
    }

    default Expression functionUnit(List<Scalar> scalars){
        final Finder finder = Finder.getFinder();
        final List<Signature> candidate = finder.getFunction(scalars.stream().map(Scalar::getType).collect(Collectors.toList()));
        Function function = (Function) FuzzUtil.randomlyChooseFrom(candidate);
        if(function == null){
            return fallback(scalars);
        }
        final Expression result = new Expression(function);
        for (Scalar scalar : scalars) {
            result.newChild(scalar);
        }
        assert result.isComplete();
        return result;
    }


    default List<Expression> tryMergeToExpression(List<Scalar> scalars){
        final List<Scalar> template = ListUtil.copyListShuffle(scalars);
        if(template.isEmpty()){
            return Collections.singletonList(new Expression(getLiteral()));
        }
        final int maxSize = Math.min(Finder.getFinder().maxFunctionArgWidth(), scalars.size());
        int epoch = 0;
        do {
            int windowSize = Math.min(template.size(), FuzzUtil.randomIntFromRange(1, maxSize));
            epoch++;
            Collections.shuffle(template);
            if(windowSize == 1){
                template.add(functionUnit(template.remove(0)));
            }else if(windowSize == 2){
                Scalar left = template.remove(0);
                Scalar right = template.remove(1);
                int prob = 50;
                if(left.getType().getCategory() == TypeCategory.NUMBER && right.getType().getCategory() == TypeCategory.NUMBER){
                    prob = 75;
                }
                if(FuzzUtil.probability(prob)){
                    template.add(operateUnit(left, right));
                }else {
                    template.add(functionUnit(left, right));
                }
            }else {
                template.add(
                        functionUnit(
                                IntStream.range(0, windowSize)
                                        .mapToObj(i -> template.remove(0))
                                        .collect(Collectors.toList())
                        )
                );
            }
        }while (FuzzUtil.probability(50/epoch));
        return template.parallelStream().map(scalar -> scalar instanceof Expression? (Expression) scalar: new Expression(scalar)).collect(Collectors.toList());
    }

    static class Statistic{
        private final List<TypeTag> requiredType;
        private final List<? extends Scalar> candidateList;
        private final Map<TypeTag, Integer> numOfScalar = new HashMap<>();
        private final Map<TypeTag, Integer> numOfType = new HashMap<>();

        private final int findMatchedNum;
        private final int totalTypeSize;
        private final int totalScalarSize;
        private final double chooseFactor;
        private final double averageDistinctFactor;

        public Statistic(List<TypeTag> requiredType, List<? extends Scalar> candidateList){
            this.requiredType = requiredType;
            this.totalTypeSize = requiredType.size();
            this.candidateList = candidateList;
            this.totalScalarSize = candidateList.size();
            this.findMatchedNum = collect();
            this.chooseFactor = (double) totalScalarSize / (double) totalTypeSize;
            this.averageDistinctFactor = collectAverageDistinctFactor();
        }

        private int collect(){
            int count = 0;
            for(TypeTag required: requiredType){
                if(!numOfScalar.containsKey(required)){
                    int numOfThisType = IExpressionGenerator.containsHowMany(candidateList, required);
                    numOfScalar.put(required, numOfThisType);
                }
                if(numOfScalar.get(required) != 0){
                    count++;
                }

                if(!numOfType.containsKey(required)){
                    numOfType.put(required, 1);
                }else {
                    numOfType.put(required, numOfType.get(required) + 1);
                }
            }
            return count;
        }

        private double collectAverageDistinctFactor(){
            double avgValue = 0D;
            for(TypeTag typeTag: numOfType.keySet()){
                int typeNum = numOfType.get(typeTag);
                int fieldNum = numOfScalar.get(typeTag);
                avgValue += (double) fieldNum / (double) typeNum;
            }
            return avgValue / numOfType.keySet().size();
        }

        public int getFindMatchedNum() {
            return findMatchedNum;
        }

        public int getHowManyRequiredType(TypeTag tag){
            return numOfScalar.containsKey(tag) ? 0: numOfScalar.get(tag);
        }

        public int getHowManyExistsTypeInCandidate(TypeTag tag){
            return numOfType.containsKey(tag) ? 0: numOfType.get(tag);
        }

        public Set<TypeTag> distinctType(){
            return numOfScalar.keySet();
        }

        public double getAverageDistinctFactor() {
            return averageDistinctFactor;
        }

        public double getChooseFactor() {
            return chooseFactor;
        }

        public int getTotalScalarSize() {
            return totalScalarSize;
        }

        public int getTotalTypeSize() {
            return totalTypeSize;
        }

        public int suitableFactorProb(){
//            System.out.println(getChooseFactor() + " " + getAverageDistinctFactor());
//            double base = getChooseFactor() > 1 ? 1 : getChooseFactor();
//            double factor = getAverageDistinctFactor() > 1 ? 1 : getAverageDistinctFactor();
//            System.out.println((int)((factor/base) * 100));
            return (int)((averageDistinctFactor/chooseFactor) * 100);
        }

        public Scalar[] findForAll(){
            Scalar[] result = new Scalar[totalTypeSize];
            int count = 0;
            Map<TypeTag, Integer> copiedCounter = new HashMap<>(numOfType);
            for(Scalar scalar: candidateList){
                final TypeTag type = scalar.getType();
                if(count < totalTypeSize && type == requiredType.get(count)){
                    if(copiedCounter.containsKey(type) && copiedCounter.get(type)!=0){
                        result[count] = scalar;
                        copiedCounter.put(type, copiedCounter.get(type) - 1);
                    }else {
                        result[count] = null;
                    }
                    count++;
                    continue;
                }
                break;
            }
            return result;
        }
    }
}
