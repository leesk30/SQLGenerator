package org.lee.testutils;

import org.lee.SQLGeneratorContext;
import org.lee.common.Utility;
import org.lee.common.global.Finder;
import org.lee.symbol.Signature;
import org.lee.type.TypeTag;

import java.util.ArrayList;
import java.util.List;

public class Convert {

    public static List<Signature> pathToSignature(TypeTag source, List<TypeTag> path){
        final Finder finder = SQLGeneratorContext.getCurrentFinder();
        final List<Signature> signatures = new ArrayList<>();
        for(TypeTag target: path){
            Signature signature = Utility.randomlyChooseFrom(finder.getCaster(source, target));
            source = target;
            signatures.add(signature);
        }
        return signatures;
    }
}
