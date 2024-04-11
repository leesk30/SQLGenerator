package org.lee.util;

import java.util.Random;

public class RandomUtil {
    private static final Random randomer = new Random();

    public static boolean probability(int prob){
        return randomer.nextInt(100) <= prob;
    }
}
