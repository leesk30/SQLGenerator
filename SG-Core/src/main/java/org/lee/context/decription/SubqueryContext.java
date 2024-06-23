package org.lee.context.decription;

public class SubqueryContext extends SelectContext{
    @Override
    public int getRecursionLevel() {
        return 0;
    }

    @Override
    public int getCollectionLevel() {
        return 0;
    }
}
