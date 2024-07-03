package org.lee.common;

import org.json.JSONObject;
import org.lee.statement.entry.relation.Relation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetaEntry {
    private static MetaEntry singleton = new MetaEntry();

    private final Map<String, List<Relation>> relationMap;

    public static MetaEntry getSingleton() {
        return singleton;
    }

    private MetaEntry(){
        relationMap = new HashMap<>();
    }

    public void load(JSONObject json){

    }

}
