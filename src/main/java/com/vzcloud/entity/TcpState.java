package com.vzcloud.entity;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * @version v2.3
 * @ClassName:TcpState.java
 * @author: http://www.wgstart.com
 * @date: November 16, 2019
 * @Description: View TCP connection status
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
public class TcpState extends BaseEntity {


    /**
     *
     */
    private static final long serialVersionUID = -299667815095138020L;
    /**
     * host name
     */
    private String hostname;

    /**
     *The number of TCP connections sponsored every second, not only the TCP connection created by Connect;,active/s
     */
    private String active;

    /**
     * The number of TCP connections sponsored per second, that is, the TCP connection created by access is called,passive/s
     */
    private String passive;

    /**
     * The number of TCP transmission per second,retrans/s
     */
    private String retrans;

    /**
     * add time
     * yyyy-MM-dd hh:mm:ss
     */
    private String dateStr;

    /**
     * Creation time
     */
    private Date createTime;


    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getPassive() {
        return passive;
    }

    public void setPassive(String passive) {
        this.passive = passive;
    }

    public String getRetrans() {
        return retrans;
    }

    public void setRetrans(String retrans) {
        this.retrans = retrans;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDateStr() {
        if (!StringUtils.isEmpty(dateStr) && dateStr.length() > 16) {
            return dateStr.substring(5);
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