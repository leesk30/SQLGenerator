package org.lee.util;

import java.security.SecureRandom;

public class FuzzUtil {
    public static final SecureRandom randomer = new SecureRandom();

    public static boolean probability(int prob){
        return randomer.nextInt(100) <= prob;
    }
}
