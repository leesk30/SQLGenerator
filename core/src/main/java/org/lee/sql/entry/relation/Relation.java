package org.lee.sql.entry.relation;

import org.apache.commons.lang3.StringUtils;
import org.lee.common.enumeration.NodeTag;
import org.lee.context.SQLGeneratorContext;
import org.lee.sql.entry.scalar.Field;
import org.lee.sql.statement.insert.InsertStatement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        for(int i=0; i<fields.size(); i++){
            pair[i] = fields.get(i).getString() + SPACE + fields.get(i).getDescriptor().toString();
        }
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

    public String toTemplateInsert(){
        String[] placeholders = new String[fields.size()];
        Arrays.fill(placeholders, "{}");
        return INSERT + SPACE + INTO + SPACE + getName() + SPACE + LP + nodeArrayToString(",", fields) + RP
                + SPACE + VALUES  + SPACE + LP + StringUtils.joinWith(", ", placeholders) + RP + ENDING;
    }

    public InsertStatement getInsertStatement(SQLGeneratorContext context, int maxNumOfValueLines){
        return context.generateInsert(this, maxNumOfValueLines);
    }
}
