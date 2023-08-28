package com.wgcloud.util;


import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @version v2.3
 * @ClassName:FormatUtil.java
 * @author: http://www.wgstart.com
 * @date: November 16, 2019
 * @Description: FormatUtil.java
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
public class FormatUtil {

    /**
     * Formatal file size
     * Forms such as: 2.22 K, 2.Bytes, retain 4 bits in lengthytes，长度保留4位
     *
     * @param size File size
     * @return Formatized string
     */
    public static String formatSize(long size) {
        String sizeStr = "";
        //Less than 1024 bytes
        if (size < 1024) {
            sizeStr = size + " Bytes";
        }
        //Greater than 1m
        else if (size > 1024000) {
            sizeStr = Float.toString((float) size / 1024000);
            if (sizeStr.length() > 4) {
                sizeStr = sizeStr.substring(0, 4);
            }
            if (sizeStr.endsWith(".")) {
                sizeStr = sizeStr.substring(0, sizeStr.length() - 1);
            }
            sizeStr += " M";
        } else {
            sizeStr = Float.toString((float) size / 1024);
            if (sizeStr.length() > 4) {
                sizeStr = sizeStr.substring(0, 4);
            }
            if (sizeStr.endsWith(".")) {
                sizeStr = sizeStr.substring(0, sizeStr.length() - 1);
            }
            sizeStr += " K";
        }
        return sizeStr;
    }

    public static String formatPath(String pathStr, boolean isEndWithSeparator) {
        pathStr = formatPath(pathStr);
        pathStr = pathStr + File.separator;
        return pathStr;
    }

    public static String formatPath(String pathStr) {
        pathStr = pathStr.replace('/', File.separatorChar);
        pathStr = pathStr.replace('\\', File.separatorChar);
        if (pathStr.endsWith(File.separator)) {
            pathStr = pathStr.substring(0, pathStr.length() - 1);
            pathStr = formatPath(pathStr);
        }
        return pathStr;
    }


    /**
     * Treat the string to prevent errors in the empty string
     *
     * @param String
     * @return String Formatized string
     */
    public static String formatNullString(String str) {
        if (str == null || str.trim().equals("")) {
            str = "";
        }
        return str;
    }


    /**
     * If the string is empty, set it to the default value
     *
     * @param String String
     * @param String Default string
     * @return String Formatized string
     */
    public static String formatNullString(String str, String defaultStr) {
        if (str == null || str.trim().equals("")) {
            str = defaultStr;
        }
        return str;
    }


    /**
     * Determine whether it is a number
     *
     * @param String String
     * @return boolean
     */
    public static boolean isNumeric(String str) {
        if (str != null && !"".equals(str) && !str.startsWith("0")) {
            for (int i = str.length(); --i >= 0; ) {
                int chr = str.charAt(i);
                if (chr < 48 || chr > 57)
                    return false;
            }
            return true;
        } else
            return false;
    }


    /**
     * Determine whether it is Chinese
     *
     * @param char c
     * @return boolean
     */
    public static boolean isLetter(char c) {
        int k = 0x80;
        return c / k == 0 ? true : false;
    }


    /**
     * Replace multiple spaces in the string to one
     *
     * @param str
     * @return
     */
    public static String replaceKg(String str) {
        Pattern p = Pattern.compile("\\s+");
        Matcher m = p.matcher(str);
        return m.replaceAll(" ");
    }

    /**
     * Depending on the domain name according to the URL
     *
     * @param String url
     * @return String
     */
    public static String getDomainForUrl(String url) {
        return url.replaceAll("http://([^/|:]+)[/|:].*", "$1");
    }

    /**
     * m turn to G to G
     *
     * @param str
     * @return
     */
    public static double mToG(String str) {
        double result = 0;
        double mod = 1024;
        if (str.contains("M")) {
            double f = Double.valueOf(str.replace("M", ""));
            result = f / mod;
        } else if (str.contains("K")) {
            double f = Double.valueOf(str.replace("K", ""));
            result = (f / mod) / mod;
        } else if (str.contains("T")) {
            double f = Double.valueOf(str.replace("T", ""));
            result = f * 1024;
        } else if (str.contains("G")) {
            result = Double.valueOf(str.replace("G", ""));
        }
        return formatDouble(result, 2);
    }


    // Sort by value in the map -- Order
    public static List<String> sortMapValueDouble(Map<String, Integer> maps) {
        List<Map.Entry<String, Integer>> info = new ArrayList<Map.Entry<String, Integer>>(maps.entrySet());
        List<String> desc = new ArrayList<String>();
        Collections.sort(info, new DoubleComparator());
        for (Map.Entry<String, Integer> map : info) {
            desc.add(map.getKey());
        }
        return desc;
    }

    // Custom comparator: Sort by similarity
    static class DoubleComparator implements Comparator {
        public int compare(Object object1, Object object2) {// Method in the interface  
            Map.Entry<String, Integer> p1 = (Map.Entry<String, Integer>) object1; // Mandatory conversion
            Map.Entry<String, Integer> p2 = (Map.Entry<String, Integer>) object2;
            return p2.getValue().compareTo(p1.getValue());
        }
    }


    /**
     * Format the double data, cut the number after the decimal point
     *
     * @param str
     * @param num
     * @return
     */
    public static double formatDouble(double str, int num) {
        java.math.BigDecimal b = new java.math.BigDecimal(str);
        double myNum3 = b.setScale(num, java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
        return myNum3;
    }

    public static void main(String[] args) {
        System.out.println(formatDouble(96.36, 1));
    }

}