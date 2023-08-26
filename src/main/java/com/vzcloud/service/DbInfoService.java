package com.vzcloud.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vzcloud.entity.DbInfo;
import com.vzcloud.mapper.DbInfoMapper;
import com.vzcloud.util.DateUtil;
import com.vzcloud.util.UUIDUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @version v2.3
 * @ClassName:DbInfoService.java
 * @author: http://www.wgstart.com
 * @date: November 16, 2019
 * @Description: DbInfoService.java
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
@Service
public class DbInfoService {

    public PageInfo selectByParams(Map<String, Object> params, int currPage, int pageSize) throws Exception {
        PageHelper.startPage(currPage, pageSize);
        List<DbInfo> list = dbInfoMapper.selectByParams(params);
        PageInfo<DbInfo> pageInfo = new PageInfo<DbInfo>(list);
        return pageInfo;
    }

    public void save(DbInfo DbInfo) throws Exception {
        DbInfo.setId(UUIDUtil.getUUID());
        DbInfo.setCreateTime(DateUtil.getNowTime());
        DbInfo.setDbState("1");
        dbInfoMapper.save(DbInfo);
    }

    public int countByParams(Map<String, Object> params) throws Exception {
        return dbInfoMapper.countByParams(params);
    }

    @Transactional
    public int deleteById(String[] id) throws Exception {
        return dbInfoMapper.deleteById(id);
    }

    public int updateById(DbInfo DbInfo)
            throws Exception {
        return dbInfoMapper.updateById(DbInfo);
    }

    public DbInfo selectById(String id) throws Exception {
        return dbInfoMapper.selectById(id);
    }

    public List<DbInfo> selectAllByParams(Map<String, Object> params) throws Exception {
        return dbInfoMapper.selectAllByParams(params);
    }


    @Autowired
    private DbInfoMapper dbInfoMapper;


}
