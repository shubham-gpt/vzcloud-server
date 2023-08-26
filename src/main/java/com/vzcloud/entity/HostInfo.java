package com.vzcloud.entity;

import java.util.Date;

/**
 * @version v2.3
 * @ClassName:HostInfo.java
 * @author: http://www.wgstart.com
 * @date: November 16, 2019
 * @Description: Host's IP password and other information, unused yet
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
public class HostInfo extends BaseEntity {


    /**
     *
     */
    private static final long serialVersionUID = 3875927332935900938L;


    /**
     * host name
     */
    private String ip;

    /**
     * user
     */
    private String root;

    /**
     * SSH port
     */
    private String port;

    /**
     * password
     */
    private String passwd;

    /**
     * password
     */
    private String remark;


    /**
     * Creation time
     */
    private Date createTime;


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}