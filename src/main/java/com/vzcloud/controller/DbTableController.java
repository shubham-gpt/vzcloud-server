package com.vzcloud.controller;

import cn.hutool.json.JSONUtil;
import com.github.pagehelper.PageInfo;
import com.vzcloud.entity.DbInfo;
import com.vzcloud.entity.DbTable;
import com.vzcloud.entity.DbTableCount;
import com.vzcloud.service.DbInfoService;
import com.vzcloud.service.DbTableCountService;
import com.vzcloud.service.DbTableService;
import com.vzcloud.service.LogInfoService;
import com.vzcloud.util.PageUtil;
import com.vzcloud.util.jdbc.RDSConnection;
import com.vzcloud.util.staticvar.StaticKeys;

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
import java.util.List;
import java.util.Map;

/**
 * @version v2.3
 * @ClassName:DbTableController.java
 * @author: http://www.wgstart.com
 * @date: November 16, 2019
 * @Description: DbTableController.java
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
@Controller
@RequestMapping("/dbTable")
public class DbTableController {


    private static final Logger logger = LoggerFactory.getLogger(DbTableController.class);

    @Resource
    private DbInfoService dbInfoService;
    @Resource
    private DbTableService dbTableService;
    @Resource
    private DbTableCountService dbTableCountService;
    @Resource
    private LogInfoService logInfoService;


    /**
     * Query list according to conditions
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "list")
    public String DbTableList(DbTable DbTable, Model model) {
        Map<String, Object> params = new HashMap<String, Object>();
        try {
            PageInfo<DbTable> pageInfo = dbTableService.selectByParams(params, DbTable.getPage(), DbTable.getPageSize());
            PageUtil.initPageNumber(pageInfo, model);
            List<DbInfo> dbInfoList = dbInfoService.selectAllByParams(params);
            for (DbTable dbTable : pageInfo.getList()) {
                for (DbInfo dbInfo : dbInfoList) {
                    if (dbInfo.getId().equals(dbTable.getDbInfoId())) {
                        dbTable.setTableName(dbInfo.getAliasName());
                    }
                }
            }
            model.addAttribute("pageUrl", "/dbTable/list?1=1");
            model.addAttribute("page", pageInfo);
        } catch (Exception e) {
            logger.error("Query data table information error", e);
            logInfoService.save("Query data table information error", e.toString(), StaticKeys.LOG_ERROR);

        }
        return "mysql/list";
    }


    /**
     * Save data source table information
     *
     * @param DbTable
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "save")
    public String saveDbTable(DbTable DbTable, Model model, HttpServletRequest request) {
        try {
            String whereVal = DbTable.getWhereVal().toLowerCase();
            if (!StringUtils.isEmpty(whereVal)) {
                String[] sqlinkeys = RDSConnection.SQL_INKEYS.split(",");
                for (String sqlinkey : sqlinkeys) {
                    if (whereVal.indexOf(sqlinkey) > -1) {
                        model.addAttribute("dbTable", DbTable);
                        List<DbInfo> dbInfoList = dbInfoService.selectAllByParams(new HashMap<>());
                        model.addAttribute("dbInfoList", dbInfoList);
                        model.addAttribute("msg", "The where statement contains SQL sensitive characters" + sqlinkey + ",Check, please");
                        return "mysql/add";
                    }
                }
            }
            if (StringUtils.isEmpty(DbTable.getId())) {
                dbTableService.save(DbTable);
            } else {
                dbTableService.updateById(DbTable);
            }
        } catch (Exception e) {
            logger.error("Save the data table error:", e);
            logInfoService.save("Save the data table error", e.toString(), StaticKeys.LOG_ERROR);
        }
        return "redirect:/dbTable/list";
    }


    /**
     * View data source table information
     *
     * @param DbTable
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "edit")
    public String editDbTable(DbTable DbTable, Model model, HttpServletRequest request) {
        try {
            String id = request.getParameter("id");
            DbTable dbTableInfo = new DbTable();
            if (!StringUtils.isEmpty(id)) {
                dbTableInfo = dbTableService.selectById(id);
            }
            List<DbInfo> dbInfoList = dbInfoService.selectAllByParams(new HashMap<>());
            model.addAttribute("dbInfoList", dbInfoList);
            model.addAttribute("dbTable", dbTableInfo);
        } catch (Exception e) {
            logger.error("View data table error:", e);
            logInfoService.save("View data table error", e.toString(), StaticKeys.LOG_ERROR);
        }
        return "mysql/add";
    }

    /**
     * View data source table chart statistics information
     *
     * @param DbTable
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "viewChart")
    public String viewChartDbTable(DbTable DbTable, Model model, HttpServletRequest request) {
        try {
            String id = request.getParameter("id");
            if (!StringUtils.isEmpty(id)) {
                DbTable dbTableInfo = dbTableService.selectById(id);
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("dbTableId", id);
                List<DbTableCount> dbTableCounts = dbTableCountService.selectAllByParams(params);
                model.addAttribute("dbTableCounts", JSONUtil.parseArray(dbTableCounts));
                model.addAttribute("dbTable", dbTableInfo);
                String sql = RDSConnection.query_table_count.replace("{tableName}", dbTableInfo.getTableName()) + dbTableInfo.getWhereVal();
                model.addAttribute("sqlCount", sql);
            }
        } catch (Exception e) {
            logger.error("View data chart statistical error:", e);
            logInfoService.save("View data chart statistical error", e.toString(), StaticKeys.LOG_ERROR);
        }
        return "mysql/view";
    }


    /**
     * Delete the data source table
     *
     * @param id
     * @param model
     * @param request
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "del")
    public String delete(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String errorMsg = "Delete the data source table information error:";
        try {
            if (!StringUtils.isEmpty(request.getParameter("id"))) {
                DbTable dbTable = dbTableService.selectById(request.getParameter("id"));
                logInfoService.save("Delete the data table:" + dbTable.getTableName(), "Delete the data table:" + dbTable.getTableName(), StaticKeys.LOG_ERROR);
                dbTableService.deleteById(request.getParameter("id").split(","));
            }
        } catch (Exception e) {
            logger.error(errorMsg, e);
            logInfoService.save(errorMsg, e.toString(), StaticKeys.LOG_ERROR);
        }

        return "redirect:/dbTable/list";
    }


}
