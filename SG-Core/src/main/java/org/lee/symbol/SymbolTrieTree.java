package org.lee.symbol;

import org.lee.symbol.Signature;
import org.lee.type.TypeTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** package private */
public class SymbolTrieTree {

    private static class SymbolNode{
        private final int level;
        private final Map<TypeTag, SymbolNode> childrenMap = new HashMap<>();
        private final List<Signature> container = new ArrayList<>();

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
    }

    private final SymbolNode root = new SymbolNode();

    protected SymbolTrieTree(){}

    protected void put(Signature signature){
        // todo
    }

    protected List<Signature> get(List<TypeTag> types){
        return null;
    }

    protected List<Signature> get(TypeTag ... types){
        return null;
    }


}
