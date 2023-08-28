package com.wgcloud.util;

import cn.hutool.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wgcloud.config.CommonConfig;

/**
 * @version v2.3
 * @ClassName:TokenUtils.java
 * @author: http://www.wgstart.com
 * @date: November 16, 2019
 * @Description: MD5 encryption processing
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
@Component
public class TokenUtils {
    private static final Logger logger = LoggerFactory.getLogger(TokenUtils.class);

    @Autowired
    private CommonConfig commonConfig;

    /**
     * Verify whether the token of the Agent and the Server token are consistent
     *
     * @param agentJsonObject
     * @return
     */
    public boolean checkAgentToken(JSONObject agentJsonObject) {
        if (null == agentJsonObject) {
            return false;
        }
        String wgToken = MD5Utils.GetMD5Code(commonConfig.getWgToken());
        String agentWgToken = agentJsonObject.getStr("wgToken");
        if (StringUtils.isEmpty(agentWgToken)) {
            return false;
        }
        if (wgToken.equals(agentWgToken)) {
            return true;
        }
        return false;

    }

}
