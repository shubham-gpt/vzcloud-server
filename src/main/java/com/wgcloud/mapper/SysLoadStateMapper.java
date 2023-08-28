package com.wgcloud.mapper;

import org.springframework.stereotype.Repository;

import com.wgcloud.entity.SysLoadState;

import java.util.List;
import java.util.Map;

/**
 * @version v2.3
 * @ClassName:SysLoadStateDao.java
 * @author: http://www.wgstart.com
 * @date: November 16, 2019
 * @Description: Check uptime View the system load status
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
@Repository
public interface SysLoadStateMapper {


    public List<SysLoadState> selectAllByParams(Map<String, Object> map) throws Exception;


    public List<SysLoadState> selectByParams(Map<String, Object> params) throws Exception;


    public SysLoadState selectById(String id) throws Exception;

    public void save(SysLoadState SysLoadState) throws Exception;


    public void insertList(List<SysLoadState> recordList) throws Exception;

    public int deleteByAccountAndDate(Map<String, Object> map) throws Exception;

    public int deleteById(String[] id) throws Exception;


}
