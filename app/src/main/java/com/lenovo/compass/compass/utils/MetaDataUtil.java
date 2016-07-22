package com.lenovo.compass.compass.utils;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.ksyun.android.ddlive.KsyunApplication;

public class MetaDataUtil {
    private static final String KEY_WECHAT_ID = "WECHAT_APP_KEY";

    public static String getWeChatId() {
        return getMetaDataByKey(KEY_WECHAT_ID);
    }

    public static String getMetaDataByKey(String metaDataKey) {
        ApplicationInfo appInfo = null;
        try {
            appInfo = KsyunApplication.getInstance().getPackageManager()
                    .getApplicationInfo(KsyunApplication.getInstance().getPackageName(),
                            PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appInfo.metaData.getString(metaDataKey);
    }
}
