package org.lee.type.precision;

/**
 * character is a specific type of var-len-array type.
 * */
public final class CharVarlena extends Varlena{
    public CharVarlena(int sizeof) {
        super(sizeof == VARLENA_FLAG ? CHAR_DEFAULT_SIZE : sizeof);
    }

    public CharVarlena(){
        super(CHAR_DEFAULT_SIZE);
    }
}
