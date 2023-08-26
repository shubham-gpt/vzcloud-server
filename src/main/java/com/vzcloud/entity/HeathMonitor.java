package com.vzcloud.entity;

import java.util.Date;

/**
 * @version v2.3
 * @ClassName:HeathMonitor.java
 * @author: http://www.wgstart.com
 * @date: November 16, 2019
 * @Description: APP port information
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
public class HeathMonitor extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = -2913111613773445949L;


    /**
     * Application service name
     */
    private String appName;

    /**
     * Heartbeat detection URL
     */
    private String heathUrl;

    /**
     * state
     */
    private String heathStatus;


    /**
     * Creation time
     */
    private Date createTime;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getHeathUrl() {
        return heathUrl;
    }

    public void setHeathUrl(String heathUrl) {
        this.heathUrl = heathUrl;
    }

    public String getHeathStatus() {
        return heathStatus;
    }

    public void setHeathStatus(String heathStatus) {
        this.heathStatus = heathStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}