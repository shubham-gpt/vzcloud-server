package com.wgcloud.util;

import java.util.Random;
import java.util.UUID;


/**
 * @version v2.3
 * @ClassName:UUIDUtil.java
 * @author: http://www.wgstart.com
 * @date: November 16, 2019
 * @Description: UUIDUtil.java
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
public class UUIDUtil {

    public static String getUUID() {
        return String.valueOf(UUID.randomUUID()).replace("-", "");
    }

    /**
     * Random 6 -digit number
     *
     * @return
     */
    public static String getRandomSix() {
        return "" + new Random().nextInt(999999);
    }

}
