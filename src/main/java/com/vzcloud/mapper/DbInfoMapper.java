package com.vzcloud.mapper;

import org.springframework.stereotype.Repository;

import com.vzcloud.entity.DbInfo;

import java.util.List;
import java.util.Map;

/**
 * @version v2.3
 * @ClassName:DbInfoMapper.java
 * @author: http://www.wgstart.com
 * @date: November 16, 2019
 * @Description: DbInfoDao.java
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
@Repository
public interface DbInfoMapper {

    public List<DbInfo> selectAllByParams(Map<String, Object> map) throws Exception;

    public List<DbInfo> selectByParams(Map<String, Object> params) throws Exception;

    public DbInfo selectById(String id) throws Exception;

    public void save(DbInfo DbInfo) throws Exception;

    public int deleteById(String[] id) throws Exception;

    public int countByParams(Map<String, Object> params) throws Exception;

    public int updateById(DbInfo DbInfo) throws Exception;
}
