package com.vzcloud.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vzcloud.entity.AppState;
import com.vzcloud.mapper.AppStateMapper;
import com.vzcloud.util.DateUtil;
import com.vzcloud.util.UUIDUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @version v2.3
 * @ClassName:AppStateService.java
 * @author: http://www.wgstart.com
 * @date: November 16, 2019
 * @Description: AppStateService.java
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
@Service
public class AppStateService {

    public PageInfo selectByParams(Map<String, Object> params, int currPage, int pageSize) throws Exception {
        PageHelper.startPage(currPage, pageSize);
        List<AppState> list = appStateMapper.selectByParams(params);
        PageInfo<AppState> pageInfo = new PageInfo<AppState>(list);
        return pageInfo;
    }

    public void save(AppState AppState) throws Exception {
        AppState.setId(UUIDUtil.getUUID());
        AppState.setCreateTime(DateUtil.getNowTime());
        AppState.setDateStr(DateUtil.getDateTimeString(AppState.getCreateTime()));
        appStateMapper.save(AppState);
    }

    public void saveRecord(List<AppState> recordList) throws Exception {
        if (recordList.size() < 1) {
            return;
        }
        for (AppState as : recordList) {
            as.setId(UUIDUtil.getUUID());
            as.setDateStr(DateUtil.getDateTimeString(as.getCreateTime()));
        }
        appStateMapper.insertList(recordList);
    }

    public int deleteByAppInfoId(String appInfoId) throws Exception {
        return appStateMapper.deleteByAppInfoId(appInfoId);
    }


    public int deleteById(String[] id) throws Exception {
        return appStateMapper.deleteById(id);
    }

    public AppState selectById(String id) throws Exception {
        return appStateMapper.selectById(id);
    }

    public List<AppState> selectAllByParams(Map<String, Object> params) throws Exception {
        return appStateMapper.selectAllByParams(params);
    }


    @Autowired
    private AppStateMapper appStateMapper;


}
