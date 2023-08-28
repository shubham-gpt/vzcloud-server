package com.wgcloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @version v2.3
 * @ClassName:CommonCotroller.java
 * @author: http://www.wgstart.com
 * @date: November 16, 2019
 * @Description: CommonCotroller.java
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
@Controller
@RequestMapping(value = "/common/error")
public class CommonCotroller {


    /**
     * Turn to page 404
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("404")
    public String to404(Model model, HttpServletRequest request) {
        return "error/404";
    }

    /**
     * Turn to 500 pages
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("500")
    public String to500(Model model, HttpServletRequest request) {
        return "error/500";
    }

}