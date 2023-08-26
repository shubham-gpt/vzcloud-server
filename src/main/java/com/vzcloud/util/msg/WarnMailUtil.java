package com.vzcloud.util.msg;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vzcloud.common.ApplicationContextHelper;
import com.vzcloud.config.MailConfig;
import com.vzcloud.entity.*;
import com.vzcloud.service.LogInfoService;
import com.vzcloud.util.staticvar.StaticKeys;

import java.util.Date;

/**
 * @version v2.3
 * @ClassName:WarnMailUtil.java
 * @author: http://www.wgstart.com
 * @date: November 16, 2019
 * @Description: WarnMailUtil.java
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
public class WarnMailUtil {

    private static final Logger logger = LoggerFactory.getLogger(WarnMailUtil.class);

    public static final String content_suffix = "<p><a target='_blank' href='http://www.wgstart.com'>WGCLOUD</a>敬上";

    private static LogInfoService logInfoService = (LogInfoService) ApplicationContextHelper.getBean(LogInfoService.class);
    private static MailConfig mailConfig = (MailConfig) ApplicationContextHelper.getBean(MailConfig.class);


    /**
     * Determine whether the system's memory usage exceeds 98%, if it exceeds, send a warning mail
     *
     * @param memState
     * @param toMail
     * @return
     */
    public static boolean sendWarnInfo(MemState memState) {
        if (StaticKeys.mailSet == null) {
            return false;
        }
        MailSet mailSet = StaticKeys.mailSet;
        if (StaticKeys.NO_SEND_WARN.equals(mailConfig.getAllWarnMail()) || StaticKeys.NO_SEND_WARN.equals(mailConfig.getMemWarnMail())) {
            return false;
        }
        String key = memState.getHostname();
        if (!StringUtils.isEmpty(WarnPools.MEM_WARN_MAP.get(key))) {
            return false;
        }
        if (memState.getUsePer() != null && memState.getUsePer() >= mailConfig.getMemWarnVal()) {
            try {
                String title = "Memory alarm:" + memState.getHostname();
                String commContent = "server:" + memState.getHostname() + ",Memory usage is" + Double.valueOf(memState.getUsePer()) + "%，There may be abnormalities, please see ";
                //send email
                sendMail(mailSet.getToMail(), title, commContent);
                //The mark has been sent to the alarm information
                WarnPools.MEM_WARN_MAP.put(key, "1");
                //Record sending information
                logInfoService.save(title, commContent, StaticKeys.LOG_ERROR);
            } catch (Exception e) {
                logger.error("Send memory and police mail failed:", e);
                logInfoService.save("Send memory and police mail error", e.toString(), StaticKeys.LOG_ERROR);
            }
        }

        return false;
    }

    /**
     * Determine whether the use of the CPU of the system exceeds 98%.
     *
     * @param cpuState
     * @param toMail
     * @return
     */
    public static boolean sendCpuWarnInfo(CpuState cpuState) {
        if (StaticKeys.mailSet == null) {
            return false;
        }
        MailSet mailSet = StaticKeys.mailSet;
        if (StaticKeys.NO_SEND_WARN.equals(mailConfig.getAllWarnMail()) || StaticKeys.NO_SEND_WARN.equals(mailConfig.getCpuWarnMail())) {
            return false;
        }
        String key = cpuState.getHostname();
        if (!StringUtils.isEmpty(WarnPools.MEM_WARN_MAP.get(key))) {
            return false;
        }
        if (cpuState.getSys() != null && cpuState.getSys() >= mailConfig.getCpuWarnVal()) {
            try {
                String title = "CPU alarm:" + cpuState.getHostname();
                String commContent = "server:" + cpuState.getHostname() + ",CPU usage is" + Double.valueOf(cpuState.getSys()) + "%，There may be abnormalities, please see";
                //send email
                sendMail(mailSet.getToMail(), title, commContent);
                //The mark has been sent to the alarm information
                WarnPools.MEM_WARN_MAP.put(key, "1");
                //Record sending information
                logInfoService.save(title, commContent, StaticKeys.LOG_ERROR);
            } catch (Exception e) {
                logger.error("Send memory and police mail failed:", e);
                logInfoService.save("Send memory and police mail error", e.toString(), StaticKeys.LOG_ERROR);
            }
        }

        return false;
    }


    /**
     *The service interface does not pass through the alarm mail
     *
     * @param cpuState
     * @param toMail
     * @return
     */
    public static boolean sendHeathInfo(HeathMonitor heathMonitor, boolean isDown) {
        if (StaticKeys.mailSet == null) {
            return false;
        }
        MailSet mailSet = StaticKeys.mailSet;
        if (StaticKeys.NO_SEND_WARN.equals(mailConfig.getAllWarnMail()) || StaticKeys.NO_SEND_WARN.equals(mailConfig.getHeathWarnMail())) {
            return false;
        }
        String key = heathMonitor.getId();
        if (isDown) {
            if (!StringUtils.isEmpty(WarnPools.MEM_WARN_MAP.get(key))) {
                return false;
            }
            try {
                String title = "Service interface detection alarm:" + heathMonitor.getAppName();
                String commContent = "Service interface:" + heathMonitor.getHeathUrl() + ", The response status code is" + heathMonitor.getHeathStatus() + "，There may be abnormalities, please see";
                //send email email
                sendMail(mailSet.getToMail(), title, commContent);
                //The mark has been sent to the alarm information
                WarnPools.MEM_WARN_MAP.put(key, "1");
                //Record sending information
                logInfoService.save(title, commContent, StaticKeys.LOG_ERROR);
            } catch (Exception e) {
                logger.error("Sending service health test alarm failed:", e);
                logInfoService.save("Send a service health detection alarm email error", e.toString(), StaticKeys.LOG_ERROR);
            }
        } else {
            WarnPools.MEM_WARN_MAP.remove(key);
            try {
                String title = "The service interface resumes normal notice:" + heathMonitor.getAppName();
                String commContent = "The service interface resumes normal notice:" + heathMonitor.getHeathUrl() + ", The response status code is" + heathMonitor.getHeathStatus() + "";
                //send email
                sendMail(mailSet.getToMail(), title, commContent);
                //Record sending information
                logInfoService.save(title, commContent, StaticKeys.LOG_ERROR);
            } catch (Exception e) {
                logger.error("Send the service interface to restore the normal notification email failure:", e);
                logInfoService.save("Send the service interface to restore normal notification email errors", e.toString(), StaticKeys.LOG_ERROR);
            }
        }
        return false;
    }

    /**
     * The host sends a warning email offline
     *
     * @param systemInfo Host information
     * @param isDown     Whether it is offline alarm, TRUE offline alert, FALSE online recovery
     * @return
     */
    public static boolean sendHostDown(SystemInfo systemInfo, boolean isDown) {
        if (StaticKeys.mailSet == null) {
            return false;
        }
        MailSet mailSet = StaticKeys.mailSet;
        if (StaticKeys.NO_SEND_WARN.equals(mailConfig.getAllWarnMail()) || StaticKeys.NO_SEND_WARN.equals(mailConfig.getHostDownWarnMail())) {
            return false;
        }
        String key = systemInfo.getId();
        if (isDown) {
            if (!StringUtils.isEmpty(WarnPools.MEM_WARN_MAP.get(key))) {
                return false;
            }
            try {
                String title = "Host to go offline to alarm:" + systemInfo.getHostname();
                String commContent = "The host has not been reported for more than 10 minutes, and it may have been offline:" + systemInfo.getHostname() + ",Remark:" + systemInfo.getRemark()
                        + "EssenceIf you no longer monitor the host delete in the list, it will no longer receive the host alarm email";
                //send email
                sendMail(mailSet.getToMail(), title, commContent);
                //The mark has been sent to the alarm information
                WarnPools.MEM_WARN_MAP.put(key, "1");
                //Record sending information
                logInfoService.save(title, commContent, StaticKeys.LOG_ERROR);
            } catch (Exception e) {
                logger.error("Sending the host offline alarm mail failed:", e);
                logInfoService.save("Send the host offline alarm email error", e.toString(), StaticKeys.LOG_ERROR);
            }
        } else {
            WarnPools.MEM_WARN_MAP.remove(key);
            try {
                String title = "The host resumes online notice:" + systemInfo.getHostname();
                String commContent = "The host has been returned to the launch:" + systemInfo.getHostname() + ",Remark:" + systemInfo.getRemark()
                        + "。";
                //send email
                sendMail(mailSet.getToMail(), title, commContent);
                //Record sending information
                logInfoService.save(title, commContent, StaticKeys.LOG_ERROR);
            } catch (Exception e) {
                logger.error("Send the host to restore the online notification email failure:", e);
                logInfoService.save("Send the host to restore the online notification email error", e.toString(), StaticKeys.LOG_ERROR);
            }
        }
        return false;
    }

    /**
     * Send a warning email offline
     *
     * @param AppInfo Process information
     * @param isDown  Whether it is offline alarm, TRUE offline alert, FALSE online recovery
     * @return
     */
    public static boolean sendAppDown(AppInfo appInfo, boolean isDown) {
        if (StaticKeys.mailSet == null) {
            return false;
        }
        MailSet mailSet = StaticKeys.mailSet;
        if (StaticKeys.NO_SEND_WARN.equals(mailConfig.getAllWarnMail()) || StaticKeys.NO_SEND_WARN.equals(mailConfig.getAppDownWarnMail())) {
            return false;
        }
        String key = appInfo.getId();
        if (isDown) {
            if (!StringUtils.isEmpty(WarnPools.MEM_WARN_MAP.get(key))) {
                return false;
            }
            try {
                String title = "Process offline Alarm:" + appInfo.getHostname() + "，" + appInfo.getAppName();
                String commContent = "The process has not been reported for more than 10 minutes, and it may have been offline:" + appInfo.getHostname() + "，" + appInfo.getAppName()
                        + "EssenceIf you no longer monitor the process delete in the list, it will no longer receive the process alarm mail";
                //send email
                sendMail(mailSet.getToMail(), title, commContent);
                //The mark has been sent to the alarm information
                WarnPools.MEM_WARN_MAP.put(key, "1");
                //Record sending information
                logInfoService.save(title, commContent, StaticKeys.LOG_ERROR);
            } catch (Exception e) {
                logger.error("Sending process offline Alarm mail failed:", e);
                logInfoService.save("Send process offline Alarm error", e.toString(), StaticKeys.LOG_ERROR);
            }
        } else {
            WarnPools.MEM_WARN_MAP.remove(key);
            try {
                String title = "Procedure restore online notification:" + appInfo.getHostname() + "，" + appInfo.getAppName();
                String commContent = "Procedure restore online notification:" + appInfo.getHostname() + "，" + appInfo.getAppName();
                //send email
                sendMail(mailSet.getToMail(), title, commContent);
                //Record sending information
                logInfoService.save(title, commContent, StaticKeys.LOG_ERROR);
            } catch (Exception e) {
                logger.error("The sending process will restore the online notification email failure:", e);
                logInfoService.save("Send process to restore online notification errors", e.toString(), StaticKeys.LOG_ERROR);
            }
        }
        return false;
    }

    public static String sendMail(String mails, String mailTitle, String mailContent) {
        try {
            HtmlEmail email = new HtmlEmail();
            email.setHostName(StaticKeys.mailSet.getSmtpHost());
            email.setSmtpPort(Integer.valueOf(StaticKeys.mailSet.getSmtpPort()));
            if ("1".equals(StaticKeys.mailSet.getSmtpSSL())) {
                email.setSSL(true);
            }
            email.setAuthenticator(new DefaultAuthenticator(StaticKeys.mailSet.getFromMailName(), StaticKeys.mailSet.getFromPwd()));
            email.setFrom(StaticKeys.mailSet.getFromMailName());//Letter
            email.setSubject("[WGCLOUD] " + mailTitle);//title
            email.setCharset("UTF-8");//Encoding format
            email.setHtmlMsg(mailContent + content_suffix);//content
            email.addTo(mails.split(";"));
            email.setSentDate(new Date());
            email.send();//send
            return "success";
        } catch (Exception e) {
            logger.error("Send mail error:", e);
            logInfoService.save("Send email error", e.toString(), StaticKeys.LOG_ERROR);
            return "error";
        }
    }


}
