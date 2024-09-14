package org.lee.symbol;

import org.lee.symbol.Signature;
import org.lee.type.TypeTag;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/** package private */
public class SymbolTrieTree {

    private static class SymbolNode{
        private final int level;
        private final Map<TypeTag, SymbolNode> childrenMap = new ConcurrentHashMap<>();
        private final List<Signature> container = new Vector<>();

        public SymbolNode() {
            this.level = 0;
        }

        private SymbolNode(final int level){
            this.level = level;
        }

        public final List<Signature> getContainer() {
            return container;
        }

        public final Map<TypeTag, SymbolNode> getChildrenMap() {
            return childrenMap;
        }

        public void put(TypeTag typeTag){
            childrenMap.put(typeTag, new SymbolNode(level+1));
        }

        public SymbolNode get(TypeTag typeTag){
            return childrenMap.get(typeTag);
        }

        public int getMaxLevel(){
            if(childrenMap.isEmpty()){
                return level;
            }
            return childrenMap.values().stream().map(SymbolNode::getMaxLevel).max(Integer::compare).get();
        }
    }

    private final SymbolNode root = new SymbolNode();

    protected SymbolTrieTree(){}

    protected void put(Signature signature){
        final List<TypeTag> guidance = signature.getArgumentsTypes();
        assert signature.argsNum() == guidance.size();
        List<Signature> container = getOrCreateContainer(guidance);
        container.add(signature);
    }

    private List<Signature> getOrCreateContainer(List<TypeTag> guidance){
        SymbolNode current = root;
        for(TypeTag guide: guidance){
            if(!current.childrenMap.containsKey(guide)){
                current.put(guide);
            }
            current = current.get(guide);
        }
        return current.container;
    }

    private List<Signature> getContainer(Iterable<TypeTag> guidance){
        SymbolNode current = root;
        for(TypeTag guide: guidance){
            if(!current.childrenMap.containsKey(guide)){
                return Collections.emptyList();
            }
            current = current.get(guide);
        }
        return current.container;
    }

    protected List<Signature> get(List<TypeTag> types){
        List<Signature> container = getContainer(types);
        if(container.isEmpty()){
            return Collections.emptyList();
        }
        return new Vector<>(container);
    }

    protected List<Signature> get(TypeTag ... types){
        return get(Arrays.asList(types));
    }

    public int maxWidth(){
        return root.getMaxLevel();
    }


}
