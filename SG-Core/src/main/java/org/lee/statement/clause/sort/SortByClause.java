package org.lee.statement.clause.sort;

import org.lee.base.NodeTag;
import org.lee.common.Assertion;
import org.lee.common.Utility;
import org.lee.common.config.Conf;
import org.lee.common.config.Rule;
import org.lee.common.config.RuntimeConfiguration;
import org.lee.entry.complex.SortEntry;
import org.lee.entry.complex.TargetEntry;
import org.lee.statement.clause.Clause;
import org.lee.statement.support.Projectable;
import org.lee.statement.support.SQLStatement;
import org.lee.statement.support.Sortable;
import org.lee.type.literal.Literal;
import org.lee.type.literal.LiteralInt;

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

    protected void addSortEntry(SortEntry sortEntry){
        sortEntry.fuzz();
        children.add(sortEntry);
    }

    protected IntConsumer randomlyOrderByIndex(){
        final Projectable projectable = (Projectable) this.statement;
        final int projectionLength = projectable.width();
        return i -> {
            final int orderByIndex = Utility.randomIntFromRange(1, projectionLength+1);
            final Literal<Integer> literal = new LiteralInt(orderByIndex);
            addSortEntry(new SortEntry(literal, this.statement.getConfig()));
        };
    }

    protected IntConsumer randomlyOrderByTarget(){
        final Projectable projectable = (Projectable) this.statement;
        final List<TargetEntry> targetEntries = projectable.project();
        final RuntimeConfiguration configuration = projectable.getConfig();
        return i -> {
            final TargetEntry targetEntry = Utility.randomlyChooseFrom(targetEntries);
            addSortEntry(new SortEntry(targetEntry, configuration));
        };
    }

    protected void orderByScalar(){
        final SortEntry sortEntry = new SortEntry(new LiteralInt(1), this.statement.getConfig());
        sortEntry.fuzz();
        children.add(sortEntry);
    }

    protected boolean requireSort(){
        if(probability(Conf.SORT_BY_CLAUSE_FUZZ_PROBABILITY)){
            Assertion.requiredTrue(this.statement instanceof Sortable);
            Assertion.requiredTrue(this.statement instanceof Projectable);
            return true;
        }
        return false;
    }

    protected int getProjectSize(){
        final Projectable projectable = (Projectable) this.statement;
        final int projectionLength = projectable.width();
        Assertion.requiredNonEquals(projectionLength, 0);
        return projectionLength;
    }

    protected IntStream getStreamer(){
        return IntStream.range(0, Utility.randomIntFromRange(1, (int)(1.5D * getProjectSize()))).sequential();
    }

    protected IntConsumer getConsumer(){
        return Utility.probability(50)? randomlyOrderByIndex(): randomlyOrderByTarget();
    }

    protected void forceOrderByAllProjections(){
        for (int i = 1; i < getProjectSize() + 1; i++) {
            addSortEntry(new SortEntry(new LiteralInt(i), this.statement.getConfig()));
        }
    }

    @Override
    public void fuzz(){
        if(statement.confirm(Rule.REWRITER_REORDER)){
            logger.info("SortClause using rewrite-reorder to generate orderBy.");
            forceOrderByAllProjections();
            return;
        }

        if(requireSort()){
            if(this.statement.confirm(Rule.REQUIRE_SCALA)){
                orderByScalar();
                return;
            }
            getStreamer().forEach(getConsumer());
        }
    }

}
