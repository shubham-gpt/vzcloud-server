package com.wgcloud.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @version v2.3
 * @ClassName:Encode.java
 * @author: http://www.wgstart.com
 * @date: November 16, 2019
 * @Description: Encode.java
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
public class Encode {

    private static final Logger logger = LoggerFactory.getLogger(Encode.class);

    /**
     * UTF-8 encoding, and then coded by the system default
     *
     * @param str Code front string
     * @return Code post -string
     */
    public static String utf8ToSystem(String str) {
        return encode(str, "UTF-8", System.getProperty("file.encoding"));
    }

    /**
     * Code by default by the system, and then UTF-8 decoding
     *
     * @param str Code front string
     * @return Code post -string
     */
    public static String systemToUtf8(String str) {
        return encode(str, System.getProperty("file.encoding"), "UTF-8");
    }

    /**
     * Code with GBK, and then use the default coding of the system
     *
     * @param str Code front string
     * @return Code post -string
     */
    public static String gbkToSystem(String str) {
        return encode(str, "GBK", System.getProperty("file.encoding"));
    }

    /**
     * Code with the default coding of the system, and then decode it with GBK
     *
     * @param str Code front string
     * @return Code post -string
     */
    public static String systemToGbk(String str) {
        return encode(str, System.getProperty("file.encoding"), "GBK");
    }

    /**
     * Code with ISO_8859_1, and then encoded and decoded by default by the system
     *
     * @param str Code front string
     * @return Code post -string
     */
    public static String iso_8859_1ToSystem(String str) {
        return encode(str, "ISO_8859_1", System.getProperty("file.encoding"));
    }

    /**
     * Code with the default coding of the system, and then decoding with ISO_8859_1
     *
     * @param str Code front string
     * @return Code post -string
     */
    public static String systemToIso_8859_1(String str) {
        return encode(str, System.getProperty("file.encoding"), "ISO_8859_1");
    }

    /**
     * Code with ISO_8859_1, and then decod
     *
     * @param str Code front string
     * @return Code post -string
     */
    public static String iso_8859_1ToGbk(String str) {
        return encode(str, "ISO_8859_1", "GBK");
    }

    /**
     * Code with GBK, and then decoded with ISO_8859_1
     *
     * @param str Code front string
     * @return Code post -string
     */
    public static String gbkToIso_8859_1(String str) {
        return encode(str, "GBK", "ISO_8859_1");
    }

    /**
     * UTF-8 encoding, then decoding with GBK
     *
     * @param str Code front string
     * @return Code post -string
     */
    public static String utf8ToGbk(String str) {
        return encode(str, "UTF-8", "GBK");
    }

    /**
* Code with GBK, and then decode UTF-8     *
     * @param str Code front string
     * @return Code post -string
     */
    public static String gbkToUtf8(String str) {
        return encode(str, "GBK", "UTF-8");
    }

    /**
     * UTF-8 encoding, then decoding with ISO_8859_1
     *
     * @param str Code front string
     * @return Code post -string
     */
    public static String utf8ToIso_8859_1(String str) {
        return encode(str, "UTF-8", "ISO_8859_1");
    }

    /**
     * Code in ISO_8859_1, and then UTF-8 decoding
     *
     * @param str Code front string
     * @return Code post -string
     */
    public static String iso_8859_1ToUtf8(String str) {
        return encode(str, "ISO_8859_1", "UTF-8");
    }

    /**
     * Url encoding
     * Code with the default code of the system
     *
     * @param str Code front string
     * @return Code post -string
     */
    public static String urlEncode(String str) {
        str = urlEncode(str, System.getProperty("file.encoding"));
        return str;
    }

    /**
     * Url encoding
     * Code by specified code
     *
     * @param str      Code front string
     * @param encoding Specified encoding
     * @return Code post -string
     */
    public static String urlEncode(String str, String encoding) {
        try {
            str = URLEncoder.encode(str, encoding);
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace(System.out);
            return null;
        }
        return str;
    }

    /**
     * Url decoding
     * Use the default code decoding of the system
     *
     * @param str Code front string
     * @return Code post -string
     */
    public static String urlDecode(String str) {
        str = urlDecode(str, System.getProperty("file.encoding"));
        return str;
    }

    /**
     * Url decoding
     * Decoding with specified code
     *
     * @param str      Code front string
     * @param encoding Specified encoding
     * @return Code post -string
     */
    public static String urlDecode(String str, String encoding) {
        try {
            str = URLDecoder.decode(str, encoding);
        } catch (UnsupportedEncodingException ex) {
            logger.error("Dreatment of garbled abnormalities" + ex.toString());
            return "";
        }
        return str;
    }

    /**
     * For Chinese URL encoding, it is used to access the resource on the browser side to visit the Linux server
     * Code in the specified code, only for Chinese
     *
     * @param str      Code front string
     * @param encoding Specified encoding
     * @return Code post -string
     */
    public static String urlEncodeForLinux(String str, String encoding) {
        str = Encode.gbkToSystem(str);
        str = Encode.urlEncode(str, encoding);
        str = str.replaceAll("\\+", "%20");

        return str;
    }

    /**
     *Code conversion, the string is the specified encoding
     *
     * @param str       Code front string
     * @param encodeStr Original string encoding
     * @param decodeStr Specified encoding
     * @return Code post -string
     */
    public static String encode(String str, String encodeStr, String decodeStr) {
        try {
            str = new String(str.getBytes(encodeStr), decodeStr);
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace(System.out);
            return null;
        }

        return str;
    }

    /**
     * Url decoding
     * Decoding by specified code
     *
     * @param str      Code front string
     * @param encoding Specified encoding
     * @return Code post -string
     */
    public static String luanmaStr(String str) {
        try {
            str = new String(str.getBytes("ISO-8859-1"), "utf-8");
        } catch (UnsupportedEncodingException ex) {
            logger.error("Dreatment of garbled abnormalities" + ex.toString());
            return "";
        }
        return str;
    }

    public static void main(String[] args) {
        //测试urlDecode()方法
        //String s = "%E6%9C%AC%E5%9C%B0+%E6%9C%AC%E5%9C%B0";
//        String s = "本地+ 本地";
//        s = urlEncode(s, "UTF-8");

//        s = "%2Froot%2Fimage%2F+plmm";
        //s = urlDecode(s, "ISO_8859_1");
//        s = urlDecode(s, "UTF-8");
        String mytext = null;
        String mytext2 = null;
        try {
            mytext = URLEncoder.encode("China", "utf-8");
            mytext2 = urlDecode(mytext, "utf-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println(mytext);
        System.out.println(mytext2);
    }

}
