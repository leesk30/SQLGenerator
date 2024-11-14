package org.lee.metrics;

// TODO: collect how many times some method we access and how much entry we contains in statement during fuzzing
public interface Metrics {
    MetricsKind getKind();
}
