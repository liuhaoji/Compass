package com.lenovo.compass.compass.utils;

import android.os.Environment;

import com.ksyun.android.ddlive.log.LogUtil;

import java.io.File;

public class MediaUtil {
    public static final String TAG = "MediaUtil";

    public final static void init() {
        String path = getAppDir();
        FileUtil.mkdir(path);
        path = getLogDir();
        FileUtil.mkdir(path);
        path = getGiftDir();
        FileUtil.mkdir(path);
    }

    public static String getAppDir() {
        File fileDir = Environment.getExternalStorageDirectory();
        if (fileDir == null) {
            LogUtil.e(TAG, "External Storage ERROR!!!!!!");
            return null;
        }
        if (!fileDir.exists()) {
            LogUtil.e(TAG, "media not exists!");
            return null;
        }
        String appDir = fileDir.getAbsolutePath() + File.separator + ".ddlive";
        LogUtil.e(TAG, "create file" + appDir);
        return appDir;
    }


    public static String getLogDir() {
        String appDir = getAppDir();
        String logDir = null;
        if (appDir != null) {
            logDir = appDir + File.separator + "log";
        }
        return logDir;
    }

    public static String getGiftDir() {
        String appDir = getAppDir();
        String logDir = null;
        if (appDir != null) {
            logDir = appDir + File.separator + "gift";
        }
        return logDir;
    }
}
