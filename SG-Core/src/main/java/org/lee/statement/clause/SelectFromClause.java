package org.lee.statement.clause;

import org.lee.common.DevTempConf;
import org.lee.common.MetaEntry;
import org.lee.statement.SQLStatement;
import org.lee.statement.ValuesStatement;
import org.lee.statement.common.Projectable;
import org.lee.statement.entry.relation.*;
import org.lee.statement.entry.RangeTableReference;
import org.lee.statement.node.NodeTag;
import org.lee.statement.complex.RTEJoin;
import org.lee.statement.select.SelectStatement;
import org.lee.util.FuzzUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class SelectFromClause extends FromClause{
    private List<List<RangeTableReference>> candidatesList;

    public SelectFromClause(SelectStatement statement){
        super(statement);

    }

    @Override
    public String getString() {
        return null;
    }

    @Override
    public NodeTag getNodeTag() {
        return null;
    }

    @Override
    public Iterator<RangeTableReference> walk() {
        return null;
    }

    @Override
    public void fuzz() {

        merge();
    }
}
