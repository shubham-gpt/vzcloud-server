package com.wgcloud.controller;

import com.github.pagehelper.PageInfo;
import com.wgcloud.entity.HeathMonitor;
import com.wgcloud.service.HeathMonitorService;
import com.wgcloud.service.LogInfoService;
import com.wgcloud.util.PageUtil;
import com.wgcloud.util.staticvar.StaticKeys;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @version v2.3
 * @ClassName:HeathMonitorController.java
 * @author: http://www.wgstart.com
 * @date: November 16, 2019
 * @Description: HeathMonitorController.java
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
@Controller
@RequestMapping("/heathMonitor")
public class HeathMonitorController {


    private static final Logger logger = LoggerFactory.getLogger(HeathMonitorController.class);

    @Resource
    private HeathMonitorService heathMonitorService;
    @Resource
    private LogInfoService logInfoService;


    /**
     * Check the list of heartbeat monitoring list according to conditions
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "list")
    public String heathMonitorList(HeathMonitor HeathMonitor, Model model) {
        Map<String, Object> params = new HashMap<String, Object>();
        try {
            PageInfo pageInfo = heathMonitorService.selectByParams(params, HeathMonitor.getPage(), HeathMonitor.getPageSize());
            PageUtil.initPageNumber(pageInfo, model);
            model.addAttribute("pageUrl", "/heathMonitor/list?1=1");
            model.addAttribute("page", pageInfo);
        } catch (Exception e) {
            logger.error("Query service heartbeat monitoring error", e);
            logInfoService.save("Query heartbeat monitor error", e.toString(), StaticKeys.LOG_ERROR);

        }
        return "heath/list";
    }


    /**
     * Save heartbeat monitoring information
     *
     * @param HeathMonitor
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "save")
    public String saveHeathMonitor(HeathMonitor HeathMonitor, Model model, HttpServletRequest request) {
        try {
            if (StringUtils.isEmpty(HeathMonitor.getId())) {
                heathMonitorService.save(HeathMonitor);
            } else {
                heathMonitorService.updateById(HeathMonitor);
            }

        } catch (Exception e) {
            logger.error("Preservation service Heartbeat monitor monitoring error:", e);
            logInfoService.save(HeathMonitor.getAppName(), "Save heartbeat monitoring error:" + e.toString(), StaticKeys.LOG_ERROR);
        }
        return "redirect:/heathMonitor/list";
    }


    /**
     * Check the heartbeat monitor
     *
     * @param HeathMonitor
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "edit")
    public String edit(Model model, HttpServletRequest request) {
        String errorMsg = "Edit Service Heartbeat Monitoring:";
        String id = request.getParameter("id");
        HeathMonitor heathMonitor = new HeathMonitor();
        if (StringUtils.isEmpty(id)) {
            model.addAttribute("heathMonitor", heathMonitor);
            return "heath/add";
        }

        try {
            heathMonitor = heathMonitorService.selectById(id);
            model.addAttribute("heathMonitor", heathMonitor);
        } catch (Exception e) {
            logger.error(errorMsg, e);
            logInfoService.save(heathMonitor.getAppName(), errorMsg + e.toString(), StaticKeys.LOG_ERROR);
        }
        return "heath/add";
    }

    /**
     * Check the heartbeat monitor
     *
     * @param HeathMonitor
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "view")
    public String view(Model model, HttpServletRequest request) {
        String errorMsg = "View service heartbeat monitor:";
        String id = request.getParameter("id");
        String date = request.getParameter("date");
        HeathMonitor heathMonitor = new HeathMonitor();
        try {
            heathMonitor = heathMonitorService.selectById(id);
            model.addAttribute("heathMonitor", heathMonitor);
        } catch (Exception e) {
            logger.error(errorMsg, e);
            logInfoService.save(heathMonitor.getAppName(), errorMsg + e.toString(), StaticKeys.LOG_ERROR);
        }
        return "heath/view";
    }


    /**
     * Delete heartbeat monitoring
     *
     * @param id
     * @param model
     * @param request
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "del")
    public String delete(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String errorMsg = "Delete service Heartbeat monitor monitoring errors:";
        HeathMonitor HeathMonitor = new HeathMonitor();
        try {
            if (!StringUtils.isEmpty(request.getParameter("id"))) {
                HeathMonitor = heathMonitorService.selectById(request.getParameter("id"));
                logInfoService.save("Delete service heartbeat monitor:" + HeathMonitor.getAppName(), "Delete service heartbeat monitor:" + HeathMonitor.getAppName() + "：" + HeathMonitor.getHeathUrl(), StaticKeys.LOG_ERROR);
                heathMonitorService.deleteById(request.getParameter("id").split(","));
            }
        } catch (Exception e) {
            logger.error(errorMsg, e);
            logInfoService.save(HeathMonitor.getAppName(), errorMsg + e.toString(), StaticKeys.LOG_ERROR);
        }

        return "redirect:/heathMonitor/list";
    }


}
