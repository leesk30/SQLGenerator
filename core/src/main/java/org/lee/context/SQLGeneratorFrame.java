package org.lee.context;

import org.lee.common.Assertion;
import org.lee.common.NamedLoggers;
import org.lee.common.generator.Fuzzer;
import org.lee.common.utils.DebugUtils;
import org.lee.sql.statement.SQLStatement;
import org.lee.sql.type.TypeTag;
import org.slf4j.Logger;
import org.slf4j.MDC;

import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.UUID;
import java.util.function.Supplier;

final class SQLGeneratorFrame implements Fuzzer {
    private final Logger LOGGER = NamedLoggers.getCoreLogger(SQLGeneratorFrame.class);
    private final SQLGeneratorContext context;
    private final Stack<SQLGeneratorFrame> stack;
    private final SQLStatement statement;

    private final SQLGeneratorFrame previous;

    private final int depth;
    private final boolean root;


    private final UUID traceID;
    private final UUID frameID = UUID.randomUUID();

    SQLGeneratorFrame(SQLGeneratorContext context, Supplier<SQLStatement> supplier){
        this.context = context;
        this.stack = this.context.stack();
        this.depth = stack.size();
        this.root = depth == 0;
        this.previous = findPrevious();

        if(root){
            traceID = frameID;
        }else {
            Assertion.requiredNonNull(previous);
            assert previous != null;
            traceID = previous.traceID;
        }

        // put frame into stack at first
        stack.add(this);

        // Then consume the statement
        this.statement = supplier.get();

        Assertion.requiredNonNull(this.statement.retrieveContext() == context);

        this.fuzz();
    }

    private void setMDC(){
        MDC.put("traceID", DebugUtils.truncate(traceID));
        MDC.put("frameID", DebugUtils.truncate(frameID));
        // LOGGER.info(String.format("Start to build frame<%s> tracer<%s> for statement: %s.", traceID, frameID, name(statement)));
    }

    private void resetMDC(){
        if(previous == null){
            String contextDefault = DebugUtils.truncate(context.uuid());
            MDC.put("traceID", contextDefault);
            MDC.put("frameID", contextDefault);
        }else {
            MDC.put("traceID", DebugUtils.truncate(previous.traceID));
            MDC.put("frameID", DebugUtils.truncate(previous.frameID));
        }
    }

    private static String name(SQLStatement namedStatement){
        String[] names = namedStatement.getClass().getName().split("\\.");
        Assertion.requiredTrue(names.length > 0);
        return names[names.length - 1];
    }

    private SQLGeneratorFrame findPrevious(){
        if(root){
            return null;
        }
        return stack.get(depth - 1);
    }

    public SQLGeneratorContext context(){
        return context;
    }

    public SQLStatement statement(){
        return statement;
    }

    public int depth(){
        return depth;
    }

    public SQLGeneratorFrame previous(){
        return previous;
    }

    public SQLStatement previousStatement(){
        if(previous == null){
            return null;
        }
        return previous.statement;
    }

    @Override
    public void fuzz() {
        setMDC();
        statement.fuzz();
        resetMDC();
        this.stack.pop();
    }
}
