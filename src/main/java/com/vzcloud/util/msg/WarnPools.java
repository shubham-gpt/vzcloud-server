package com.vzcloud.util.msg;

import java.util.HashMap;
import java.util.Map;

/**
 * @version v2.3
 * @ClassName:WarnPools.java
 * @author: http://www.vzstart.com
 * @date: November 16, 2019
 * @Description: WarnPools.java
 * @Copyright: 2017-2022 vzcloud. All rights reserved.
 */
public class WarnPools {


    /**
     * Store the memory warning information MAP sent every day<User ID+Server IP, 1>
     */
    public static Map<String, String> MEM_WARN_MAP = new HashMap<String, String>();

    public static void clearOldData() {
        MEM_WARN_MAP.clear();
    }

}
