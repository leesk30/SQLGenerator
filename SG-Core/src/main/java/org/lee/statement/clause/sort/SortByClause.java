package org.lee.statement.clause.sort;

import org.lee.common.DevTempConf;
import org.lee.entry.complex.SortEntry;
import org.lee.entry.complex.TargetEntry;
import org.lee.entry.literal.Literal;
import org.lee.entry.literal.LiteralInt;
import org.lee.rules.RuleName;
import org.lee.rules.RuleSet;
import org.lee.statement.SQLStatement;
import org.lee.node.NodeTag;
import org.lee.statement.ValuesStatement;
import org.lee.statement.clause.Clause;
import org.lee.statement.select.SelectStatement;
import org.lee.statement.support.Projectable;
import org.lee.statement.support.Sortable;
import org.lee.util.FuzzUtil;

import java.util.List;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

public abstract class SortByClause extends Clause<SortEntry> {
    public SortByClause(SQLStatement statement) {
        super(statement);
    }

    @Override
    public String getString() {
        return ORDER + SPACE + BY + SPACE + nodeArrayToString(children);
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.sortByClause;
    }

    protected IntConsumer orderByIndex(){
        final Projectable projectable = (Projectable) this.statement;
        final int projectionLength = projectable.width();
        return i -> {
            final int orderByIndex = FuzzUtil.randomIntFromRange(1, projectionLength+1);
            final Literal<Integer> literal = new LiteralInt(orderByIndex);
            final SortEntry sortEntry = new SortEntry(literal, this.statement.getRuleSet());
            sortEntry.fuzz();
            children.add(sortEntry);
        };
    }

    protected IntConsumer orderByTarget(){
        final Projectable projectable = (Projectable) this.statement;
        final List<TargetEntry> targetEntries = projectable.project();
        final RuleSet ruleSet = ((SQLStatement)projectable).getRuleSet();
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
        assert this.statement instanceof Projectable;
        final Projectable projectable = (Projectable) this.statement;
        final int projectionLength = projectable.width();
        if(projectionLength == 0){
            throw new RuntimeException("The Projection length cannot be zero.");
        }
        final int orderNumber = FuzzUtil.randomIntFromRange(1, (int)(1.5D * projectionLength));
        final RuleSet ruleSetRef = this.statement.getRuleSet();

        if(ruleSetRef.confirm(RuleName.REQUIRE_SCALA)){
            final SortEntry sortEntry = new SortEntry(new LiteralInt(1), ruleSetRef);
            sortEntry.fuzz();
            children.add(sortEntry);
            return;
        }

        final IntStream streamer = IntStream.range(0, orderNumber).parallel();
        final IntConsumer consumer = FuzzUtil.probability(50) ? orderByIndex(): orderByTarget();
        streamer.forEach(consumer);
    }

}
