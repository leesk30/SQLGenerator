package org.lee.sql.clause.sort;

import org.lee.common.Assertion;
import org.lee.common.NamedLoggers;
import org.lee.common.config.RuntimeConfiguration;
import org.lee.common.enumeration.Conf;
import org.lee.common.enumeration.NodeTag;
import org.lee.common.enumeration.Rule;
import org.lee.common.generator.Fuzzer;
import org.lee.common.utils.RandomUtils;
import org.lee.sql.clause.Clause;
import org.lee.sql.entry.Normalized;
import org.lee.sql.entry.complex.TargetEntry;
import org.lee.sql.entry.scalar.Scalar;
import org.lee.sql.literal.Literal;
import org.lee.sql.literal.LiteralInt;
import org.lee.sql.statement.Projectable;
import org.lee.sql.statement.SQLStatement;
import org.lee.sql.statement.Sortable;
import org.lee.sql.support.Alias;
import org.lee.sql.type.TypeTag;
import org.slf4j.Logger;

import java.util.List;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

public abstract class SortByClause extends Clause<SortByClause.SortEntry> {
    private final static Logger LOGGER = NamedLoggers.getCoreLogger(SortByClause.class);

    public SortByClause(SQLStatement statement) {
        super(statement);
    }

    protected static class SortEntry implements Normalized<Scalar>, Scalar, Fuzzer {

        private final Scalar scalar;
        private final boolean isDefaultAsc;
        private final boolean isDefaultNullLast;
        private final boolean supportWithProjectionAlias;
        // runtime mutable options
        private boolean orderOptionAsc;
        private boolean nullOptionLast;
        private boolean representOrderOption;
        private boolean representNullOption;

        public SortEntry(Scalar scalar, RuntimeConfiguration config){
            this.scalar = scalar;
            this.isDefaultAsc = !config.confirm(Rule.ORDER_DEFAULT_DESC);
            this.isDefaultNullLast = !config.confirm(Rule.ORDER_DEFAULT_NULL_FIRST);
            this.supportWithProjectionAlias = config.confirm(Rule.ENABLE_FILTER_USING_PROJECTION_ALIAS);
        }

        @Override
        public TypeTag getType() {
            return scalar.getType();
        }

        @Override
        public String getString() {
            StringBuilder builder = new StringBuilder();
            builder.append(getSortEntryBody());
            if(representOrderOption){
                builder.append(orderOptionAsc ? SPACE + ASC : SPACE + DESC);
            }
            if(representNullOption){
                builder.append(nullOptionLast ? SPACE + NULLS + SPACE + LAST : SPACE + NULLS + SPACE + FIRST);
            }
            return builder.toString();
        }

        @Override
        public NodeTag getNodeTag() {
            return NodeTag.scalar;
        }

        private String getSortEntryBody(){
            if(scalar instanceof Alias){
                Alias alias = ((Alias) scalar);
                if(supportWithProjectionAlias){
                    return alias.getAlias();
                }else {
                    return alias.getStringWithoutAlias();
                }
            }
            return scalar.getString();
        }

        @Override
        public void fuzz() {
            orderOptionAsc = RandomUtils.probability(50);
            nullOptionLast = RandomUtils.probability(50);
            if((isDefaultAsc && orderOptionAsc) || (!isDefaultAsc && !orderOptionAsc)){
                representOrderOption = RandomUtils.probability(50);
            }else {
                representOrderOption = true;
            }

            if((isDefaultNullLast && nullOptionLast) || (!isDefaultNullLast && !nullOptionLast)){
                representNullOption = RandomUtils.probability(50);
            }else {
                representNullOption = true;
            }
        }

        public boolean isRepresentNullOption() {
            return representNullOption;
        }

        public boolean isRepresentOrderOption() {
            return representOrderOption;
        }

        public boolean isAsc(){
            return orderOptionAsc;
        }

        public boolean isNullLast(){
            return nullOptionLast;
        }

        @Override
        public Scalar getWrapped() {
            return scalar;
        }

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

    protected void addSortEntry(LiteralInt sortIndex){
        addSortEntry(new SortEntry(sortIndex, this.statement.getConfig()));
    }

    protected IntConsumer randomlyOrderByIndex(){
        final Projectable projectable = (Projectable) this.statement;
        final int projectionLength = projectable.width();
        return i -> {
            final int orderByIndex = RandomUtils.randomIntFromRange(1, projectionLength+1);
            final Literal<Integer> literal = new LiteralInt(orderByIndex);
            addSortEntry(new SortEntry(literal, this.statement.getConfig()));
        };
    }

    protected IntConsumer randomlyOrderByTarget(){
        final Projectable projectable = (Projectable) this.statement;
        final List<TargetEntry> targetEntries = projectable.project();
        final RuntimeConfiguration configuration = projectable.getConfig();
        return i -> {
            final TargetEntry targetEntry = RandomUtils.randomlyChooseFrom(targetEntries);
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
        return IntStream.range(0, RandomUtils.randomIntFromRange(1, (int)(1.5D * getProjectSize()))).sequential();
    }

    protected IntConsumer getConsumer(){
        return RandomUtils.probability(50)? randomlyOrderByIndex(): randomlyOrderByTarget();
    }

    protected void forceOrderByAllProjections(){
        for (int i = 1; i < getProjectSize() + 1; i++) {
            addSortEntry(new LiteralInt(i));
        }
    }

    @Override
    public void fuzz(){
        if(statement.confirm(Rule.REWRITER_REORDER)){
            // LOGGER.info("SortClause using rewrite-reorder to generate orderBy.");
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
