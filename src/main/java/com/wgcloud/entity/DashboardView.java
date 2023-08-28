package com.wgcloud.entity;

/**
 * @version v2.3
 * @ClassName:DashboardView.java
 * @author: http://www.wgstart.com
 * @date: November 16, 2019
 * @Description: Summary information of the main panel
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
public class DashboardView extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = -1262528746414406709L;


    /**
     * host name
     */
    private String hostname;

    /**
     * System version information
     */
    private String version;

    /**
     * How many days have the system running
     */
    private String yxDays;

    /**
     * Memory has been used percentage
     */
    private double memPer;

    /**
     * Update time
     */
    private String dateStr;

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getYxDays() {
        return yxDays;
    }

    public void setYxDays(String yxDays) {
        this.yxDays = yxDays;
    }

    public double getMemPer() {
        return memPer;
    }

    public void setMemPer(double memPer) {
        this.memPer = memPer;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }
}