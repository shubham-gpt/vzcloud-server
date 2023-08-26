package com.vzcloud.entity;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * @version v2.3
 * @ClassName:SysLoadState.java
 * @author: http://www.wgstart.com
 * @date: November 16, 2019
 * @Description: uptime View the system load status
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
public class SysLoadState extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = -4863071148000213553L;

    /**
     * host name
     */
    private String hostname;

    /**
     * 1 minute ago to the current load
     */
    private Double oneLoad;

    /**
     * 5 minutes ago to the current load
     */
    private Double fiveLoad;

    /**
     * 15 minutes ago to the current load
     */
    private Double fifteenLoad;

    /**
     * Login user quantity Abandon
     */
    private String users;

    /**
     * add time
     * yyyy-MM-dd hh:mm:ss
     */
    private String dateStr;

    /**
     * Creation time
     */
    private Date createTime;


    public Double getOneLoad() {
        return oneLoad;
    }

    public void setOneLoad(Double oneLoad) {
        this.oneLoad = oneLoad;
    }

    public Double getFiveLoad() {
        return fiveLoad;
    }

    public void setFiveLoad(Double fiveLoad) {
        this.fiveLoad = fiveLoad;
    }

    public Double getFifteenLoad() {
        return fifteenLoad;
    }

    public void setFifteenLoad(Double fifteenLoad) {
        this.fifteenLoad = fifteenLoad;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
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