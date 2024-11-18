package org.lee.resource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.lee.common.NamedLoggers;
import org.lee.common.utils.RandomUtils;
import org.lee.sql.entry.relation.Relation;
import org.lee.sql.entry.scalar.Field;
import org.lee.sql.type.TypeDescriptor;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;


public class MetaEntry implements Resource<JSONObject> {
    private boolean isInitialized = false;
    private final Map<String, List<Relation>> relationByNamespaceMap = new HashMap<>();
    private final Map<String, Relation> relationMap = new HashMap<>();
    private static final Logger LOGGER = NamedLoggers.getCoreLogger(MetaEntry.class);

    public MetaEntry(){}

    public Map<String, List<Relation>> getRelationByNamespaceMap() {
        return relationByNamespaceMap;
    }

    public Map<String, Relation> getRelationMap() {
        return relationMap;
    }

    @Override
    public synchronized void init(JSONObject json){
        if(isInitialized){
            LOGGER.warn("The meta entry has already been initialized!!!");
            return;
        }
        long start = System.currentTimeMillis();
        for(String namespace: json.keySet()){
            JSONArray relationList = json.getJSONArray(namespace);
            for(int i=0; i< relationList.length(); i++){
                JSONObject relationJson = relationList.getJSONObject(i);
                Relation relation = json2Relation(namespace, relationJson);
                relationMap.put(relation.getName(), relation);
                if(!relationByNamespaceMap.containsKey(namespace)){
                    relationByNamespaceMap.put(namespace, new ArrayList<>());
                }
                relationByNamespaceMap.get(namespace).add(relation);
            }
        }
        LOGGER.info(String.format("MetaEntry initialized %d items. Elapsed time: %d ms", relationMap.size(), System.currentTimeMillis() - start));
        isInitialized = true;
    }

    public Relation json2Relation(String namespace, JSONObject json){
        String identifier = json.getString("identifier");
        JSONArray schemaList = json.getJSONArray("schema");
        List<Field> fieldList = new ArrayList<>(schemaList.length());
        for(int i=0; i<schemaList.length(); ++i){
            JSONObject fieldJson = schemaList.getJSONObject(i);
            Field field = json2Field(fieldJson);
            fieldList.add(field);
        }
        Relation relation = new Relation(namespace, identifier, fieldList);
        // todo: handle primary key and constraint
        return relation;
    }

    public Field json2Field(JSONObject json){
        String fieldName = json.getString("name");
        String fieldTypeName = json.getString("type").trim().toLowerCase();
        // todo: add constraint
        TypeDescriptor typeDescriptor = TypeDescriptor.string2Descriptor(fieldTypeName);
        return new Field(fieldName, typeDescriptor);
    }

    public String toDDLs(final boolean ignoreIfExists, final String options){
        StringBuilder builder = new StringBuilder();
        for(String databaseName: relationByNamespaceMap.keySet()){
            builder.append("CREATE DATABASE ");
            if(ignoreIfExists){
                builder.append("IF NOT EXISTS ");
            }
            builder.append(databaseName).append(";");
        }
        relationMap.values().forEach(r -> builder.append(r.toDDL(ignoreIfExists, options)).append("\n"));
        return builder.toString();
    }

    public String toInitializedInserts(){
        StringBuilder builder = new StringBuilder();
        relationMap.values().forEach(
                r -> {
                    final int numOfInsertStatement = RandomUtils.randomIntFromRange(10, 20);
                    IntStream.range(0, numOfInsertStatement).sequential().forEach(
                            i -> builder.append(r.getInitializedInsert(3)).append("\n")
                    );
                }
        );
        return builder.toString();
    }

}
