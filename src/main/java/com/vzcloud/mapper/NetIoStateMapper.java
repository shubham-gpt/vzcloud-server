package com.vzcloud.mapper;

import org.springframework.stereotype.Repository;

import com.vzcloud.entity.NetIoState;

import java.util.List;
import java.util.Map;

/**
 * @version v2.3
 * @ClassName:NetIoStateDao.java
 * @author: http://www.wgstart.com
 * @date: November 16, 2019
 * @Description: View the throughput of network equipment
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
@Repository
public interface NetIoStateMapper {


    public List<NetIoState> selectAllByParams(Map<String, Object> map) throws Exception;


    public List<NetIoState> selectByParams(Map<String, Object> params);


    public NetIoState selectById(String id) throws Exception;


    public void save(NetIoState NetIoState) throws Exception;


    public void insertList(List<NetIoState> recordList) throws Exception;

    public int deleteByAccountAndDate(Map<String, Object> map) throws Exception;

    public int deleteById(String[] id) throws Exception;


}
