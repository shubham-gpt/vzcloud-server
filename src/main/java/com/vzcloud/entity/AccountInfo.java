package com.vzcloud.entity;

import java.util.Date;

/**
 * @version v2.3
 * @ClassName:AccountInfo.java
 * @author: http://www.wgstart.com
 * @date: November 16, 2019
 * @Description: AccountInfo.java
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
public class AccountInfo extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = -6510176051328150406L;

    /**
     * log in account
     */
    private String account;

    /**
     * Key identification
     */
    private String accountMd5;

    /**
     * User role, admin administrator, User ordinary user,Test test user
     */
    private String role;

    /**
     * Mail
     */
    private String email;

    /**
     * password
     */
    private String passwd;

    /**
     * phone number
     */
    private String phone;

    /**
     * Expiration
     */
    private Date expDate;

    /**
     * actual name
     */
    private String realName;


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public String getAccountMd5() {
        return accountMd5;
    }

    public void setAccountMd5(String accountMd5) {
        this.accountMd5 = accountMd5;
    }


}