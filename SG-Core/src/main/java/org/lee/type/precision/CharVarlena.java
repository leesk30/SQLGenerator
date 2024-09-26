package org.lee.type.precision;

import org.lee.node.Node;

/**
 * character is a specific type of var-len-array type.
 * */
public final class CharVarlena extends Varlena{

    public final static CharVarlena DEFAULT_CHAR_PRECISION = new CharVarlena(1);
    public CharVarlena(int sizeof) {
        super(sizeof == VARLENA_FLAG ? CHAR_DEFAULT_SIZE : sizeof);
    }

    public CharVarlena(){
        super(CHAR_DEFAULT_SIZE);
    }

    @Override
    public String toString() {
        if(sizeof == VARLENA_FLAG || sizeof < 0){
            return Node.LP + 1 + Node.RP;
        }
        return Node.LP + sizeof + Node.RP;
    }
}
