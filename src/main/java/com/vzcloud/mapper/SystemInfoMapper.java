package com.vzcloud.mapper;

import org.springframework.stereotype.Repository;

import com.vzcloud.entity.SystemInfo;

import java.util.List;
import java.util.Map;

/**
 * @version v2.3
 * @ClassName:SystemInfoDao.java
 * @author: http://www.wgstart.com
 * @date: November 16, 2019
 * @Description: View system information
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
@Repository
public interface SystemInfoMapper {


    public List<SystemInfo> selectAllByParams(Map<String, Object> map) throws Exception;

    public List<SystemInfo> selectByAccountId(String accountId) throws Exception;

    public List<SystemInfo> selectByParams(Map<String, Object> params);

    public void insertList(List<SystemInfo> recordList) throws Exception;

    public void updateList(List<SystemInfo> recordList) throws Exception;

    public SystemInfo selectById(String id) throws Exception;

    public int updateById(SystemInfo SystemInfo) throws Exception;

    public int countByParams(Map<String, Object> params) throws Exception;

    public void save(SystemInfo SystemInfo) throws Exception;

    public int deleteById(String[] id) throws Exception;


    public int deleteByAccountAndDate(Map<String, Object> map) throws Exception;

    public int deleteByAccHname(Map<String, Object> map) throws Exception;


}
