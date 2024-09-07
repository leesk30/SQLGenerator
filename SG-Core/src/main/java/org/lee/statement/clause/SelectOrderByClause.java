package org.lee.statement.clause;

import org.lee.common.DevTempConf;
import org.lee.entry.complex.SortEntry;
import org.lee.entry.complex.TargetEntry;
import org.lee.entry.literal.Literal;
import org.lee.entry.literal.LiteralNumber;
import org.lee.rules.RuleName;
import org.lee.rules.RuleSet;
import org.lee.statement.select.SelectStatement;
import org.lee.statement.support.Sortable;
import org.lee.type.TypeTag;
import org.lee.util.FuzzUtil;

import java.util.List;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

public class SelectOrderByClause extends SortByClause{
    private final RuleSet ruleSetRef = this.statement.getRuleSet();
    public SelectOrderByClause(SelectStatement statement) {
        super(statement);
    }

    IntConsumer orderByIndex(){
        final SelectStatement selectStatement = (SelectStatement) this.statement;
        final int projectionLength = selectStatement.width();
        return i -> {
            final int orderByIndex = FuzzUtil.randomIntFromRange(1, projectionLength+1);
            final Literal<Number> literal = new LiteralNumber(TypeTag.int_, orderByIndex);
            final SortEntry sortEntry = new SortEntry(literal, ruleSetRef);
            sortEntry.fuzz();
            children.add(sortEntry);
        };
    }

    IntConsumer orderByTarget(){
        final SelectStatement selectStatement = (SelectStatement) this.statement;
        final List<TargetEntry> targetEntries = selectStatement.project();
        final RuleSet ruleSet = selectStatement.getRuleSet();
        return i -> {
                    final TargetEntry targetEntry = FuzzUtil.randomlyChooseFrom(targetEntries);
                    final SortEntry sortEntry = new SortEntry(targetEntry, ruleSet);
                    sortEntry.fuzz();
                    children.add(sortEntry);
                };
    }

    @Override
    public void fuzz(){
        if(!FuzzUtil.probability(DevTempConf.SORT_BY_CLAUSE_FUZZ_PROBABILITY)){
            return;
        }
        assert this.statement instanceof Sortable;
        assert this.statement instanceof SelectStatement;
        final SelectStatement selectStatement = (SelectStatement) this.statement;
        final int projectionLength = selectStatement.width();
        final int orderNumber = FuzzUtil.randomIntFromRange(1, 2 * projectionLength);

        if(ruleSetRef.confirm(RuleName.REQUIRE_SCALA)){
            final SortEntry sortEntry = new SortEntry(new LiteralNumber(TypeTag.int_, 1), ruleSetRef);
            sortEntry.fuzz();
            children.add(sortEntry);
            return;
        }

        final IntStream streamer = IntStream.range(0, orderNumber).parallel();
        final IntConsumer consumer = FuzzUtil.probability(50)? orderByIndex(): orderByTarget();
        streamer.forEach(consumer);
    }
}
