package com.wgcloud.service;

import org.springframework.stereotype.Service;

import com.wgcloud.util.DateUtil;
import com.wgcloud.util.staticvar.StaticKeys;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @version v2.3
 * @ClassName:DashboardService.java
 * @author: http://www.wgstart.com
 * @date: November 16, 2019
 * @Description: Main panel information management
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
@Service
public class DashboardService {


    /**
     * Get the date of date from today, the countdown to the countdown
     *
     * @param days
     * @return
     */
    public List<String> getDateList() {
        int days = 7;
        List<String> dateList = new ArrayList<String>();
        String nowTime = DateUtil.getCurrentDateTime();
        String sevenDayBefore = DateUtil.getDateBefore(nowTime, days);
        for (int i = 0; i < days; i++) {
            sevenDayBefore = DateUtil.getDateBefore(nowTime, i);
            dateList.add(sevenDayBefore.substring(0, 10));
        }
        return dateList;
    }

    /**
     * View detailed information monitoring, the assembly date query condition
     *
     * @param params
     * @param date
     */
    public void setDateParam(String date, Map<String, Object> params) {
        params.put(StaticKeys.SEARCH_START_TIME, date + " 00:00:00");
        params.put(StaticKeys.SEARCH_END_TIME, date + " 23:59:59");
    }

}
