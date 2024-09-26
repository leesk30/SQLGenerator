package org.lee.util;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.lee.common.MetaEntry;
import org.lee.entry.relation.Relation;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.*;

public class FuzzUtil {
    public static final SecureRandom secureRandom;
    public static final RandomDataGenerator randomDataGenerator;
    public static final short MAX_STRINGS;
    private static final char[] charset;
    private static final String strings;
    private static final long timestamps = 1720000000000L;
    private static final long timestampsRange = 100 * 24 * 3600000L;  // 100 days
    public static final int ACTUALLY_MAX_STRINGS;

    static {
        secureRandom = new SecureRandom();
        randomDataGenerator = new RandomDataGenerator();
        MAX_STRINGS = 100;
        charset = new char[]{
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'g', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'G', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        };
        String stringFromFile = FileUtil.inputStreamToString(FuzzUtil.class.getClassLoader().getResourceAsStream("strings.txt"));
        ACTUALLY_MAX_STRINGS = Math.max(stringFromFile.length(), MAX_STRINGS);
        strings = stringFromFile.substring(0, ACTUALLY_MAX_STRINGS);
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

    public static <T> T randomlyPop(List<T> list){
        if(list == null || list.isEmpty()){
            return null;
        }
        Collections.shuffle(list);
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

    public static Relation getRandomRelationFromMetaEntry(){
        Object[] keys = MetaEntry.relationMap.keySet().toArray();
        String key = (String) randomlyChooseFrom(keys);
        return MetaEntry.relationMap.get(key);
    }

    public static Relation getRandomRelationFromMetaEntry(String limitationNamespace){
        if(limitationNamespace == null){
            return getRandomRelationFromMetaEntry();
        }
        return randomlyChooseFrom(MetaEntry.relationByNamespaceMap.get(limitationNamespace));
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
        final int startAt = FuzzUtil.randomIntFromRange(0, ACTUALLY_MAX_STRINGS - candidateLength);
        builder.append(strings, startAt, startAt + candidateLength);
        return builder.toString();
    }

    public static Timestamp randomTimestamp(int partial){
        long absInterval = FuzzUtil.randomLongFromRange(0, timestampsRange);
        long interval = FuzzUtil.probability(50) ? absInterval: -absInterval;
        return new Timestamp(timestamps + partial + interval);
    }

    public static Date randomDate(int partial){
        long absInterval = FuzzUtil.randomLongFromRange(0, timestampsRange);
        long interval = FuzzUtil.probability(50) ? absInterval: -absInterval;
        return new Date(timestamps + partial + interval);
    }

    public static String stringToLikePattern(String candidate){
        if(candidate.isEmpty()) return candidate;
        if(candidate.length() == 1) return FuzzUtil.probability(50) ? "%" + candidate : candidate + "%";
        final int duplicatePatternProb = candidate.length() == 2 ? 0 : Math.min(10, candidate.length()/10 + 1);
        final int maxPattern = candidate.length() / 2;
        int times = 0;
        do{
            times++;
            int replacedBeginIndex = FuzzUtil.randomIntFromRange(0, candidate.length());
            int replacedEndIndex = FuzzUtil.randomIntFromRange(replacedBeginIndex, candidate.length());
            assert replacedEndIndex >= replacedBeginIndex;
            if(replacedBeginIndex == 0){
                return "%" + candidate.substring(1);
            }else if (replacedEndIndex == candidate.length() - 1){
                return candidate.substring(0, candidate.length() - 1) + "%";
            }
            candidate = candidate.substring(0, replacedBeginIndex) + "%" + candidate.substring(replacedEndIndex+1);
        }while (times < maxPattern && FuzzUtil.probability(duplicatePatternProb));
        System.out.println("TotalTimes: " + times);
        return candidate;
    }

    public static String getRandomName(String prefix){
        return prefix + UUID.randomUUID().toString().replace("-", "").substring(0, 6);
    }

    private static boolean checkBeginAndEndIsInvalid(int beginInclusive, int endExclusive){
        if(beginInclusive == endExclusive) return true;
        if(beginInclusive > endExclusive) throw new IndexOutOfBoundsException("The end index must be greater than begin index.");
        return false;
    }

    private static boolean checkBeginAndEndIsInvalid(long beginInclusive, long endExclusive){
        if(beginInclusive == endExclusive) return true;
        if(beginInclusive > endExclusive) throw new IndexOutOfBoundsException("The end index must be greater than begin index.");
        return false;
    }

    public static int getBinomialDistributionRandomFromRange(int prob, int beginInclusive, int endExclusive){
        // TODO: There might be some bugs in calculating of edge.
        if(checkBeginAndEndIsInvalid(beginInclusive, endExclusive)){
            return beginInclusive;
        }
        int edge = endExclusive - beginInclusive;
        return beginInclusive + randomDataGenerator.nextBinomial(edge, (double) prob / 100D);
    }

}
