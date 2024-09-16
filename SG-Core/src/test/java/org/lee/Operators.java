package org.lee;

import org.lee.symbol.Finder;
import org.lee.symbol.Operator;
import org.lee.type.TypeTag;

public class Operators {

    public static void main(String[] args) {
        Finder finder = Finder.getFinder();
        finder.getOperatorByReturn(TypeTag.null_);

    }
}
