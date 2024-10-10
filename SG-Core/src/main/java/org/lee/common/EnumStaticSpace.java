package org.lee.common;

import org.lee.type.TypeTag;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EnumStaticSpace {
    public final static List<TypeTag> ARG_NUM_1 = Collections.singletonList(TypeTag.boolean_);
    public final static List<TypeTag> ARG_NUM_2 = Arrays.asList(TypeTag.boolean_, TypeTag.boolean_);
}
