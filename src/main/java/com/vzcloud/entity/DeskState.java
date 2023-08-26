package com.vzcloud.entity;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * @version v2.3
 * @ClassName:DeskState.java
 * @author: http://www.wgstart.com
 * @date: November 16, 2019
 * @Description: Check the disk size use information
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
public class DeskState extends BaseEntity {


    /**
     *
     */
    private static final long serialVersionUID = 879979812204191283L;


    /**
     * host name
     */
    private String hostname;

    /**
     * Disk type
     */
    private String fileSystem;

    /**
     * Partition size
     */
    private String size;

    /**
     * Used
     */
    private String used;

    /**
     * Available
     */
    private String avail;

    /**
     * Percent
     */
    private String usePer;

    /**
     * add time
     * yyyy-MM-dd hh:mm:ss
     */
    private String dateStr;

    /**
     * Creation time
     */
    private Date createTime;


    public String getFileSystem() {
        return fileSystem;
    }

    public void setFileSystem(String fileSystem) {
        this.fileSystem = fileSystem;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
    }

    public String getAvail() {
        return avail;
    }

    public void setAvail(String avail) {
        this.avail = avail;
    }

    public String getUsePer() {
        return usePer;
    }

    public void setUsePer(String usePer) {
        this.usePer = usePer;
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