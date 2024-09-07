package org.lee.common;

import org.json.JSONArray;
import org.json.JSONObject;
import org.lee.entry.relation.Relation;
import org.lee.entry.scalar.Field;
import org.lee.type.TypeDescriptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetaEntry {
    private static boolean isInitialized = false;
    public static final Map<String, List<Relation>> relationByNamespaceMap = new HashMap<>();
    public static final Map<String, Relation> relationMap = new HashMap<>();

    MetaEntry(){}
    public static synchronized void load(JSONObject json){
        if(isInitialized){
            return;
        }
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
        isInitialized = true;
    }

    public static Relation json2Relation(String namespace, JSONObject json){
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

    public static Field json2Field(JSONObject json){
        String fieldName = json.getString("name");
        String fieldTypeName = json.getString("type").trim().toLowerCase();
        // todo: add constraint
        TypeDescriptor typeDescriptor = TypeDescriptor.string2Descriptor(fieldTypeName);
        return new Field(fieldName, typeDescriptor);
    }


}
