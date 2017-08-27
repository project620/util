package com.jim.util.string;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/**
 * Created by jim.huang on 2017/08/27.
 */
public class StringUtil {


    private static Map<String, Pattern> patternCache = new ConcurrentHashMap<>();


    private static Pattern retrievePattern(String key) {
        Pattern pattern = patternCache.get(key);
        if (pattern == null) {
            pattern = Pattern.compile(key);
            patternCache.put(key, pattern);
        }
        return pattern;
    }


    /**
     * replace all oldChar with newChar
     * @param srcStr
     * @param oldChar
     * @param newChar
     * @return
     */
    public static String replaceAll(String srcStr, String oldChar, String newChar) {
        Pattern pattern = retrievePattern(oldChar);
        return pattern.matcher(srcStr).replaceAll(newChar);
    }


    /**
     * replace first oldChar with newChar
     * @param srcStr
     * @param oldChar
     * @param newChar
     * @return
     */
    public static String replaceFirst(String srcStr, String oldChar, String newChar) {
        Pattern pattern = retrievePattern(oldChar);
        return pattern.matcher(srcStr).replaceFirst(newChar);
    }


}
