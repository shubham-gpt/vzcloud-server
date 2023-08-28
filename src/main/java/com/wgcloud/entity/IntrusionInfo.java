package com.wgcloud.entity;

import java.sql.Timestamp;

/**
 * @version v2.3
 * @ClassName:IntrusionInfo.java
 * @author: http://www.wgstart.com
 * @date: November 16, 2019
 * @Description: Check the system invasion information
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
public class IntrusionInfo extends BaseEntity {


    /**
     *
     */
    private static final long serialVersionUID = 879979812204191283L;


    /**
     * host名称
     */
    private String hostname;

    /**
     * System kernel module
     */
    private String lsmod;

    /**
     * View the passwd file modification time
     */
    private String passwdInfo;

    /**
     * View the system plan task
     */
    private String crontab;

    /**
     * Check the network, the normal network card should not be in the PROMISC mode.
     */
    private String promisc;

    /**
     * System RPC service
     */
    private String rpcinfo;


    /**
     * Creation time
     */
    private Timestamp createTime;


    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getLsmod() {
        return lsmod;
    }

    public void setLsmod(String lsmod) {
        this.lsmod = lsmod;
    }

    public String getPasswdInfo() {
        return passwdInfo;
    }

    public void setPasswdInfo(String passwdInfo) {
        this.passwdInfo = passwdInfo;
    }

    public String getCrontab() {
        return crontab;
    }

    public void setCrontab(String crontab) {
        this.crontab = crontab;
    }

    public String getPromisc() {
        return promisc;
    }

    public void setPromisc(String promisc) {
        this.promisc = promisc;
    }

    public String getRpcinfo() {
        return rpcinfo;
    }

    public void setRpcinfo(String rpcinfo) {
        this.rpcinfo = rpcinfo;
    }


}