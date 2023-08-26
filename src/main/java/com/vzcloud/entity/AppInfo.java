package com.vzcloud.entity;

import java.util.Date;

/**
 * @version v2.3
 * @ClassName:AppInfo.java
 * @author: http://www.wgstart.com
 * @date: November 16, 2019
 * @Description: APP port information
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
public class AppInfo extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = -2913111613773445949L;


    /**
     * host name
     */
    private String hostname;

    /**
     * Application process ID
     */
    private String appPid;

    /**
     * Process acquisition pathway, 1 process ID number, 2 process PID file
     */
    private String appType;

    /**
     * Process name
     */
    private String appName;

    /**
     * Memory usage rate%
     */
    private Double memPer;

    /**
     * CPU usage rate%
     */
    private Double cpuPer;


    /**
     * Process status, 1 normal, 2 offline
     */
    private String state;


    /**
     * Creation time
     */
    private Date createTime;


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public String getAppPid() {
        return appPid;
    }

    public void setAppPid(String appPid) {
        this.appPid = appPid;
    }


    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Double getMemPer() {
        return memPer;
    }

    public void setMemPer(Double memPer) {
        this.memPer = memPer;
    }

    public Double getCpuPer() {
        return cpuPer;
    }

    public void setCpuPer(Double cpuPer) {
        this.cpuPer = cpuPer;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}