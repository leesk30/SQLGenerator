package org.lee.fuzzer.expr;

import org.lee.entry.scalar.Scalar;
import org.lee.util.ListUtil;

import java.util.List;
import java.util.Vector;
import java.util.stream.IntStream;

public abstract class UnrelatedQualificationGenerator implements QualificationGenerator {

    protected final List<Scalar> candidateList;
    protected UnrelatedQualificationGenerator(Scalar ... scalars){
        candidateList = new Vector<>(scalars.length);
        IntStream.range(0, scalars.length).parallel().forEach(i -> candidateList.add(i, scalars[i]));
    }

    protected UnrelatedQualificationGenerator(List<Scalar> scalars){
        candidateList = ListUtil.copyList(scalars);
    }

}
