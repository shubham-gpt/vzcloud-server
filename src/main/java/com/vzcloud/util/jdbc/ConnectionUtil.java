package com.vzcloud.util.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import com.vzcloud.entity.DbInfo;
import com.vzcloud.service.DbInfoService;
import com.vzcloud.service.LogInfoService;
import com.vzcloud.util.staticvar.StaticKeys;

import javax.annotation.Resource;

/**
 * @version v2.3
 * @ClassName:ConnectionUtil.java
 * @author: http://www.vzstart.com
 * @date: November 16, 2019
 * @Description: ConnectionUtil.java
 * @Copyright: 2017-2022 vzcloud. All rights reserved.
 */
@Component
public class ConnectionUtil {
    private static final Logger logger = LoggerFactory.getLogger(ConnectionUtil.class);
    @Resource
    private LogInfoService logInfoService;
    @Resource
    private DbInfoService dbInfoService;

    public JdbcTemplate getJdbcTemplate(DbInfo dbInfo) throws Exception {
        JdbcTemplate jdbcTemplate = null;
        String driver = "";
        String url = "";
        if ("mysql".equals(dbInfo.getDbType())) {
            driver = RDSConnection.driver_mysql;
            url = RDSConnection.url_mysql;
        } else if ("postgresql".equals(dbInfo.getDbType())) {
            driver = RDSConnection.driver_postgresql;
            url = RDSConnection.url_postgresql;
        } else if ("sqlserver".equals(dbInfo.getDbType())) {
            driver = RDSConnection.driver_sqlserver;
            url = RDSConnection.url_sqlserver;
        } else if ("db2".equals(dbInfo.getDbType())) {
            driver = RDSConnection.driver_db2;
            url = RDSConnection.url_db2;
        } else {
            driver = RDSConnection.driver_oracle;
            url = RDSConnection.url_oracle;
        }
        url = url.replace("{ip}", dbInfo.getIp()).replace("{port}", dbInfo.getPort()).replace("{dbname}", dbInfo.getDbName());
        try {
            //Create a connection pool
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName(driver);
            dataSource.setUrl(url);
            dataSource.setUsername(dbInfo.getUser());
            dataSource.setPassword(dbInfo.getPasswd());
            jdbcTemplate = new JdbcTemplate(dataSource);
            if ("mysql".equals(dbInfo.getDbType())) {
                jdbcTemplate.queryForRowSet(RDSConnection.MYSQL_VERSION);
            } else if ("postgresql".equals(dbInfo.getDbType())) {
                jdbcTemplate.queryForRowSet(RDSConnection.MYSQL_VERSION);
            } else if ("sqlserver".equals(dbInfo.getDbType())) {
                jdbcTemplate.queryForRowSet(RDSConnection.SQLSERVER_VERSION);
            } else if ("db2".equals(dbInfo.getDbType())) {
                jdbcTemplate.queryForRowSet(RDSConnection.DB2_VERSION);
            } else {
                jdbcTemplate.queryForRowSet(RDSConnection.ORACLE_VERSION);
            }
            dbInfo.setDbState("1");
            dbInfoService.updateById(dbInfo);
            return jdbcTemplate;
        } catch (Exception e) {
            jdbcTemplate = null;
            logger.error("Connect the database error", e);
            logInfoService.save("Connect the database error:" + dbInfo.getAliasName(), "IP：" + dbInfo.getIp() + ",port:" + dbInfo.getPort() + ", Database alias:"
                    + dbInfo.getAliasName() + ", Error message:" + e.toString(), StaticKeys.LOG_ERROR);
            dbInfo.setDbState("2");
            dbInfoService.updateById(dbInfo);
        }
        return null;
    }

    public long queryTableCount(DbInfo dbInfo, String sql) {
        try {
            JdbcTemplate jdbcTemplate = getJdbcTemplate(dbInfo);
            if (null == jdbcTemplate) {
                return 0;
            }
            return jdbcTemplate.queryForObject(sql, Long.class);
        } catch (Exception e) {
            logger.error("Statistical data table error:", e);
            logInfoService.save("Statistical data table error:" + dbInfo.getAliasName(), "IP：" + dbInfo.getIp() + ",port:" + dbInfo.getPort() + ", Database alias:"
                    + dbInfo.getAliasName() + ", Error message:" + e.toString(), StaticKeys.LOG_ERROR);
            return 0;
        }
    }

}