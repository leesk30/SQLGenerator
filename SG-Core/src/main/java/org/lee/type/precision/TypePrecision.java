package org.lee.type.precision;

public interface TypePrecision {
    public static int VARLENA_FLAG = -1;
    public static int UNDEFINED_FLAG = -2;
    public static int NON_PRECISION_SIZE = 0;

    public static int INT1_SIZE = 1;
    public static int INT2_SIZE = 2;
    public static int INT4_SIZE = 4;
    public static int INT8_SIZE = 8;

    public static int FLOAT4_SIZE = 4;
    public static int FLOAT8_SIZE = 8;
    public static int DOUBLE_SIZE = 8;

    public static int CHAR_DEFAULT_SIZE = 1;
    public static int VARCHAR_DEFAULT_SIZE = -1;

    public static int DECIMAL_DEFAULT_TAIL = 0;

    default int getPrecisionSize(){ return 1;}
    default int getPrecision() {return  getPrecision(0);}
    int getPrecision(int index);
    boolean isUnlimitedLength();
}
