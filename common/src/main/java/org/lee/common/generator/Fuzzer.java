package org.lee.common.generator;

/*
 * The Fuzzer is a specific generator.
 *
 * public interface Fuzzer extends Generator<Fuzzer> {
 *     void fuzz();
 *
 *     default Fuzzer generate(){
 *         this.fuzz();
 *         return this;
 *     }
 *  }
 */

public interface Fuzzer {
    void fuzz();
}
