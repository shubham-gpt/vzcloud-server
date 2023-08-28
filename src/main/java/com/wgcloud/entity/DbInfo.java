package com.wgcloud.entity;

import java.sql.Timestamp;

/**
 * @version v2.3
 * @ClassName:DbInfo.java
 * @author: http://www.wgstart.com
 * @date: November 16, 2019
 * @Description: Check the system invasion information
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
public class DbInfo extends BaseEntity {


    /**
     *
     */
    private static final long serialVersionUID = 879979812204191283L;


    /**
     * Connect
     */
    private String aliasName;


    /**
     * Database type, MySQL or Oracle
     */
    private String dbType;

    /**
     * database username
     */
    private String user;

    /**
     * Database password
     */
    private String passwd;

    /**
     * Database server IP
     */
    private String ip;

    /**
     * Database port
     */
    private String port;

    /**
     * Name database
     */
    private String dbName;

    /**
     * Database connection status,1 normal, 2 failed
     */
    private String dbState;

    /**
     * Creation time
     */
    private Timestamp createTime;

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbState() {
        return dbState;
    }

    public void setDbState(String dbState) {
        this.dbState = dbState;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }
}