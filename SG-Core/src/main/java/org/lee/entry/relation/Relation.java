package org.lee.entry.relation;

import org.apache.commons.lang3.StringUtils;
import org.lee.base.NodeTag;
import org.lee.entry.scalar.Field;
import org.lee.statement.insert.InsertInitializedStatement;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.stream.IntStream;

public class Relation implements RangeTableEntry {

    private final String namespace;
    private final String name;
    private final List<Field> fields;
    private final List<Partition> partitions = new ArrayList<>();

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
        return namespace != null ? namespace + "." + name: name;
    }

    public List<Partition> getPartitions(){
        return partitions;
    }

    private String toDDLStyleFieldWithType(){
        final String[] pair = new String[fields.size()];
        IntStream.range(0, fields.size()).sequential()
                .forEach(i -> pair[i] = fields.get(i).getString() + SPACE + fields.get(i).getDescriptor().toString());
        return concatWithComma(pair);
    }

    public String toDDL(boolean addIgnore, String ddlOptions){
        String result = concatWithSpace(
                concatWithSpace(CREATE, TABLE, addIgnore ? concatWithSpace(IF, NOT, EXISTS, EMPTY):EMPTY),
                getName(),
                LP,
                toDDLStyleFieldWithType(),
                RP
        );
        if(!StringUtils.isEmpty(ddlOptions) && !StringUtils.isBlank(ddlOptions)){
            return concatWithSpace(result, ddlOptions) + ENDING;
        }
        return result + ENDING;
    }

    public String getInitializedInsert(int maxNumOfValueLines){
        return new InsertInitializedStatement(this, maxNumOfValueLines).getString();
    }
}
