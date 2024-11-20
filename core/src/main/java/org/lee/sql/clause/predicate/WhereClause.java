package org.lee.sql.clause.predicate;

import org.lee.common.Assertion;
import org.lee.common.NamedLoggers;
import org.lee.common.enumeration.Conf;
import org.lee.common.enumeration.NodeTag;
import org.lee.common.enumeration.Rule;
import org.lee.common.exception.UnrecognizedValueException;
import org.lee.common.generator.Generator;
import org.lee.common.utils.RandomUtils;
import org.lee.generator.common.CombinerAccessor;
import org.lee.generator.expression.WhereQualificationGenerator;
import org.lee.generator.expression.common.ExpressionLocation;
import org.lee.sql.clause.from.FromClause;
import org.lee.sql.entry.FieldReference;
import org.lee.sql.entry.RangeTableReference;
import org.lee.sql.expression.Qualification;
import org.lee.sql.statement.SQLStatement;
import org.lee.sql.statement.common.SQLType;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class WhereClause extends PredicateClause {

    private static final Logger LOGGER = NamedLoggers.getCoreLogger(WhereClause.class);

    protected WhereClause(SQLStatement statement) {
        super(ExpressionLocation.where, statement);
    }

    @Override
    public String getString() {
        Assertion.requiredEquals(children.size(), 1);
        return WHERE + SPACE + nodeArrayToString(children);
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.whereClause;
    }

    private int getNumOfFilter(){
        SQLType sqlType = statement.getSQLType();
        switch (sqlType){
            case select:
                return RandomUtils.randomIntFromRange(0, config.getInt(Conf.MAX_SELECT_WHERE_FILTER_NUM));
            case values:
            case update:
            case insert:
            case delete:
            case merge:
                return 0;
        }
        throw new UnrecognizedValueException(sqlType);
    }

    @Override
    protected Generator<Qualification> createPredicateGenerator() {
        CombinerAccessor<Qualification> generatorCombiner = new CombinerAccessor<>(100);
        List<FieldReference> candidates = new ArrayList<>();
        FromClause fromClause = (FromClause) statement.getClause(NodeTag.fromClause);
        for(RangeTableReference ref: fromClause.getChildNodes()){
            candidates.addAll(ref.getFieldReferences());
        }
        generatorCombiner.add(new WhereQualificationGenerator(statement, candidates));
        if(confirm(Rule.PREFER_RELATED)){
            LOGGER.debug("TODO: add prefer related where qualification generator");
        }
        return generatorCombiner;
    }

    @Override
    public void fuzz() {
        if(!probability(Conf.WHERE_CLAUSE_FUZZ_PROBABILITY)){
            return;
        }
        Generator<Qualification> generator = createPredicateGenerator();
        final int num = getNumOfFilter();
        for(int i=0; i< num; i++){
            Qualification qualification = generator.generate();
            Assertion.requiredNonNull(qualification);
            filter.add(qualification);
        }
        filter.fuzz();
    }
}
