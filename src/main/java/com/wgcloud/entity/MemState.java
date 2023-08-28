package com.wgcloud.entity;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * @version v2.3
 * @ClassName:MemState.java
 * @author: http://www.wgstart.com
 * @date: November 16, 2019
 * @Description: View memory usage
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
public class MemState extends BaseEntity {


    /**
     *
     */
    private static final long serialVersionUID = -1412473355088780549L;


    /**
     * host name
     */
    private String hostname;

    /**
     * Total memory, m
     */
    private String total;

    /**
     * How much is used, m
     */
    private String used;

    /**
     * Unused, m
     */
    private String free;

    /**
     * Percent%
     */
    private Double usePer;

    /**
     * add time
     * yyyy-MM-dd hh:mm:ss
     */
    private String dateStr;

    /**
     * Creation time
     */
    private Date createTime;


    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
    }

    public String getFree() {
        return free;
    }

    public void setFree(String free) {
        this.free = free;
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

    public Double getUsePer() {
        return usePer;
    }

    public void setUsePer(Double usePer) {
        this.usePer = usePer;
    }


}