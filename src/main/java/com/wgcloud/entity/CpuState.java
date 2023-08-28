package com.wgcloud.entity;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * @version v2.3
 * @ClassName:CpuState.java
 * @author: http://www.wgstart.com
 * @date: November 16, 2019
 * @Description: View CPU usage
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
public class CpuState extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = -2913111613773445949L;


    /**
     * host name
     */
    private String hostname;

    /**
     * CPU time of user mode (%)
     */
    private String user;

    /**
     * CPU usage rate
     */
    private Double sys;

    /**
     * Current leisure rate
     */
    private Double idle;

    /**
     * CPU current waiting rate
     */
    private Double iowait;

    /**
     * Hard interrupt time (%） Abandon
     */
    private String irq;

    /**
     * Soft interrupt time (%） Abandonandon
     */
    private String soft;

    /**
     * add time
     * MM-dd hh:mm:ss
     */
    private String dateStr;

    /**
     * Creation time
     */
    private Date createTime;


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Double getSys() {
        return sys;
    }

    public void setSys(Double sys) {
        this.sys = sys;
    }

    public Double getIdle() {
        return idle;
    }

    public void setIdle(Double idle) {
        this.idle = idle;
    }

    public Double getIowait() {
        return iowait;
    }

    public void setIowait(Double iowait) {
        this.iowait = iowait;
    }

    public String getIrq() {
        return irq;
    }

    public void setIrq(String irq) {
        this.irq = irq;
    }

    public String getSoft() {
        return soft;
    }

    public void setSoft(String soft) {
        this.soft = soft;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDateStr() {
        if (!StringUtils.isEmpty(dateStr) && dateStr.length() > 16) {
            return dateStr.substring(11);
        }
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }


}