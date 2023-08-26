package com.vzcloud.dto;

import org.apache.commons.lang3.StringUtils;

import com.vzcloud.entity.BaseEntity;

import java.util.Date;

/**
 * @version v2.3
 * @ClassName:NetIoState.java
 * @author: http://www.wgstart.com
 * @date: November 16, 2019
 * @Description: Venus of network equipment
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
public class NetIoStateDto extends BaseEntity {


    /**
     *
     */
    private static final long serialVersionUID = -8314012397341825158L;


    /**
     * host name
     */
    private String hostname;

    /**
     * Data packets received per second,rxpck/s
     */
    private Integer rxpck;

    /**
     * Data packets sent per second,txpck/s
     */
    private Integer txpck;


    /**
     * Number of KB received per second,rxkB/s
     */
    private Integer rxbyt;


    /**
     * The number of KB sent per second,txkB/s
     */
    private Integer txbyt;


    /**
     * add time
     * yyyy-MM-dd hh:mm:ss
     */
    private String dateStr;

    /**
     * Creation time
     */
    private Date createTime;

    public Integer getRxpck() {
        return rxpck;
    }

    public void setRxpck(Integer rxpck) {
        this.rxpck = rxpck;
    }

    public Integer getTxpck() {
        return txpck;
    }

    public void setTxpck(Integer txpck) {
        this.txpck = txpck;
    }

    public Integer getRxbyt() {
        return rxbyt;
    }

    public void setRxbyt(Integer rxbyt) {
        this.rxbyt = rxbyt;
    }

    public Integer getTxbyt() {
        return txbyt;
    }

    public void setTxbyt(Integer txbyt) {
        this.txbyt = txbyt;
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