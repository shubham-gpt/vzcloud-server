package com.wgcloud.controller;

import cn.hutool.json.JSONUtil;
import com.github.pagehelper.PageInfo;
import com.wgcloud.dto.MessageDto;
import com.wgcloud.entity.DbInfo;
import com.wgcloud.service.DbInfoService;
import com.wgcloud.service.DbTableService;
import com.wgcloud.service.LogInfoService;
import com.wgcloud.util.PageUtil;
import com.wgcloud.util.jdbc.ConnectionUtil;
import com.wgcloud.util.staticvar.StaticKeys;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @version v2.3
 * @ClassName:DbInfoController.java
 * @author: http://www.wgstart.com
 * @date: November 16, 2019
 * @Description: DbInfoController.java
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
@Controller
@RequestMapping("/dbInfo")
public class DbInfoController {


    private static final Logger logger = LoggerFactory.getLogger(DbInfoController.class);

    @Resource
    private DbInfoService dbInfoService;
    @Resource
    private DbTableService dbTableService;
    @Resource
    private LogInfoService logInfoService;
    @Resource
    private ConnectionUtil connectionUtil;


    /**
     * Test database connection
     *
     * @param model
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "validate")
    public String valdateDbInfo(DbInfo DbInfo, Model model, HttpServletRequest request) {
        MessageDto messageDto = new MessageDto();
        try {
            JdbcTemplate JdbcTemplate = connectionUtil.getJdbcTemplate(DbInfo);
            if (JdbcTemplate == null) {
                messageDto.setCode("1");
                messageDto.setMsg("Connect the database error, please check whether the parameters are correct.Please view the log in the system information");
            } else {
                messageDto.setCode("0");
                messageDto.setMsg("Connect to the database success");
            }
        } catch (Exception e) {
            logger.error("Test data source information error", e);
            logInfoService.save("Test data source information error", e.toString(), StaticKeys.LOG_ERROR);

        }
        return JSONUtil.toJsonStr(messageDto);
    }


    /**
     * Query list according to conditions
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "list")
    public String DbInfoList(DbInfo DbInfo, Model model, HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        try {
            PageInfo pageInfo = dbInfoService.selectByParams(params, DbInfo.getPage(), DbInfo.getPageSize());
            PageUtil.initPageNumber(pageInfo, model);
            model.addAttribute("pageUrl", "/dbInfo/list?1=1");
            model.addAttribute("page", pageInfo);
        } catch (Exception e) {
            logger.error("Query data source information error", e);
            logInfoService.save("Query data source information error", e.toString(), StaticKeys.LOG_ERROR);

        }
        return "mysql/dblist";
    }


    /**
     * 添加
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "edit")
    public String edit(Model model, HttpServletRequest request) {
        String errorMsg = "Add data source:";
        String id = request.getParameter("id");
        DbInfo dbInfo = new DbInfo();
        try {
            if (StringUtils.isEmpty(id)) {
                model.addAttribute("dbInfo", dbInfo);
                return "mysql/init";
            }
            dbInfo = dbInfoService.selectById(id);
            model.addAttribute("dbInfo", dbInfo);
        } catch (Exception e) {
            logger.error(errorMsg, e);
            logInfoService.save(dbInfo.getDbName(), errorMsg + e.toString(), StaticKeys.LOG_ERROR);
        }
        return "mysql/init";
    }


    /**
     * Save data source information
     *
     * @param DbInfo
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "save")
    public String saveDbInfo(DbInfo DbInfo, Model model, HttpServletRequest request) {
        try {
            if (StringUtils.isEmpty(DbInfo.getId())) {
                dbInfoService.save(DbInfo);
            } else {
                dbInfoService.updateById(DbInfo);
            }
        } catch (Exception e) {
            logger.error("Save data source error:", e);
            logInfoService.save("Save data source error", e.toString(), StaticKeys.LOG_ERROR);
        }
        return "redirect:/dbInfo/list?msg=save";
    }


    /**
     * Delete the data source
     *
     * @param id
     * @param model
     * @param request
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "del")
    public String delete(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String errorMsg = "Delete data source information errors:";
        DbInfo DbInfo = new DbInfo();
        try {
            if (!StringUtils.isEmpty(request.getParameter("id"))) {
                DbInfo = dbInfoService.selectById(request.getParameter("id"));
                logInfoService.save("Delete the data source:" + DbInfo.getAliasName(), "Delete the data source:" + DbInfo.getIp() + "：" + DbInfo.getPort() +
                        ", Database alias" + DbInfo.getAliasName(), StaticKeys.LOG_ERROR);
                dbInfoService.deleteById(request.getParameter("id").split(","));
                dbTableService.deleteByDbInfoId(DbInfo.getId());
            }
        } catch (Exception e) {
            logger.error(errorMsg, e);
            logInfoService.save(errorMsg, e.toString(), StaticKeys.LOG_ERROR);
        }
        return "redirect:/dbInfo/list?msg=del";
    }


}
