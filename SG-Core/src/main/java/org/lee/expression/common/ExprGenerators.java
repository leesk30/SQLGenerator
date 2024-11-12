package org.lee.statement.expression.common;

import org.lee.base.Generator;
import org.lee.base.NodeTag;
import org.lee.common.Utility;
import org.lee.entry.FieldReference;
import org.lee.entry.RangeTableReference;
import org.lee.entry.complex.RTEJoin;
import org.lee.statement.clause.Clause;
import org.lee.statement.clause.condition.WhereClause;
import org.lee.statement.clause.from.FromClause;
import org.lee.statement.clause.project.SelectClause;
import org.lee.statement.clause.project.SelectClauseWithinFrom;
import org.lee.statement.clause.project.SelectClauseWithoutFrom;
import org.lee.statement.expression.Expression;
import org.lee.statement.expression.abs.QualificationGenerator;
import org.lee.statement.expression.generator.CommonExpressionGenerator;
import org.lee.statement.expression.generator.JoinQualificationGenerator;
import org.lee.statement.expression.generator.WhereQualificationGenerator;
import org.lee.statement.select.AbstractSimpleSelectStatement;
import org.lee.statement.select.SelectClauseStatement;
import org.lee.statement.support.Projectable;
import org.lee.statement.support.SQLStatement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExprGenerators {

    public static CommonExpressionGenerator projectionFactory(Projectable statement){
        // todo: disable window for now
        if(statement == null || statement instanceof SelectClauseStatement){
            return new CommonExpressionGenerator(Location.project, false, false, statement, Collections.emptyList());
        }
        if(statement instanceof AbstractSimpleSelectStatement){
            AbstractSimpleSelectStatement simple = (AbstractSimpleSelectStatement) statement;
            List<FieldReference> fieldReferences = new ArrayList<>();
            simple.getFromClause().getChildNodes().forEach(ref -> fieldReferences.addAll(ref.getFieldReferences()));
            return new CommonExpressionGenerator(Location.project, true, false, statement, fieldReferences);
        }
        throw new UnsupportedOperationException("Cannot build expression generators for:" + statement.getClass().getName());
    }

    public static CommonExpressionGenerator projectionFactory(SelectClause clause){
        // todo: disable window for now
        if(clause instanceof SelectClauseWithoutFrom){
            return new CommonExpressionGenerator(Location.project, false, false, clause.retrieveParent(), Collections.emptyList());
        }
        if(clause instanceof SelectClauseWithinFrom){
            AbstractSimpleSelectStatement simple = ((SelectClauseWithinFrom) clause).retrieveParent();
            List<FieldReference> fieldReferences = new ArrayList<>();
            simple.getFromClause().getChildNodes().forEach(ref -> fieldReferences.addAll(ref.getFieldReferences()));
            return new CommonExpressionGenerator(Location.project, true, false, simple, fieldReferences);
        }
        throw new UnsupportedOperationException("Cannot build expression generators for:" + clause.getClass().getName());
    }

    public static QualificationGenerator qualificationFactory(Clause<?> clause){
        if(clause instanceof WhereClause){
            List<FieldReference> candidates = new ArrayList<>();
            SQLStatement statement = clause.retrieveParent();
            FromClause fromClause = (FromClause) statement.getClause(NodeTag.fromClause);
            for(RangeTableReference ref: fromClause.getChildNodes()){
                candidates.addAll(ref.getFieldReferences());
            }
            return new WhereQualificationGenerator(statement, candidates);
        }
        if(clause instanceof RTEJoin){
            RangeTableReference left = ((RTEJoin) clause).getLeft();
            RangeTableReference right = ((RTEJoin) clause).getRight();
            return new JoinQualificationGenerator(clause.retrieveParent(), left, right);
        }
        throw new UnsupportedOperationException("Cannot build expression generators for:" + clause.getClass().getName());
    }

    public static QualificationGenerator qualificationFactory(SQLStatement statement, RangeTableReference left, RangeTableReference right){
        return new JoinQualificationGenerator(statement, left, right);
    }

    public static <T extends Expression> CombinedGenerator<T> newEmptyCombinedGenerator(){
        return new CombinedGenerator<>();
    }

    public static class CombinedGenerator<T extends Expression> implements Generator<T> {
        private final List<Generator<T>> generators = new ArrayList<>();

        public List<Generator<T>> getGenerators() {
            return generators;
        }

        public void registerGenerator(Generator<T> generator){
            generators.add(generator);
        }

        private Generator<T> get(){
            if(generators.isEmpty()){
                throw new UnsupportedOperationException("The combined generator is empty");
            }
            return Utility.randomlyChooseFrom(generators);
        }

        @Override
        public T generate() {
            return get().generate();
        }
    }

}
