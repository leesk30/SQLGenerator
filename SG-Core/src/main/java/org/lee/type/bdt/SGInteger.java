package org.lee.type.bdt;

import org.lee.type.TypeTag;
import org.lee.type.base.AbstractBasicDataType;
import org.lee.type.precision.FixedLen;
import org.lee.type.precision.TypePrecision;

public class SGInteger extends AbstractBasicDataType {
    private SGInteger(int sizeof) {
        super(TypeTag.INTEGER, new FixedLen(sizeof));
    }

    private final static SGInteger INSTANCE_INT1 = new SGInteger(TypePrecision.INT1_SIZE);
    private final static SGInteger INSTANCE_INT2 = new SGInteger(TypePrecision.INT2_SIZE);
    private final static SGInteger INSTANCE_INT4 = new SGInteger(TypePrecision.INT4_SIZE);
    private final static SGInteger INSTANCE_INT8 = new SGInteger(TypePrecision.INT8_SIZE);

    public static SGInteger getInt(){
        return INSTANCE_INT4;
    }
    public static SGInteger getLong(){
        return INSTANCE_INT8;
    }
    public static SGInteger getShort(){
        return INSTANCE_INT2;
    }
    public static SGInteger getSmall(){
        return INSTANCE_INT1;
    }
    public static SGInteger getInt1(){
        return INSTANCE_INT1;
    }
    public static SGInteger getInt2(){
        return INSTANCE_INT2;
    }
    public static SGInteger getInt4(){
        return INSTANCE_INT4;
    }
    public static SGInteger getInt8(){
        return INSTANCE_INT8;
    }
}
