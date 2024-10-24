package org.lee.statement.expression.abs;

import org.lee.base.Generator;
import org.lee.common.Utility;
import org.lee.common.config.Rule;
import org.lee.entry.scalar.Scalar;
import org.lee.statement.expression.Expression;
import org.lee.statement.generator.ProjectableGenerator;
import org.lee.statement.support.SQLStatement;
import org.lee.statement.support.SQLStatementChildren;
import org.lee.statement.support.Projectable;
import org.lee.type.TypeTag;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public interface IExpressionGenerator<T extends Expression>
        extends
        Generator<T>,
        SQLStatementChildren {

    List<Scalar> getWholeScopeCandidates();
    GeneratorStatistic getStatistic();

    default Scalar getScalar(TypeTag typeTag){
        if(probability(50)){
            return getContextFreeScalar(typeTag);
        }
        return getContextSensitiveScalar(typeTag);
    }

    default Scalar getScalar(){
        return getScalar(TypeTag.randomGenerateTarget());
    }

    default Scalar getContextFreeScalar(){
        final TypeTag targetType = TypeTag.randomGenerateTarget();
        return getContextFreeScalar(targetType);
    }

    default Scalar getContextFreeScalar(TypeTag typeTag){
        final SQLStatement statement = retrieveStatement();
        final boolean enableScalarSubquery = statement == null || statement.enableSubquery();
        final int prob = Utility.randomIntFromRange(0, 100);
        if(enableScalarSubquery && prob < 1){
            return getScalarSubquery(typeTag);
        }
        return getLiteral(typeTag);
    }

    default Scalar getContextSensitiveScalar(){
        final TypeTag targetType = TypeTag.randomGenerateTarget();
        return getContextSensitiveScalar(targetType);
    }

    default  Scalar getContextSensitiveScalar(TypeTag typeTag){
        final SQLStatement statement = retrieveStatement();
        final boolean enableScalarSubquery = statement == null || statement.enableSubquery();
        final int prob = Utility.randomIntFromRange(0, 100);
        if(enableScalarSubquery && prob < 1){
            // context-sensitive generate a related scalar subquery
            return getRelatedScalarSubquery(typeTag);
        }
        Scalar scalar = getStatistic().findAny(typeTag);
        if(scalar != null){
            return scalar;
        }
        return getLiteral(typeTag);
    }


    default Scalar getScalarSubquery(TypeTag typeTag){
        Projectable projectable = ProjectableGenerator.newPreparedProjectable(retrieveStatement());
        projectable.withProjectTypeLimitation(Collections.singletonList(typeTag));
        projectable.setConfig(Rule.REQUIRE_SCALA,true);
        projectable.fuzz();
        return projectable.toScalar();
    }

    default Scalar getScalarSubquery(){
        return getScalarSubquery(TypeTag.randomGenerateTarget());
    }

    default Scalar getRelatedScalarSubquery(TypeTag typeTag){
        Projectable projectable = ProjectableGenerator.newPreparedProjectable(retrieveStatement());
        projectable.withProjectTypeLimitation(Collections.singletonList(typeTag));
        projectable.setConfig(Rule.REQUIRE_SCALA,true);
        projectable.setConfig(Rule.PREFER_SCALA_RELATED,true);
        projectable.fuzz();
        return projectable.toScalar();
    }

    default Scalar getRelatedScalarSubquery(){
        return getRelatedScalarSubquery(TypeTag.randomGenerateTarget());
    }

    default Scalar getPseudo(){
        // todo
        return null;
    }

    default Scalar getLiteral(TypeTag typeTag){
        return typeTag.asMapped().generate();
    }

    default Scalar getLiteral(){
        return getLiteral(Utility.randomlyChooseFrom(TypeTag.GENERATE_PREFER_CHOOSE));
    }

    default Scalar getLiteral(int partial){
        return getLiteral(TypeTag.randomGenerateTarget(), partial);
    }

    default Scalar getLiteral(TypeTag typeTag, int partial){
        return typeTag.asMapped().generate(partial);
    }

    default boolean isContainsType(List<? extends Scalar> scalarList, TypeTag check){
        return scalarList.stream()
                .map(Scalar::getType)
                .anyMatch(exprType -> exprType == check);
    }

    default int containsHowMany(List<? extends Scalar> scalarList, TypeTag check){
        if(scalarList.isEmpty()){
            return 0;
        }
        return (int)scalarList.stream()
                .map(Scalar::getType)
                .filter(type -> type == check)
                .count();
    }
}
