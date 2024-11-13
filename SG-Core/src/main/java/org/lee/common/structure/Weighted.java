package org.lee.common.structure;

public final class Weighted<W> {
    private final int weight;
    private final W something;

    public static <ANY> Weighted<ANY> create(ANY anything, int weight){
        return new Weighted<>(anything, weight);
    }

    public static <ANY> Weighted<ANY> create(ANY anything){
        return new Weighted<>(anything, 100);
    }

    public Weighted(W something, int weight){
        this.weight = weight;
        this.something = something;
    }

    public int weight(){
        return weight;
    }

    public W get(){
        return something;
    }
}
