package com.vzcloud.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vzcloud.entity.SystemInfo;
import com.vzcloud.service.LogInfoService;
import com.vzcloud.service.SystemInfoService;
import com.vzcloud.util.staticvar.StaticKeys;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @version v2.3
 * @ClassName:HostInfoController.java
 * @author: http://www.wgstart.com
 * @date: November 16, 2019
 * @Description: Host remarks information
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
@Controller
@RequestMapping("/host")
public class HostInfoController {


    private static final Logger logger = LoggerFactory.getLogger(HostInfoController.class);


    @Resource
    private SystemInfoService systemInfoService;
    @Resource
    private LogInfoService logInfoService;


    /**
     * Save the host remark information
     *
     * @param SystemInfo
     * @param model
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "save")
    public String saveHostInfo(SystemInfo SystemInfo, Model model, HttpServletRequest request) {
        try {
            if (StringUtils.isEmpty(SystemInfo.getId())) {
                systemInfoService.save(SystemInfo);
            } else {
                SystemInfo ho = systemInfoService.selectById(SystemInfo.getId());
                ho.setRemark(SystemInfo.getRemark());
                systemInfoService.updateById(ho);
            }

        } catch (Exception e) {
            logger.error("Save the host remark information error:", e);
            logInfoService.save(SystemInfo.getHostname(), "Save the host remark information error:" + e.toString(), StaticKeys.LOG_ERROR);
        }
        return "redirect:/dash/systemInfoList";
    }


}
