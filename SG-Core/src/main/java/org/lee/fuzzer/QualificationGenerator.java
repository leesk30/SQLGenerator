package org.lee.fuzzer;

import org.lee.statement.expression.Qualification;

public abstract class QualificationGenerator implements Generator<Qualification> {
    protected int index = 0;
    abstract public Qualification generate();
}
