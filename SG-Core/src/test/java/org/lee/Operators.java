package org.lee;

import org.lee.common.global.Finder;
import org.lee.type.TypeTag;

public class Operators {

    public static void main(String[] args) {
        Finder finder = SQLGeneratorContext.getCurrentFinder();
        finder.getOperatorByReturn(TypeTag.null_);

    }
}
