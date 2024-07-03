package org.lee.statement.entry.relation;

import org.lee.statement.node.NodeTag;
import org.lee.statement.entry.scalar.Field;

import java.util.List;

public class Relation implements RangeTableEntry {

    private final String namespace;
    private final String name;
    private final List<Field> fields;

    public Relation(String namespace, String name, List<Field> fields){
        this.namespace = namespace;
        this.name = name;
        this.fields = fields;
    }

    public Relation(String nameWithNamespace, List<Field> fields){
        int splitLocate = nameWithNamespace.lastIndexOf(".");
        this.namespace = nameWithNamespace.substring(0, splitLocate);
        this.name = nameWithNamespace.substring(splitLocate+1);
        this.fields = fields;
    }

    @Override
    public String getString() {
        return String.format("%s.%s", namespace, name);
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.relation;
    }

    @Override
    public List<Field> getFields() {
        return fields;
    }

    @Override
    public String getName() {
        return name;
    }
}
