package org.lee.statement.syntax;

import org.lee.statement.SQLStatement;
import org.lee.statement.SQLType;
import org.lee.statement.select.SelectStatement;
import org.lee.statement.select.SelectType;

public class SyntaxBuilder {
    private SQLType sqlType;
    private SQLType parentSqlType;
    private SelectType selectType;
    private SelectType parentSelectType;
    private int collectionRecursiveDepth = 0;
    private int subqueryRecursiveDepth = 0;

    public SyntaxBuilder(SQLType sqlType){
        this.sqlType = sqlType;
    }

    public SQLSyntax build(){
//        switch (sqlType){
//            case select:
//                SelectSyntax syntax = new SelectSyntax(sqlType);
//                syntax.set
//                return new SelectSyntax();
//        }
        return null;
    }

    public SyntaxBuilder withParentSqlType(SQLType sqlType){
        assert !this.sqlType.isDML();
        this.parentSqlType = sqlType;
        return this;
    }

    public SyntaxBuilder withParentSelectType(SelectType selectType){
        assert parentSqlType == SQLType.select;
        this.parentSelectType = selectType;
        return this;
    }

    public SyntaxBuilder withCollectionDepth(int depth){
        this.collectionRecursiveDepth = depth;
        return this;
    }

    public SyntaxBuilder withSubqueryDepth(int depth){
        this.subqueryRecursiveDepth = depth;
        return this;
    }

    public SQLSyntax byStatement(SQLStatement statement){
        SQLStatement parent = statement.getParent();
//        this.withSubqueryDepth();
        if(parent == null){
            return this.build();
        }
        SQLType parentSqlType = parent.getSqlType();
        this.withParentSqlType(parentSqlType);
        if(parentSqlType != SQLType.select){
            return this.build();
        }
        this.withParentSelectType(((SelectStatement)parent).getSelectType());
        return this.build();
    }

}
