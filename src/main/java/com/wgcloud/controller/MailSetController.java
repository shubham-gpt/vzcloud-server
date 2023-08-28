package com.wgcloud.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wgcloud.entity.MailSet;
import com.wgcloud.service.LogInfoService;
import com.wgcloud.service.MailSetService;
import com.wgcloud.util.msg.WarnMailUtil;
import com.wgcloud.util.staticvar.StaticKeys;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version v2.3
 * @ClassName:MailSetController.java
 * @author: http://www.wgstart.com
 * @date: November 16, 2019
 * @Description: MailSetController.java
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
@Controller
@RequestMapping("/mailset")
public class MailSetController {


    private static final Logger logger = LoggerFactory.getLogger(MailSetController.class);

    @Resource
    private MailSetService mailSetService;
    @Resource
    private LogInfoService logInfoService;


    /**
     * Query list according to conditions
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "list")
    public String MailSetList(MailSet MailSet, Model model, HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        try {
            List<MailSet> list = mailSetService.selectAllByParams(params);
            if (list.size() > 0) {
                model.addAttribute("mailSet", list.get(0));
            }
        } catch (Exception e) {
            logger.error("Query email settings error", e);
            logInfoService.save("Query email settings error:", e.toString(), StaticKeys.LOG_ERROR);

        }
        String msg = request.getParameter("msg");
        if (!StringUtils.isEmpty(msg)) {
            if (msg.equals("save")) {
                model.addAttribute("msg", "Saved successfully");
            } else if (msg.equals("test")) {
                String result = request.getParameter("result");
                if ("success".equals(result)) {
                    model.addAttribute("msg", "Successful test sending");
                } else {
                    model.addAttribute("msg", "Test sending failed, please check the log");
                }
            } else {
                model.addAttribute("msg", "successfully deleted");
            }
        } else {
            model.addAttribute("msg", "");
        }
        return "mail/view";
    }


    /**
     * Save the email setting information
     *
     * @param MailSet
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "save")
    public String saveMailSet(MailSet mailSet, Model model, HttpServletRequest request) {
        try {
            if (StringUtils.isEmpty(mailSet.getId())) {
                mailSetService.save(mailSet);
            } else {
                mailSetService.updateById(mailSet);
            }
            StaticKeys.mailSet = mailSet;
        } catch (Exception e) {
            logger.error("Save the email setting information error:", e);
            logInfoService.save("Email setting information error", e.toString(), StaticKeys.LOG_ERROR);
        }
        return "redirect:/mailset/list?msg=save";
    }

    @RequestMapping(value = "test")
    public String test(MailSet mailSet, Model model, HttpServletRequest request) {
        String result = "success";
        try {
            if (StringUtils.isEmpty(mailSet.getId())) {
                mailSetService.save(mailSet);
            } else {
                mailSetService.updateById(mailSet);
            }
            StaticKeys.mailSet = mailSet;
            result = WarnMailUtil.sendMail(mailSet.getToMail(), "WGCloud test email sending", "WGCloud test email sending");
        } catch (Exception e) {
            logger.error("Test email setting information error:", e);
            logInfoService.save("Test email setting information error", e.toString(), StaticKeys.LOG_ERROR);
        }
        return "redirect:/mailset/list?msg=test&result=" + result;
    }

    /**
     * Delete the alarm mail information
     *
     * @param id
     * @param model
     * @param request
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "del")
    public String delete(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String errorMsg = "Delete the alarm email setting error:";
        try {
            if (!StringUtils.isEmpty(request.getParameter("id"))) {
                mailSetService.deleteById(request.getParameter("id").split(","));
                StaticKeys.mailSet = null;
            }
        } catch (Exception e) {
            logger.error(errorMsg, e);
            logInfoService.save(errorMsg, e.toString(), StaticKeys.LOG_ERROR);
        }

        return "redirect:/mailset/list?msg=save";
    }


}
