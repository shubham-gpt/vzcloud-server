package com.vzcloud.mapper;

import org.springframework.stereotype.Repository;

import com.vzcloud.entity.AppState;

import java.util.List;
import java.util.Map;

/**
 * @version v2.3
 * @ClassName:AppStateDao.java
 * @author: http://www.wgstart.com
 * @date: November 16, 2019
 * @Description: AppStateDao.java
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
@Repository
public interface AppStateMapper {

    public List<AppState> selectAllByParams(Map<String, Object> map) throws Exception;

    public List<AppState> selectByParams(Map<String, Object> params) throws Exception;

    public AppState selectById(String id) throws Exception;

    public int selectByParamsCount(Map<String, Object> map);

    public void save(AppState AppState) throws Exception;

    public void insertList(List<AppState> recordList) throws Exception;

    public int deleteByAppInfoId(String appInfoId) throws Exception;

    public int deleteByDate(Map<String, Object> map) throws Exception;

    public int deleteById(String[] id) throws Exception;


}
