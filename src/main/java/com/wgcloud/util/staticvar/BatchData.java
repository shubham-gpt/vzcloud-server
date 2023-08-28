package com.wgcloud.util.staticvar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.wgcloud.entity.*;


/**
 * @version v2.3
 * @ClassName:BatchData.java
 * @author: http://www.vzstart.com
 * @date: November 16, 2019
 * @Description: Static tools for temporary storage monitoring data
 * @Copyright: 2017-2022 vzcloud. All rights reserved.
 */
public class BatchData {

    //system message
    public static List<SystemInfo> SYSTEM_INFO_LIST = Collections.synchronizedList(new ArrayList<SystemInfo>());


    //Process information
    public static List<AppInfo> APP_INFO_LIST = Collections.synchronizedList(new ArrayList<AppInfo>());


    //Process status
    public static List<AppState> APP_STATE_LIST = Collections.synchronizedList(new ArrayList<AppState>());

    //CPU monitoring
    public static List<CpuState> CPU_STATE_LIST = Collections.synchronizedList(new ArrayList<CpuState>());

    //Memory monitoring
    public static List<MemState> MEM_STATE_LIST = Collections.synchronizedList(new ArrayList<MemState>());

    //Network throughput monitoring, it's useless for the time being
    public static List<NetIoState> NETIO_STATE_LIST = Collections.synchronizedList(new ArrayList<NetIoState>());

    //Disk size
    public static List<DeskState> DESK_STATE_LIST = Collections.synchronizedList(new ArrayList<DeskState>());


    //System load monitoring
    public static List<SysLoadState> SYSLOAD_STATE_LIST = Collections.synchronizedList(new ArrayList<SysLoadState>());

    //TCP monitoring, it's useless for the time being
    public static List<TcpState> TCP_STATE_LIST = Collections.synchronizedList(new ArrayList<TcpState>());

    //Log information
    public static List<LogInfo> LOG_INFO_LIST = Collections.synchronizedList(new ArrayList<LogInfo>());


    // Invasive detection information
    public static List<IntrusionInfo> INTRUSION_INFO_LIST = Collections.synchronizedList(new ArrayList<IntrusionInfo>());


}
