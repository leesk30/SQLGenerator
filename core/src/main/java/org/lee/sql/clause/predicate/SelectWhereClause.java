package org.lee.sql.clause.predicate;

import org.lee.common.enumeration.NodeTag;
import org.lee.sql.clause.from.FromClause;
import org.lee.sql.entry.RangeTableReference;
import org.lee.sql.statement.select.SelectStatement;

import java.util.ArrayList;
import java.util.List;

public final class SelectWhereClause extends WhereClause {

    private final WhereClause rawWhereClause = new WhereClause(statement);;
    private final List<JoinClause> joinClauseList = new ArrayList<>();

    public SelectWhereClause(SelectStatement statement) {
        super(statement);
    }

    // should be only called before fuzz
    private void initJoinClause(){
        final FromClause fromClause = (FromClause) statement.getClause(NodeTag.fromClause);
        final int length = fromClause.size();
        if(length <= 1){
            return;
        }

        for(int i = 0; i < fromClause.size(); i++){
            if(i+1 >= length){
                return;
            }
            final RangeTableReference left = fromClause.getChildNodes().get(i);
            final RangeTableReference right = fromClause.getChildNodes().get(i+1);
            joinClauseList.add(new WhereJoinClause(statement, left, right));
        }
    }

    @Override
    public void fuzz() {
        initJoinClause();
        for(JoinClause joinClause: this.joinClauseList){
            // combine implicit join filter
            joinClause.fuzz();
            this.filter.add(joinClause.filter);
        }
        // combine pure where
        this.rawWhereClause.fuzz();
        this.filter.add(this.rawWhereClause.filter);
        // combine self
        this.filter.fuzz();
    }

}
