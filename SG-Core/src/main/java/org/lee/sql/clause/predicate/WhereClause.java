package org.lee.sql.clause.predicate;

import org.lee.base.Generator;
import org.lee.base.NodeTag;
import org.lee.common.Assertion;
import org.lee.common.Utility;
import org.lee.common.config.Conf;
import org.lee.common.config.Rule;
import org.lee.common.exception.UnrecognizedValueException;
import org.lee.entry.FieldReference;
import org.lee.entry.RangeTableReference;
import org.lee.generator.expression.common.ExpressionLocation;
import org.lee.generator.WeightedGenerator;
import org.lee.generator.expression.WhereQualificationGenerator;
import org.lee.sql.basic.SQLType;
import org.lee.sql.clause.from.FromClause;
import org.lee.sql.expression.Qualification;
import org.lee.sql.support.SQLStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class WhereClause extends PredicateClause {

    private static final Logger LOGGER = LoggerFactory.getLogger(WhereClause.class);

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
                return Utility.randomIntFromRange(0, config.getInt(Conf.MAX_SELECT_WHERE_FILTER_NUM));
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
        WeightedGenerator.Combiner<Qualification> generatorCombiner = WeightedGenerator.getCombiner(100);
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
        Generator<Qualification> generator = createPredicateGenerator();
        int num = getNumOfFilter();
        for(int i=0; i< num; i++){
            Qualification qualification = generator.generate();
            Assertion.requiredNonNull(qualification);
            filter.add(qualification);
        }
        filter.fuzz();
    }
}
