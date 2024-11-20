package org.lee.context;

import org.lee.sql.statement.SQLStatement;

import java.util.Stack;

public class SQLGeneratorStack {

    private final Stack<SQLStatement> frame = new Stack<>();

    public SQLStatement peek() {
        if(frame.isEmpty()){
            return null;
        }
        return frame.peek();
    }

    public void put(SQLStatement statement) {
        frame.add(statement);
    }

    public SQLStatement pop() {
        if(frame.isEmpty()){
            throw new RuntimeException("Cannot remove from empty frame.");
        }
        return frame.pop();
    }

    public SQLStatement fuzzy(SQLStatement statement){
        if(peek() == statement){
            throw new RuntimeException("The statement has already in frame.");
        }
        put(statement);
        statement.fuzz();
        return pop();
    }


}
