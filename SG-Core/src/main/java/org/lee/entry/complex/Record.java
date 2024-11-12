package org.lee.entry.complex;

import org.lee.base.Node;
import org.lee.base.NodeTag;
import org.lee.entry.scalar.Scalar;
import org.lee.type.TypeTag;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Record extends ArrayList<Scalar> implements Node {

    public Record(int width){
        super(width);
    }

    public Record(List<Scalar> vector){
        super(vector);
    }

    @Override
    public String getString() {
        return LP + nodeArrayToString(", ", this) + RP;
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.record;
    }

    public Scalar toScalar(){
        if(this.size() == 1)
            return this.get(0);
        return AdaptiveRecordScalar.adaptScalarList(this);
    }

    public AdaptiveRecordScalar toAdaptiveScalar(){
        return AdaptiveRecordScalar.adaptScalarList(this);
    }

    public List<TypeTag> getTypeList(){
        return this.stream().map(Scalar::getType).collect(Collectors.toList());
    }
}
