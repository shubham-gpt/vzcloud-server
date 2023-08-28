package com.wgcloud.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @version v2.3
 * @ClassName:MD5Utils.java
 * @author: http://www.wgstart.com
 * @date: November 16, 2019
 * @Description: MD5 encryption processing
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
@SuppressWarnings("unused")
public class MD5Utils {
    private static final Logger logger = LoggerFactory.getLogger(MD5Utils.class);

    // Global array
    private final static String[] strDigits = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * 16th -to -produce characters
     */
    private final static char hexdigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9', 'a', 'b', 'c', 'd', 'e', 'f'};


    /**
     * Full text on the file MD5 Summary
     *
     * @param file Files to be encrypted
     * @return MD5 Summary Code
     */
    public static String getMD5ForFile(String filePath) {
        FileInputStream fis = null;
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            File file = new File(filePath);
            if (!file.exists()) {
                return "";
            }
            fis = new FileInputStream(file);
            byte[] buffer = new byte[4096];
            int length = -1;
            while ((length = fis.read(buffer)) != -1) {
                md.update(buffer, 0, length);
            }
            byte[] b = md.digest();
            return byteToHexString(b);
        } catch (Exception ex) {
            logger.error("Get MD5 information abnormal!" + ex.toString());
            return null;
        } finally {
            try {
                if (null != fis) {
                    fis.close();
                }
            } catch (IOException e) {
                logger.error("Get MD5 information abnormal!" + e.toString());
            }
        }
    }

    /**
     * Byte[]The array converted into a sixteen -proof string representation form
     *
     * @param tmp Byte to be converted[]
     * @return Sixteen -proof string representation forms
     */
    private static String byteToHexString(byte[] tmp) {
        String s;
        char str[] = new char[16 * 2];
        int k = 0;
        for (int i = 0; i < 16; i++) {
            byte byte0 = tmp[i];
            str[k++] = hexdigits[byte0 >>> 4 & 0xf];
            str[k++] = hexdigits[byte0 & 0xf];
        }
        s = new String(str);
        return s;
    }

    // The return form is numbers and string
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        // System.out.println("iRet="+iRet);
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }

    // The return form is only the number
    private static String byteToNum(byte bByte) {
        int iRet = bByte;
        System.out.println("iRet1=" + iRet);
        if (iRet < 0) {
            iRet += 256;
        }
        return String.valueOf(iRet);
    }

    // The converted bytes are hexadecimal string
    private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }

    public static String GetMD5Code(String strObj) {
        if (StringUtils.isEmpty(strObj)) {
            return "";
        }
        String resultString = null;
        try {
            resultString = new String(strObj);
            MessageDigest md = MessageDigest.getInstance("MD5");
            // md.digest() The return value of this function is the Byte array that stores the results of the Highee value
            resultString = byteToString(md.digest(strObj.getBytes()));
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return resultString;
    }


}
