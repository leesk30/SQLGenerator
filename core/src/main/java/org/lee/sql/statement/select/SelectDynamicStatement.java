package org.lee.sql.statement.select;

import org.lee.common.enumeration.NodeTag;
import org.lee.context.SQLGeneratorContext;
import org.lee.sql.clause.from.FromClause;
import org.lee.sql.entry.complex.TargetEntry;
import org.lee.sql.entry.relation.RangeTableEntry;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class SelectDynamicStatement extends SelectStatement{

    private List<TargetEntry> projection;
    private List<RangeTableEntry> rawEntryList;
    private boolean withParentheses = false;
    private boolean isScalar = false;
    private String overwriteRawBody;

    public static SelectStatement fromEntry(RangeTableEntry entry){
        SelectDynamicStatement statement = new SelectDynamicStatement();
        statement.setWithParentheses(true);
        statement.setProjection(entry.getFields().stream().map(field -> new TargetEntry(field.asNameProxy())).collect(Collectors.toList()));
        statement.setRawEntryList(Collections.singletonList(entry));
        statement.setOverwriteRawBody("SELECT * FROM " + entry.getString());
        statement.setScalar(false);
        return statement;
    }

    private SelectDynamicStatement() {
        this(null);
    }

    private SelectDynamicStatement(SQLGeneratorContext context) {
        super(SelectType.dynamic, context);
    }

    private String rawBody(){
        return overwriteRawBody;
    }


    @Override
    public List<TargetEntry> project() {
        return projection;
    }

    @Override
    public boolean isWithLogicalParentheses() {
        // always be true
        return withParentheses;
    }

    @Override
    public List<RangeTableEntry> getRawRTEList() {
        if(rawEntryList != null){
            return rawEntryList;
        }
        FromClause fromClause = ((FromClause)getClause(NodeTag.fromClause));
        if(fromClause == null){
            logger.error("The from clause from dynamic statement is empty");
            return Collections.emptyList();
        }
        return fromClause.getRawEntryList();
    }

    @Override
    public void fuzz() {
        // do nothing
    }

    @Override
    public boolean isScalar() {
        return isScalar;
    }

    @Override
    public String getString() {
        if(isWithLogicalParentheses()){
            return LP + overwriteRawBody + RP;
        }
        return overwriteRawBody;
    }

    public void setOverwriteRawBody(String overwriteRawBody) {
        this.overwriteRawBody = overwriteRawBody;
    }

    public void setProjection(List<TargetEntry> projection) {
        this.projection = projection;
    }

    public void setRawEntryList(List<RangeTableEntry> rawEntryList) {
        this.rawEntryList = rawEntryList;
    }

    public void setWithParentheses(boolean withParentheses) {
        this.withParentheses = withParentheses;
    }

    public void setScalar(boolean scalar) {
        isScalar = scalar;
    }
}
