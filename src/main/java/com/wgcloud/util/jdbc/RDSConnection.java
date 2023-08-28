package com.wgcloud.util.jdbc;

import org.springframework.stereotype.Component;

/**
 * @version v2.3
 * @ClassName:RDSConnection.java
 * @author: http://www.vzstart.com
 * @date: November 16, 2019
 * @Description: RDSConnection.java
 * @Copyright: 2017-2022 vzcloud. All rights reserved.
 */
@Component
public class RDSConnection {
    public static final String driver_oracle = "oracle.jdbc.driver.OracleDriver";
    public static final String url_oracle = "jdbc:oracle:thin:@{ip}:{port}:{dbname}";

    public static final String driver_postgresql = "org.postgresql.Driver";
    public static final String url_postgresql = "jdbc:postgresql://{ip}:{port}/{dbname}";

    public static final String driver_mysql = "com.mysql.jdbc.Driver";
    public static final String url_mysql = "jdbc:mysql://{ip}:{port}/{dbname}?characterEncoding=utf-8&characterSetResults=utf8&autoReconnect=true&useSSL=false";

    public static final String driver_sqlserver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static final String url_sqlserver = "jdbc:sqlserver://{ip}:{port};DatabaseName={dbname}";

    public static final String driver_db2 = "com.ibm.db2.jdbc.app.DB2Driver";
    public static final String url_db2 = "jdbc:db2://{ip}:{port}/{dbname}";

    public static final String SQL_INKEYS = "execute,update,delete,insert,create,drop,alter,rename,where,modify";//SQL injection keyword
    public static final String MYSQL_VERSION = "select version()";//View mysql version
    public static final String ORACLE_VERSION = "select * from v$version";//View Oracle version
    public static final String SQLSERVER_VERSION = "SELECT @@VERSION";//View SQLSERVER version
    public static final String DB2_VERSION = "SELECT SERVICE_LEVEL FROM SYSIBMADM.ENV_INST_INFO";//View DB2 version
    public static final String PROCESS_LIST = "SHOW FULL PROCESSLIST";//Check the list of processes currently in the unreasonable state;
    public static final String GLOBAL_VAR = "SHOW GLOBAL VARIABLES";//View mysql settings
    public static final String MAX_CONN = "show variables like '%max_connections%'";//The maximum number of connections currently set by the database
    public static final String MAX_USED_CONN = "show global status like 'Max_used_connections'";//The maximum number of connections of server response
    public static final String query_table_count = "SELECT COUNT(*) FROM {tableName} WHERE 1=1 ";
    public static final String query_table_count_pg = "SELECT COUNT(*) FROM \"{tableName}\" WHERE 1=1 ";
}