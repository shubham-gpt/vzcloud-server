package com.wgcloud.controller;

import cn.hutool.json.JSONObject;

import com.wgcloud.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * @version v2.3 @ClassName:AgentController.java
 * @author: http://www.wgstart.com
 * @date:November 16, 2019 @Description: AgentController.java @Copyright: 2017-2022 wgcloud. All
 *     rights reserved.
 */
@Controller
@RequestMapping("/agent")
public class AgentController {

  @Autowired private AgentService agentService;

  @ResponseBody
  @RequestMapping("/minTask")
  public JSONObject minTask(@RequestBody String paramBean) {
    return agentService.handle(paramBean);
  }
}
