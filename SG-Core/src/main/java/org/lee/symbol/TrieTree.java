package org.lee.symbol;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class TrieTree<K, V> {

    private static class TrieTreeNode<K, V> {
        private final int level;
        private final Map<K, TrieTreeNode<K, V>> childrenMap = new ConcurrentHashMap<>();
        private final List<V> container = new Vector<>();

        public TrieTreeNode() {
            this.level = 0;
        }

        private TrieTreeNode(final int level){
            this.level = level;
        }

        public final List<V> getContainer() {
            return container;
        }

        public final Map<K, TrieTreeNode<K, V>> getChildrenMap() {
            return childrenMap;
        }

        public void put(K typeTag){
            childrenMap.put(typeTag, new TrieTreeNode<K, V>(level+1));
        }

        public TrieTreeNode<K, V> get(K typeTag){
            return childrenMap.get(typeTag);
        }

        public int getMaxLevel(){
            if(childrenMap.isEmpty()){
                return level;
            }
            return childrenMap.values().stream().map(TrieTreeNode::getMaxLevel).max(Integer::compare).get();
        }
    }

    private final TrieTreeNode<K, V> root = new TrieTreeNode<>();

    public TrieTree(){}


    public void put(List<K> guidance, V value){
        List<V> container = getOrCreateContainer(guidance);
        container.add(value);
    }

    private List<V> getOrCreateContainer(List<K> guidance){
        TrieTreeNode<K, V> current = root;
        for(K guide: guidance){
            if(!current.getChildrenMap().containsKey(guide)){
                current.put(guide);
            }
            current = current.get(guide);
        }
        return current.getContainer();
    }

    private List<V> getContainer(Iterable<K> guidance){
        TrieTreeNode<K, V> current = root;
        for(K guide: guidance){
            if(!current.getChildrenMap().containsKey(guide)){
                return Collections.emptyList();
            }
            current = current.get(guide);
        }
        return current.getContainer();
    }

    public List<V> get(List<K> guidance){
        List<V> container = getContainer(guidance);
        if(container.isEmpty()){
            return Collections.emptyList();
        }
        return new Vector<>(container);
    }

    public final List<V> get(K singleGuide){
        TrieTreeNode<K, V> child = root.getChildrenMap().get(singleGuide);
        if(child == null){
            return Collections.emptyList();
        }
        return new Vector<>(child.getContainer());
    }

    public int maxWidth(){
        return root.getMaxLevel();
    }


}
