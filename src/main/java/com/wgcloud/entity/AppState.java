package com.wgcloud.entity;

import org.apache.commons.lang3.StringUtils;

import com.wgcloud.util.DateUtil;

import java.util.Date;

/**
 * @version v2.3
 * @ClassName:AppState.java
 * @author: http://www.wgstart.com
 * @date: November 16, 2019
 * @Description: APP status monitoring
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
public class AppState extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = -2913111613773445949L;


    /**
     * Application information ID
     */
    private String appInfoId;


    /**
     * %CPU
     */
    private Double cpuPer;

    /**
     * %mem
     */
    private Double memPer;

    /**
     * add time
     * MM-dd hh:mm:ss
     */
    private String dateStr;

    /**
     * Creation time
     */
    private Date createTime;


    public Double getCpuPer() {
        return cpuPer;
    }

    public void setCpuPer(Double cpuPer) {
        this.cpuPer = cpuPer;
    }


    public String getAppInfoId() {
        return appInfoId;
    }

    public void setAppInfoId(String appInfoId) {
        this.appInfoId = appInfoId;
    }

    public Double getMemPer() {
        return memPer;
    }

    public void setMemPer(Double memPer) {
        this.memPer = memPer;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDateStr() {
        String str = DateUtil.getDateTimeString(createTime);
        if (!StringUtils.isEmpty(str) && str.length() > 16) {
            return str.substring(5);
        }
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }


}