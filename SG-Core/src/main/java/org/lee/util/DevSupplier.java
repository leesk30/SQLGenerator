package org.lee.util;

import java.util.function.Supplier;

public class DevSupplier {
    public static final Supplier<RuntimeException> impossible = ()-> new RuntimeException("[BUG]Cannot be null here");
}
