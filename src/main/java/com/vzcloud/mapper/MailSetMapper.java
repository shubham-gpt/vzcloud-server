package com.vzcloud.mapper;

import org.springframework.stereotype.Repository;

import com.vzcloud.entity.MailSet;

import java.util.List;
import java.util.Map;

/**
 * @version v2.3
 * @ClassName:MailSetMapper.java
 * @author: http://www.wgstart.com
 * @date: November 16, 2019
 * @Description: View Disk IO usage
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
@Repository
public interface MailSetMapper {


    public List<MailSet> selectAllByParams(Map<String, Object> map) throws Exception;

    public void save(MailSet MailSet) throws Exception;

    public int deleteById(String[] id) throws Exception;

    public int updateById(MailSet MailSet) throws Exception;


}
