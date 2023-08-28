package com.wgcloud.util.staticvar;


import com.wgcloud.entity.MailSet;

/**
 * @version v2.3
 * @ClassName:StaticKeys.java
 * @author: http://www.vzstart.com
 * @date: November 16, 2019
 * @Description: StaticKeys.java
 * @Copyright: 2017-2022 vzcloud. All rights reserved.
 */
public class StaticKeys {

    public static final String ADMIN_ACCOUNT = "admin";//Administrator account

    public static final String SEARCH_START_TIME = "startTime";//Date query start time condition

    public static final String SEARCH_END_TIME = "endTime";//Date query end time condition

    public static final String SEARCH_EXP_TIME = "expDate";//Date query expiration time

    public static String MENU_ACTIVE = "active ";//Menu highlight display

    public static String APP_PRO_FILE = "application.properties";//Configuration information file

    //The verification code kept in session
    public static final String SESSION_CODE = "valcode";

    //SESSION storage login information logo
    public static String LOGIN_KEY = "LOGIN_KEY";

    public static String DASH_VIEW_ACCOUNT = "dashView";


    //Log failure mark
    public static final String LOG_ERROR = "1";

    //Do not send alarm SMS mail logo
    public static final String NO_SEND_WARN = "false";

    public static String SPLIT_BR = "</br>";//Replacement logo

    public static String SPLIT_KG = " ";//Space

    public static String SPLIT_DH = ",";//comma

    public static String SPLIT_SXG = "//";//Double reflect

    public static String DOWN_STATE = "2";

    public static MailSet mailSet = null;

}
