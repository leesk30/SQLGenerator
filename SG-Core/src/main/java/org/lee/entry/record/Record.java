package org.lee.entry.record;

import org.lee.entry.scalar.Scalar;
import org.lee.node.Node;
import org.lee.node.NodeTag;

import java.util.List;
import java.util.Vector;

public class Record extends Vector<Scalar> implements Node {

    public Record(int width){
        super(width);
    }

    public Record(List<Scalar> vector){
        super(vector);
    }

    public int getWidth() {
        return size();
    }

    @Override
    public String getString() {
        return LP + nodeArrayToString(", ", (Node[])this.elementData) + RP;
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.record;
    }
}
