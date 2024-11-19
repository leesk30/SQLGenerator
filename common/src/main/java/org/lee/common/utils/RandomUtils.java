package org.lee.common.utils;

import org.lee.common.NamedLoggers;
import org.slf4j.Logger;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

public class RandomUtils {
    public static final SecureRandom secureRandom;
    public static final int ACTUALLY_MAX_STRINGS;
    public static final char[] charset;
    public static final String strings;
    public static final long timestamps;
    public static final long timestampsRange;  // 100 days
    public static final int MAX_STRINGS;
    public static final Logger LOGGER;

    static {
        LOGGER = NamedLoggers.getCommonLogger(RandomUtils.class);
        timestamps = 1720000000000L;
        timestampsRange = 100 * 24 * 3600000L;
        secureRandom = new SecureRandom();
        MAX_STRINGS = 100;
        charset = new char[]{
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'g', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'G', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        };
        String stringFromFile = IOUtils.inputStreamToString(RandomUtils.class.getClassLoader().getResourceAsStream("strings.txt"));
        ACTUALLY_MAX_STRINGS = Math.max(stringFromFile.length(), MAX_STRINGS);
        strings = stringFromFile.substring(0, RandomUtils.ACTUALLY_MAX_STRINGS);
    }

    public static boolean probability(int prob){
        return secureRandom.nextInt(100) < prob;
    }

    public static <T> T pop(List<T> list){
        if(list == null || list.isEmpty()){
            return null;
        }
        return list.remove(0);
    }

    public static <T> T randomlyChooseFrom(List<T> list){
        if(list == null || list.isEmpty()){
            return null;
        }
        return list.get(secureRandom.nextInt(list.size()));
    }

    public static <T> T randomlyChooseFrom(Set<T> set){
        if(set == null || set.isEmpty()){
            return null;
        }
        return randomlyChooseFrom(new ArrayList<>(set));
    }

    public static <T> T randomlyChooseFrom(T[] arr){
        if(arr == null || arr.length == 0){
            return null;
        }
        return arr[secureRandom.nextInt(arr.length)];
    }

    public static int randomlyChooseFrom(int[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }
        return arr[secureRandom.nextInt(arr.length)];
    }

    public static int randomIntFromRange(int beginInclusive, int endExclusive){
        if(checkBeginAndEndIsInvalid(beginInclusive, endExclusive)){
            return beginInclusive;
        }
        return secureRandom.nextInt(endExclusive - beginInclusive) + beginInclusive;
    }

    public static long randomLongFromRange(long beginInclusive, long endExclusive){
        if(checkBeginAndEndIsInvalid(beginInclusive, endExclusive)){
            return beginInclusive;
        }
        long interval = endExclusive - beginInclusive;
        if(interval / Integer.MAX_VALUE > 0){
            return Integer.MAX_VALUE + randomLongFromRange(0, interval - Integer.MAX_VALUE);
        }
        return secureRandom.nextInt((int) interval) + beginInclusive;
    }

    public static BigDecimal randomDecimalFromRange(int beginInclusive, int endExclusive){
        if(checkBeginAndEndIsInvalid(beginInclusive, endExclusive)){
            return new BigDecimal(beginInclusive);
        }
        String intPart = String.valueOf(secureRandom.nextInt(endExclusive - beginInclusive) + beginInclusive);
        String floatPart = new StringBuffer(String.valueOf(secureRandom.nextInt(100000000))).reverse().toString();
        return new BigDecimal(intPart + "." + floatPart);
    }

    public static double randomDoubleFromRange(int beginInclusive, int endExclusive){
        if(checkBeginAndEndIsInvalid(beginInclusive, endExclusive)){
            return (double) beginInclusive;
        }
        String intPart = String.valueOf(secureRandom.nextInt(endExclusive - beginInclusive) + beginInclusive);
        String floatPart = new StringBuffer(String.valueOf(secureRandom.nextInt(100000000))).reverse().toString();
        return Double.parseDouble( intPart + "." + floatPart);
    }

    public static String randomStringByLength(int maxLength){
        int candidateLength = Math.max(1, randomIntFromRange(1, maxLength+1));
        StringBuilder builder = new StringBuilder();
        while (candidateLength > ACTUALLY_MAX_STRINGS){
            builder.append(randomStringByLength(candidateLength % ACTUALLY_MAX_STRINGS));
            candidateLength -= ACTUALLY_MAX_STRINGS;
        }
        final int startAt = randomIntFromRange(0, ACTUALLY_MAX_STRINGS - candidateLength);
        builder.append(strings, startAt, startAt + candidateLength);
        return builder.toString();
    }

    public static Timestamp randomTimestamp(int partial){
        long absInterval = randomLongFromRange(0, timestampsRange);
        long interval = probability(50) ? absInterval: -absInterval;
        return new Timestamp(timestamps + partial + interval);
    }

    public static java.sql.Date randomDate(int partial){
        long absInterval = randomLongFromRange(0, timestampsRange);
        long interval = probability(50) ? absInterval: -absInterval;
        return new Date(timestamps + partial + interval);
    }

    public static String getRandomName(String prefix){
        return prefix + UUID.randomUUID().toString().replace("-", "").substring(0, 6);
    }

    private static boolean checkBeginAndEndIsInvalid(int beginInclusive, int endExclusive){
        if(beginInclusive == endExclusive) return true;
        if(beginInclusive > endExclusive)
            throw new IndexOutOfBoundsException(String.format("The end index must be greater than begin index. BeginInclusive: %d, EndExclusive: %d", beginInclusive, endExclusive));
        return false;
    }

    private static boolean checkBeginAndEndIsInvalid(long beginInclusive, long endExclusive){
        if(beginInclusive == endExclusive) return true;
        if(beginInclusive > endExclusive) throw new IndexOutOfBoundsException(String.format("The end index must be greater than begin index. BeginInclusive: %d, EndExclusive: %d", beginInclusive, endExclusive));
        return false;
    }

    public static int getBinomialDistributionRandomFromRange(int prob, int beginInclusive, int endExclusive){
        // TODO: There might be some bugs in calculating of edge.
        if(checkBeginAndEndIsInvalid(beginInclusive, endExclusive)){
            return beginInclusive;
        }
        int edge = endExclusive - beginInclusive;
        // return beginInclusive + randomDataGenerator.nextBinomial(edge, (double) prob / 100D);
        return 0;
    }

    public static <T> List<T> randomlyChooseManyFrom(int n, Collection<T> collection){
        if(n < 1){
            // todo
            throw new RuntimeException();
        }
        if(collection.size() < n){
            throw new IndexOutOfBoundsException("Too many element for selection");
        }
        List<T> copied = new ArrayList<>(collection);
        Collections.shuffle(copied);
        return copied.subList(0, n);
    }

    public static <T> T randomlyPop(List<T> list){
        if(list.isEmpty()){
            return null;
        }
        int index = randomIntFromRange(0, list.size());
        return list.remove(index);
    }

    public static <T> T randomlyPop(Set<T> set){
        if(set.isEmpty()){
            return null;
        }
        int index = randomIntFromRange(0, set.size());
        T choose = new ArrayList<>(set).get(index);
        set.remove(choose);
        return choose;
    }
}
