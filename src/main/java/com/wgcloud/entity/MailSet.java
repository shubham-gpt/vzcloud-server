package com.wgcloud.entity;

import java.util.Date;

/**
 * @version v2.3
 * @ClassName:DiskIoState.java
 * @author: http://www.wgstart.com
 * @date: November 16, 2019
 * @Description: View Disk IO usage
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
public class MailSet extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = -8284741180883299533L;


    /**
     * Whether to send an email alarm,1 Send 0 without sending
     */
    private String sendMail;

    /**
     * Send the account of the mailbox
     */
    private String fromMailName;

    /**
     * Send the password of the mailbox
     */
    private String fromPwd;

    /**
     * Send the SMTP server of the mailbox
     */
    private String smtpHost;

    /**
     * SMTP port of sending mailboxes,25 or 465
     */
    private String smtpPort;

    /**
* Send the mailbox whether to enable a security link (SSL), 1 enable, 0 will not be enabled     */
    private String smtpSSL;

    /*** Email of receiving alarm information
     */
    private String toMail;

    /**
     * CPU usage rate
     */
    private String cpuPer;
    /**
     * MEM usage rate alarm value
     */
    private String memPer;
    /**
     * MEM usage rate alarm value
     */
    private String heathPer;


    /**
     * Creation time
     */
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSendMail() {
        return sendMail;
    }

    public void setSendMail(String sendMail) {
        this.sendMail = sendMail;
    }

    public String getFromMailName() {
        return fromMailName;
    }

    public void setFromMailName(String fromMailName) {
        this.fromMailName = fromMailName;
    }

    public String getFromPwd() {
        return fromPwd;
    }

    public void setFromPwd(String fromPwd) {
        this.fromPwd = fromPwd;
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public String getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(String smtpPort) {
        this.smtpPort = smtpPort;
    }

    public String getSmtpSSL() {
        return smtpSSL;
    }

    public void setSmtpSSL(String smtpSSL) {
        this.smtpSSL = smtpSSL;
    }

    public String getToMail() {
        return toMail;
    }

    public void setToMail(String toMail) {
        this.toMail = toMail;
    }

    public String getCpuPer() {
        return cpuPer;
    }

    public void setCpuPer(String cpuPer) {
        this.cpuPer = cpuPer;
    }

    public String getMemPer() {
        return memPer;
    }

    public void setMemPer(String memPer) {
        this.memPer = memPer;
    }

    public String getHeathPer() {
        return heathPer;
    }

    public void setHeathPer(String heathPer) {
        this.heathPer = heathPer;
    }
}