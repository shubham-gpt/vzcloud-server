package com.wgcloud.mapper;

import org.springframework.stereotype.Repository;

import com.wgcloud.entity.TcpState;

import java.util.List;
import java.util.Map;

/**
 * @version v2.3
 * @ClassName:TcpStateDao.java
 * @author: http://www.wgstart.com
 * @date: November 16, 2019
 * @Description: View TCP connection status
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
@Repository
public interface TcpStateMapper {


    public List<TcpState> selectAllByParams(Map<String, Object> map) throws Exception;


    public List<TcpState> selectByParams(Map<String, Object> params) throws Exception;


    public TcpState selectById(String id) throws Exception;

    public void save(TcpState TcpState) throws Exception;


    public void insertList(List<TcpState> recordList) throws Exception;

    public int deleteByAccountAndDate(Map<String, Object> map) throws Exception;

    public int deleteById(String[] id) throws Exception;


}
