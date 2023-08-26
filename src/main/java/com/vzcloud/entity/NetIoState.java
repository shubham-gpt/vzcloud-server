package com.vzcloud.entity;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * @version v2.3
 * @ClassName:NetIoState.java
 * @author: http://www.wgstart.com
 * @date: November 16, 2019
 * @Description: Venus of network equipment
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
public class NetIoState extends BaseEntity {


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
    private String rxpck;

    /**
     * Data packets sent per second,txpck/s
     */
    private String txpck;


    /**
     * Number of KB received per second,rxkB/s
     */
    private String rxbyt;


    /**
     * The number of KB sent per second,txkB/s
     */
    private String txbyt;


    /*** Compressed data packet received per second,rxcmp/s
     */
    private String rxcmp;


    /**
     * The compressed data packet sent per second,txcmp/s
     */
    private String txcmp;


    /**
     * Multi -broadcast packets received per second,rxmcst/s
     */
    private String rxmcst;

    /**
     * add time
     * yyyy-MM-dd hh:mm:ss
     */
    private String dateStr;

    /**
     * Creation time
     */
    private Date createTime;


    public String getRxpck() {
        return rxpck;
    }

    public void setRxpck(String rxpck) {
        this.rxpck = rxpck;
    }

    public String getTxpck() {
        return txpck;
    }

    public void setTxpck(String txpck) {
        this.txpck = txpck;
    }

    public String getRxbyt() {
        return rxbyt;
    }

    public void setRxbyt(String rxbyt) {
        this.rxbyt = rxbyt;
    }

    public String getTxbyt() {
        return txbyt;
    }

    public void setTxbyt(String txbyt) {
        this.txbyt = txbyt;
    }

    public String getRxcmp() {
        return rxcmp;
    }

    public void setRxcmp(String rxcmp) {
        this.rxcmp = rxcmp;
    }

    public String getTxcmp() {
        return txcmp;
    }

    public void setTxcmp(String txcmp) {
        this.txcmp = txcmp;
    }

    public String getRxmcst() {
        return rxmcst;
    }

    public void setRxmcst(String rxmcst) {
        this.rxmcst = rxmcst;
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