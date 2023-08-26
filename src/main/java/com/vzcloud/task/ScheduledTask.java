package com.vzcloud.task;


import cn.hutool.core.collection.CollectionUtil;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.vzcloud.config.CommonConfig;
import com.vzcloud.entity.*;
import com.vzcloud.mapper.*;
import com.vzcloud.service.*;
import com.vzcloud.util.DateUtil;
import com.vzcloud.util.RestUtil;
import com.vzcloud.util.jdbc.ConnectionUtil;
import com.vzcloud.util.jdbc.RDSConnection;
import com.vzcloud.util.msg.WarnMailUtil;
import com.vzcloud.util.msg.WarnPools;
import com.vzcloud.util.staticvar.BatchData;
import com.vzcloud.util.staticvar.StaticKeys;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @version v2.3
 * @ClassName:ScheduledTask.java
 * @author: http://www.wgstart.com
 * @date: November 16, 2019
 * @Description: ScheduledTask.java
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
@Component
public class ScheduledTask {

    private Logger logger = LoggerFactory.getLogger(ScheduledTask.class);

    /**
     * Thread Pool
     */
    static ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 40, 2, TimeUnit.MINUTES, new LinkedBlockingDeque<>());

    @Autowired
    SystemInfoService systemInfoService;
    @Autowired
    DeskStateService deskStateService;
    @Autowired
    LogInfoService logInfoService;
    @Autowired
    AppInfoService appInfoService;
    @Autowired
    CpuStateService cpuStateService;
    @Autowired
    MemStateService memStateService;
    @Autowired
    NetIoStateService netIoStateService;
    @Autowired
    SysLoadStateService sysLoadStateService;
    @Autowired
    TcpStateService tcpStateService;
    @Autowired
    AppStateService appStateService;
    @Autowired
    MailSetService mailSetService;
    @Autowired
    IntrusionInfoService intrusionInfoService;
    @Autowired
    HostInfoService hostInfoService;
    @Autowired
    DbInfoService dbInfoService;
    @Autowired
    DbTableService dbTableService;
    @Autowired
    DbTableCountService dbTableCountService;
    @Autowired
    HeathMonitorService heathMonitorService;
    @Autowired
    private RestUtil restUtil;
    @Autowired
    ConnectionUtil connectionUtil;
    @Autowired
    CommonConfig commonConfig;

    /**
     * Execute after 20 seconds
     * Initialization operation
     */
    @Scheduled(initialDelay = 20000L, fixedRate = 600 * 60 * 1000)
    public void initTask() {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            List<MailSet> list = mailSetService.selectAllByParams(params);
            if (list.size() > 0) {
                StaticKeys.mailSet = list.get(0);
            }
        } catch (Exception e) {
            logger.error("Initialization operation error", e);
        }

    }


    /**
     * Execute after 300 seconds
     * Detect whether the host has been offline and the detection process is offline
     */
    @Scheduled(initialDelay = 300000L, fixedRate = 20 * 60 * 1000)
    public void hostDownCheckTask() {
        Date date = DateUtil.getNowTime();
        long delayTime = 900 * 1000;

        try {
            Map<String, Object> params = new HashMap<String, Object>();
            List<SystemInfo> list = systemInfoService.selectAllByParams(params);
            if (!CollectionUtil.isEmpty(list)) {
                List<SystemInfo> updateList = new ArrayList<SystemInfo>();
                List<LogInfo> logInfoList = new ArrayList<LogInfo>();
                for (SystemInfo systemInfo : list) {

                    Date createTime = systemInfo.getCreateTime();
                    long diff = date.getTime() - createTime.getTime();
                    if (diff > delayTime) {
                        if (!StringUtils.isEmpty(WarnPools.MEM_WARN_MAP.get(systemInfo.getId()))) {
                            continue;
                        }
                        systemInfo.setState(StaticKeys.DOWN_STATE);
                        LogInfo logInfo = new LogInfo();
                        logInfo.setHostname("The host is offline:" + systemInfo.getHostname());
                        logInfo.setInfoContent("It may not be reported for more than 10 minutes, and it may have been offline:" + systemInfo.getHostname());
                        logInfo.setState(StaticKeys.LOG_ERROR);
                        logInfoList.add(logInfo);
                        updateList.add(systemInfo);
                        Runnable runnable = () -> {
                            WarnMailUtil.sendHostDown(systemInfo, true);
                        };
                        executor.execute(runnable);
                    } else {
                        if (!StringUtils.isEmpty(WarnPools.MEM_WARN_MAP.get(systemInfo.getId()))) {
                            Runnable runnable = () -> {
                                WarnMailUtil.sendHostDown(systemInfo, false);
                            };
                            executor.execute(runnable);
                        }
                    }
                }
                if (updateList.size() > 0) {
                    systemInfoService.updateRecord(updateList);
                }
                if (logInfoList.size() > 0) {
                    logInfoService.saveRecord(logInfoList);
                }
            }
        } catch (Exception e) {
            logger.error("Detect whether the host is offline error", e);
        }

        try {
            Map<String, Object> params = new HashMap<String, Object>();
            List<AppInfo> list = appInfoService.selectAllByParams(params);
            if (!CollectionUtil.isEmpty(list)) {
                List<AppInfo> updateList = new ArrayList<AppInfo>();
                List<LogInfo> logInfoList = new ArrayList<LogInfo>();
                for (AppInfo appInfo : list) {

                    Date createTime = appInfo.getCreateTime();
                    long diff = date.getTime() - createTime.getTime();
                    if (diff > delayTime) {
                        if (!StringUtils.isEmpty(WarnPools.MEM_WARN_MAP.get(appInfo.getId()))) {
                            continue;
                        }
                        appInfo.setState(StaticKeys.DOWN_STATE);
                        LogInfo logInfo = new LogInfo();
                        logInfo.setHostname("Process offline IP:" + appInfo.getHostname() + ",name:" + appInfo.getAppName());
                        logInfo.setInfoContent("If it is not reported for more than 10 minutes, it may have been offline IP:" + appInfo.getHostname() + ",name:" + appInfo.getAppName() + ", Process ID:" + appInfo.getAppPid());
                        logInfo.setState(StaticKeys.LOG_ERROR);
                        logInfoList.add(logInfo);
                        updateList.add(appInfo);
                        Runnable runnable = () -> {
                            WarnMailUtil.sendAppDown(appInfo, true);
                        };
                        executor.execute(runnable);
                    } else {
                        if (!StringUtils.isEmpty(WarnPools.MEM_WARN_MAP.get(appInfo.getId()))) {
                            Runnable runnable = () -> {
                                WarnMailUtil.sendAppDown(appInfo, false);
                            };
                            executor.execute(runnable);
                        }
                    }
                }
                if (updateList.size() > 0) {
                    appInfoService.updateRecord(updateList);
                }
                if (logInfoList.size() > 0) {
                    logInfoService.saveRecord(logInfoList);
                }
            }
        } catch (Exception e) {
            logger.error("Whether the test process is offline error", e);
        }


    }


    /**
     * After 90 seconds, execute, and then execute every 10 minutes, unit: MS.
     * Test a heartbeat
     */
    @Scheduled(initialDelay = 90000L, fixedRateString = "${base.heathTimes}")
    public void heathMonitorTask() {
        logger.info("heathMonitorTask------------" + DateUtil.getDateTimeString(new Date()));
        Map<String, Object> params = new HashMap<>();
        List<HeathMonitor> heathMonitors = new ArrayList<HeathMonitor>();
        List<LogInfo> logInfoList = new ArrayList<LogInfo>();
        Date date = DateUtil.getNowTime();
        try {
            List<HeathMonitor> heathMonitorAllList = heathMonitorService.selectAllByParams(params);
            if (heathMonitorAllList.size() > 0) {
                for (HeathMonitor h : heathMonitorAllList) {
                    int status = 500;
                    status = restUtil.get(h.getHeathUrl());
                    h.setCreateTime(date);
                    h.setHeathStatus(status + "");
                    heathMonitors.add(h);
                    if (!"200".equals(h.getHeathStatus())) {
                        if (!StringUtils.isEmpty(WarnPools.MEM_WARN_MAP.get(h.getId()))) {
                            continue;
                        }
                        LogInfo logInfo = new LogInfo();
                        logInfo.setHostname("Service interface detection abnormal:" + h.getAppName());
                        logInfo.setInfoContent("Service interface detection abnormal:" + h.getAppName() + "，" + h.getHeathUrl() + ", Back to status" + h.getHeathStatus());
                        logInfo.setState(StaticKeys.LOG_ERROR);
                        logInfoList.add(logInfo);
                        Runnable runnable = () -> {
                            WarnMailUtil.sendHeathInfo(h, true);
                        };
                        executor.execute(runnable);
                    } else {
                        if (!StringUtils.isEmpty(WarnPools.MEM_WARN_MAP.get(h.getId()))) {
                            Runnable runnable = () -> {
                                WarnMailUtil.sendHeathInfo(h, false);
                            };
                            executor.execute(runnable);
                        }
                    }
                }
                heathMonitorService.updateRecord(heathMonitors);
                if (logInfoList.size() > 0) {
                    logInfoService.saveRecord(logInfoList);
                }
            }
        } catch (Exception e) {
            logger.error("Service interface detection task error", e);
            logInfoService.save("Service interface detection error", e.toString(), StaticKeys.LOG_ERROR);
        }
    }


    /**
     * After 60 seconds, execute, and then execute every 120 minutes, unit: MS.
     * Data table monitoring
     */
    @Scheduled(initialDelay = 60000L, fixedRateString = "${base.dbTableTimes}")
    public void tableCountTask() {
        Map<String, Object> params = new HashMap<>();
        List<DbTable> dbTablesUpdate = new ArrayList<DbTable>();
        List<DbTableCount> dbTableCounts = new ArrayList<DbTableCount>();
        Date date = DateUtil.getNowTime();
        String sql = "";
        Long tableCount = 0l;
        try {
            List<DbInfo> dbInfos = dbInfoService.selectAllByParams(params);
            for (DbInfo dbInfo : dbInfos) {
                params.put("dbInfoId", dbInfo.getId());
                List<DbTable> dbTables = dbTableService.selectAllByParams(params);
                for (DbTable dbTable : dbTables) {
                    String whereAnd = "";
                    if (!StringUtils.isEmpty(dbTable.getWhereVal())) {
                        whereAnd = " and ";
                    }
                    if ("postgresql".equals(dbInfo.getDbType())) {
                        sql = RDSConnection.query_table_count_pg.replace("{tableName}", dbTable.getTableName()) + whereAnd + dbTable.getWhereVal();
                    } else {
                        sql = RDSConnection.query_table_count.replace("{tableName}", dbTable.getTableName()) + whereAnd + dbTable.getWhereVal();
                    }
                    tableCount = connectionUtil.queryTableCount(dbInfo, sql);
                    DbTableCount dbTableCount = new DbTableCount();
                    dbTableCount.setCreateTime(date);
                    dbTableCount.setDbTableId(dbTable.getId());
                    dbTableCount.setTableCount(tableCount);
                    dbTableCounts.add(dbTableCount);
                    dbTable.setDateStr(DateUtil.getDateTimeString(date));
                    dbTable.setTableCount(tableCount);
                    dbTablesUpdate.add(dbTable);
                }
            }
            if (dbTableCounts.size() > 0) {
                dbTableCountService.saveRecord(dbTableCounts);
                dbTableService.updateRecord(dbTablesUpdate);
            }
        } catch (Exception e) {
            logger.error("Data table monitoring task error", e);
            logInfoService.save("Data table monitoring task error", e.toString(), StaticKeys.LOG_ERROR);
        }
    }

    /**
     * After 30 seconds, execute, and then execute every 1 minute, unit: MS.
     * Submit data in batches
     */
    @Scheduled(initialDelay = 30000L, fixedRate = 1 * 60 * 1000)
    public synchronized void commitTask() {
        logger.info("Start submitting the monitoring data task in batches to start----------" + DateUtil.getCurrentDateTime());
        try {
            if (BatchData.APP_STATE_LIST.size() > 0) {
                List<AppState> APP_STATE_LIST = new ArrayList<AppState>();
                APP_STATE_LIST.addAll(BatchData.APP_STATE_LIST);
                BatchData.APP_STATE_LIST.clear();
                appStateService.saveRecord(APP_STATE_LIST);
            }
            if (BatchData.CPU_STATE_LIST.size() > 0) {
                List<CpuState> CPU_STATE_LIST = new ArrayList<CpuState>();
                CPU_STATE_LIST.addAll(BatchData.CPU_STATE_LIST);
                BatchData.CPU_STATE_LIST.clear();
                cpuStateService.saveRecord(CPU_STATE_LIST);
            }
            if (BatchData.MEM_STATE_LIST.size() > 0) {
                List<MemState> MEM_STATE_LIST = new ArrayList<MemState>();
                MEM_STATE_LIST.addAll(BatchData.MEM_STATE_LIST);
                BatchData.MEM_STATE_LIST.clear();
                memStateService.saveRecord(MEM_STATE_LIST);
            }
            if (BatchData.NETIO_STATE_LIST.size() > 0) {
                List<NetIoState> NETIO_STATE_LIST = new ArrayList<NetIoState>();
                NETIO_STATE_LIST.addAll(BatchData.NETIO_STATE_LIST);
                BatchData.NETIO_STATE_LIST.clear();
                netIoStateService.saveRecord(NETIO_STATE_LIST);
            }
            if (BatchData.SYSLOAD_STATE_LIST.size() > 0) {
                List<SysLoadState> SYSLOAD_STATE_LIST = new ArrayList<SysLoadState>();
                SYSLOAD_STATE_LIST.addAll(BatchData.SYSLOAD_STATE_LIST);
                BatchData.SYSLOAD_STATE_LIST.clear();
                sysLoadStateService.saveRecord(SYSLOAD_STATE_LIST);
            }
            if (BatchData.LOG_INFO_LIST.size() > 0) {
                List<LogInfo> LOG_INFO_LIST = new ArrayList<LogInfo>();
                LOG_INFO_LIST.addAll(BatchData.LOG_INFO_LIST);
                BatchData.LOG_INFO_LIST.clear();
                logInfoService.saveRecord(LOG_INFO_LIST);
            }
            if (BatchData.DESK_STATE_LIST.size() > 0) {
                Map<String, Object> paramsDel = new HashMap<String, Object>();

                List<DeskState> DESK_STATE_LIST = new ArrayList<DeskState>();
                DESK_STATE_LIST.addAll(BatchData.DESK_STATE_LIST);
                BatchData.DESK_STATE_LIST.clear();
                List<String> hostnameList = new ArrayList<String>();
                for (DeskState deskState : DESK_STATE_LIST) {
                    if (!hostnameList.contains(deskState.getHostname())) {
                        hostnameList.add(deskState.getHostname());
                    }
                }
                for (String hostname : hostnameList) {
                    paramsDel.put("hostname", hostname);
                    deskStateService.deleteByAccHname(paramsDel);
                }
                deskStateService.saveRecord(DESK_STATE_LIST);
            }
            if (BatchData.SYSTEM_INFO_LIST.size() > 0) {
                Map<String, Object> paramsDel = new HashMap<String, Object>();
                List<SystemInfo> SYSTEM_INFO_LIST = new ArrayList<SystemInfo>();
                SYSTEM_INFO_LIST.addAll(BatchData.SYSTEM_INFO_LIST);
                BatchData.SYSTEM_INFO_LIST.clear();
                List<SystemInfo> updateList = new ArrayList<SystemInfo>();
                List<SystemInfo> insertList = new ArrayList<SystemInfo>();
                List<SystemInfo> savedList = systemInfoService.selectAllByParams(paramsDel);
                for (SystemInfo systemInfo : SYSTEM_INFO_LIST) {
                    boolean issaved = false;
                    for (SystemInfo systemInfoS : savedList) {
                        if (systemInfoS.getHostname().equals(systemInfo.getHostname())) {
                            systemInfo.setId(systemInfoS.getId());
                            updateList.add(systemInfo);
                            issaved = true;
                            break;
                        }
                    }
                    if (!issaved) {
                        insertList.add(systemInfo);
                    }
                }
                systemInfoService.updateRecord(updateList);
                systemInfoService.saveRecord(insertList);
            }
            if (BatchData.APP_INFO_LIST.size() > 0) {
                Map<String, Object> paramsDel = new HashMap<String, Object>();
                List<AppInfo> APP_INFO_LIST = new ArrayList<AppInfo>();
                APP_INFO_LIST.addAll(BatchData.APP_INFO_LIST);
                BatchData.APP_INFO_LIST.clear();

                List<AppInfo> updateList = new ArrayList<AppInfo>();
                List<AppInfo> insertList = new ArrayList<AppInfo>();
                List<AppInfo> savedList = appInfoService.selectAllByParams(paramsDel);
                for (AppInfo systemInfo : APP_INFO_LIST) {
                    boolean issaved = false;
                    for (AppInfo systemInfoS : savedList) {
                        if (systemInfoS.getHostname().equals(systemInfo.getHostname()) && systemInfoS.getAppPid().equals(systemInfo.getAppPid())) {
                            systemInfo.setId(systemInfoS.getId());
                            updateList.add(systemInfo);
                            issaved = true;
                            break;
                        }
                    }
                    if (!issaved) {
                        insertList.add(systemInfo);
                    }
                }
                appInfoService.updateRecord(updateList);
                appInfoService.saveRecord(insertList);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            logger.error("Submit monitoring data errors in batches----------", e);
            logInfoService.save("commitTask", "Submit monitoring data errors in batches:" + e.toString(), StaticKeys.LOG_ERROR);
        }
        logger.info("Submit the monitoring data task in batches, the end of the monitoring data----------" + DateUtil.getCurrentDateTime());
    }

    @Autowired
    SystemInfoMapper systemInfoMapper;
    @Autowired
    CpuStateMapper cpuStateMapper;
    @Autowired
    DeskStateMapper deskStateMapper;
    @Autowired
    MemStateMapper memStateMapper;
    @Autowired
    NetIoStateMapper netIoStateMapper;
    @Autowired
    SysLoadStateMapper sysLoadStateMapper;
    @Autowired
    TcpStateMapper tcpStateMapper;
    @Autowired
    AppInfoMapper appInfoMapper;
    @Autowired
    AppStateMapper appStateMapper;
    @Autowired
    MailSetMapper mailSetMapper;
    @Autowired
    IntrusionInfoMapper intrusionInfoMapper;
    @Autowired
    LogInfoMapper logInfoMapper;

    /**
     * Perform every day at 1:10 in the morning
     * Delete historical data, 15 days
     */
    @Scheduled(cron = "0 10 1 * * ?")
    public void clearHisdataTask() {
        logger.info("Timed to empty the historical data task to start----------" + DateUtil.getCurrentDateTime());
        WarnPools.clearOldData();//Clear the record of the alarm email
        String nowTime = DateUtil.getCurrentDateTime();
        //15 days ago time
        String thrityDayBefore = DateUtil.getDateBefore(nowTime, 15);
        Map<String, Object> paramsDel = new HashMap<String, Object>();
        try {
            paramsDel.put(StaticKeys.SEARCH_END_TIME, thrityDayBefore);
            //Execute the deletion operation begin
            if (paramsDel.get(StaticKeys.SEARCH_END_TIME) != null && !"".equals(paramsDel.get(StaticKeys.SEARCH_END_TIME))) {
                cpuStateMapper.deleteByAccountAndDate(paramsDel);//Delete CPU monitoring information
                deskStateMapper.deleteByAccountAndDate(paramsDel);//Delete disk monitoring information
                memStateMapper.deleteByAccountAndDate(paramsDel);//Delete memory monitoring information
                netIoStateMapper.deleteByAccountAndDate(paramsDel);//Delete through throughput monitoring information
                sysLoadStateMapper.deleteByAccountAndDate(paramsDel);//Delete load status monitoring information
                tcpStateMapper.deleteByAccountAndDate(paramsDel);//Delete TCP monitoring information
                appStateMapper.deleteByDate(paramsDel);
                //Delete the log information 15 days ago
                logInfoMapper.deleteByDate(paramsDel);
                //Delete the statistics of the database table 15 days ago
                dbTableCountService.deleteByDate(paramsDel);

                logInfoService.save("Cleaning historical data is completed regularly", "The historical data is completed from time to time:", StaticKeys.LOG_ERROR);
            }
            //Execute the deletion operation end

        } catch (Exception e) {
            logger.error("The historical data task is errors in time:", e);
            logInfoService.save("Timed clear hiClear historical data errors regularly:ta errors", "Clear historical data errors regularly:" + e.toString(), StaticKeys.LOG_ERROR);
        }
        logger.info("Timing the end of the historical data task ends----------" + DateUtil.getCurrentDateTime());
    }


}
