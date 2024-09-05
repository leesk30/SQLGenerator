package org.lee.type.precision;

public interface TypePrecision {
    int VARLENA_FLAG = -1;
    int UNDEFINED_FLAG = -2;
    int NON_PRECISION_SIZE = 0;
    int MULTI_PRECISION_FLAG = -3;

    int INT1_SIZE = 1;
    int INT2_SIZE = 2;
    int INT4_SIZE = 4;
    int INT8_SIZE = 8;

    int FLOAT4_SIZE = 4;
    int FLOAT8_SIZE = 8;
    int DOUBLE_SIZE = 8;

    public static int CHAR_DEFAULT_SIZE = 1;
    public static int VARCHAR_DEFAULT_SIZE = -1;

    public static int DECIMAL_DEFAULT_TAIL = 0;

    default int getPrecisionSize(){ return 1;}
    default int getPrecision() {return  getPrecision(0);}
    int getPrecision(int index);
    boolean isUnlimitedLength();
}
