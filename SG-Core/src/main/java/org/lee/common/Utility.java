package org.lee.common;

import org.apache.commons.codec.digest.MurmurHash3;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.lee.SQLGeneratorContext;
import org.lee.entry.relation.Relation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

public class Utility {
    public static final Logger LOGGER;
    public static final SecureRandom secureRandom;
    public static final ThreadLocal<SecureRandom> threadLocalSecureRandom;
    public static final RandomDataGenerator randomDataGenerator;
    public static final short MAX_STRINGS;
    public static final int ACTUALLY_MAX_STRINGS;
    private static final char[] charset;
    private static final String strings;
    private static final long timestamps;
    private static final long timestampsRange;  // 100 days

    public static String getLocalFrameInfo(){
        return getLocalFrameInfo(4);
    }

    public static long parameterToKey(Object ... objects){
        StringBuilder stringBuilder = new StringBuilder();
        for (Object object : objects) {
            stringBuilder.append(object);
        }
        return MurmurHash3.hash32x86(stringBuilder.toString().getBytes());
    }

    public static String getLocalFrameInfo(final int truncateSize){
        StackTraceElement[] localStackElement = Thread.getAllStackTraces().get(Thread.currentThread());
        Assertion.requiredTrue(localStackElement.length >= truncateSize);
        StackTraceElement[] removedNonSenseStackInfo = Arrays.copyOfRange(localStackElement, truncateSize, localStackElement.length);
        return StringUtils.joinWith("\n", (Object[]) removedNonSenseStackInfo);
    }

    public static void recordLocalFrameInfo4DebugInLog(Logger logger){
        if(logger == null){
            logger = LOGGER;
        }
        logger.debug(getLocalFrameInfo(4));
    }

    public static <T> List<T> copyList(final List<T> source){
        if(source == null || source.isEmpty()){
            // cannot be emptyList here
            return new ArrayList<>();
        }
        final List<T> dest = new ArrayList<>(source.size());
        dest.addAll(source);
        return dest;
    }

    public static <T> List<T> copyListShuffle(final List<T> source){
        List<T> dest = copyList(source);
        Collections.shuffle(dest);
        return dest;
    }

    public static <T> List<T> copyFrozenList(final List<T> source){
        return Collections.unmodifiableList(source);
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

    public static String stringToLikePattern(String candidate){
        if(candidate.isEmpty()) return candidate;
        if(candidate.length() == 1) return probability(50) ? "%" + candidate : candidate + "%";
        final int duplicatePatternProb = candidate.length() == 2 ? 0 : Math.min(10, candidate.length()/10 + 1);
        final int maxPattern = candidate.length() / 2;
        int times = 0;
        do{
            times++;
            int replacedBeginIndex = randomIntFromRange(0, candidate.length());
            int replacedEndIndex = randomIntFromRange(replacedBeginIndex, candidate.length());
            assert replacedEndIndex >= replacedBeginIndex;
            if(replacedBeginIndex == 0){
                return "%" + candidate.substring(1);
            }else if (replacedEndIndex == candidate.length() - 1){
                return candidate.substring(0, candidate.length() - 1) + "%";
            }
            candidate = candidate.substring(0, replacedBeginIndex) + "%" + candidate.substring(replacedEndIndex+1);
        }while (times < maxPattern && probability(duplicatePatternProb));
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

    public static String inputStreamToString(InputStream input){
        StringBuilder builder = new StringBuilder();
        String line;
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(input))){
            while ((line = reader.readLine()) != null){
                builder.append(line).append("\n");
            }
        }catch (IOException e){
            LOGGER.error("An IOException occurred during loading input stream.", e);
            throw new RuntimeException(e);
        }
        return builder.toString();
    }

    static {
        LOGGER = LoggerFactory.getLogger(Utility.class);
        timestamps = 1720000000000L;
        timestampsRange = 100 * 24 * 3600000L;
        secureRandom = new SecureRandom();
        // todo: add thread local initializer for secureRandom
        threadLocalSecureRandom = new ThreadLocal<>();
        threadLocalSecureRandom.set(new SecureRandom());
        randomDataGenerator = new RandomDataGenerator();
        MAX_STRINGS = 100;
        charset = new char[]{
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'g', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'G', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        };
        String stringFromFile = inputStreamToString(Utility.class.getClassLoader().getResourceAsStream("strings.txt"));
        ACTUALLY_MAX_STRINGS = Math.max(stringFromFile.length(), MAX_STRINGS);
        strings = stringFromFile.substring(0, ACTUALLY_MAX_STRINGS);
    }
}
