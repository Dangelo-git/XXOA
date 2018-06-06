package com.dangelo.xxoa.uitl;

import com.blankj.utilcode.utils.Utils;
import com.jiongbull.jlog.JLog;
import com.jiongbull.jlog.constant.LogLevel;
import com.jiongbull.jlog.constant.LogSegment;
import com.jiongbull.jlog.constant.ZoneOffset;


import java.util.ArrayList;
import java.util.List;

//import com.jiongbull.jlog.JLog;
//import com.jiongbull.jlog.constant.LogLevel;
//import com.jiongbull.jlog.constant.LogSegment;
//import com.jiongbull.jlog.constant.ZoneOffset;

/**
 * Created by dangelo on 17/4/14.
 */
public class JlogUtils {
    /**
     * 初始化Jlog模块，Jlog调用方法不变
     */
    public static void initJlog() {
        FileService.init();
        List<LogLevel> logLevels = new ArrayList<>();
        logLevels.add(LogLevel.DEBUG);
        logLevels.add(LogLevel.WARN);
        logLevels.add(LogLevel.ERROR);
        logLevels.add(LogLevel.INFO);
        JLog.init(Utils.getContext())
                .setDebug(true)
                .writeToFile(true)
                .setLogLevelsForFile(logLevels)
                .setLogDir(FileService.getSelfFilePath(Utils.getContext()))
                .setLogSegment(LogSegment.TWENTY_FOUR_HOURS)
                .setCharset("UTF-8")
                .setTimeFormat("yyyy年MM月dd日 HH时mm分ss秒")
                .setZoneOffset(ZoneOffset.P0800);
    }
}
