package com.vzcloud.entity;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * @version v2.3
 * @ClassName:DbTableCount.java
 * @author: http://www.wgstart.com
 * @date: November 16, 2019
 * @Description: Check the system invasion information
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
public class DbTableCount extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 879979812204191283L;

    /**
     * Table ID
     */
    private String dbTableId;

    /**
     * W data amount
     */
    private Long tableCount;

    /**
     * add time
     * MM-dd hh:mm:ss
     */
    private String dateStr;

    /**
     * Creation time
     */
    private Date createTime;

    public String getDbTableId() {
        return dbTableId;
    }

    public void setDbTableId(String dbTableId) {
        this.dbTableId = dbTableId;
    }

    public Long getTableCount() {
        return tableCount;
    }

    public void setTableCount(Long tableCount) {
        this.tableCount = tableCount;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}