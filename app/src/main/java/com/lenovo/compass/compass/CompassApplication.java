package com.lenovo.compass.compass;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;

import com.karumi.dexter.Dexter;
import com.lenovo.compass.compass.image.ImageLoader;
import com.lenovo.compass.compass.log.LogUtil;
import com.lenovo.compass.compass.utils.MediaUtil;
import com.lenovo.compass.compass.utils.ProcessUtil;
import com.lenovo.compass.compass.utils.Utils;

public class CompassApplication extends Application {
    private static final String TAG = "CompassApplication";
    private static CompassApplication sInstance;
    private static final String CORE_PROCESS_NAME = "com.lenovo.compass.compass";
    public static boolean DEBUG = true;
    public static Context applicationContext;
    public static int screenWidth;
    public static int screenHeight;
    public static int statusBarHeight;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        applicationContext = getApplicationContext();
        init();
    }

    private void init() {
        String currentProcessName = ProcessUtil.getCurProcessName(getApplicationContext());
        String applicationPackageName = getApplicationInfo().packageName;
        Log.d(TAG, "currentProcessName = " + currentProcessName + ", applicationPackageName = " + applicationPackageName);
        if (!TextUtils.isEmpty(currentProcessName) && currentProcessName.equals(applicationPackageName)) {
            Dexter.initialize(this);
            ImageLoader.init(this);
            MediaUtil.init();
            LogUtil.init();
            startService();
           // OrmPersistManager.getInstance();
        }
    }

    private void startService() {
       /* Intent intent = new Intent();
        intent.setClass(this, WorkerService.class);
        startService(intent);*/
    }

    public static int getScreenHeight(Context context) {
        if (CompassApplication.screenHeight == 0) {
            initScreenSize(context);
        }
        return CompassApplication.screenHeight;
    }

    public static int getScreenWidth(Context context) {
        if (CompassApplication.screenWidth == 0) {
            initScreenSize(context);
        }
        return CompassApplication.screenWidth;
    }

    public static int getStatusBarHeight(Context context) {
        if (CompassApplication.statusBarHeight == 0) {
            initScreenSize(context);
        }
        return CompassApplication.statusBarHeight;
    }

    private static void initScreenSize(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();// ÆÁÄ»¸ß¶È
        int width = wm.getDefaultDisplay().getWidth();// ÆÁÄ»¿í¶È;
        if (width > height) {
            CompassApplication.screenHeight = width;
            CompassApplication.screenWidth = height;
        } else {
            CompassApplication.screenHeight = height;
            CompassApplication.screenWidth = width;
        }

        if (CompassApplication.statusBarHeight <= 0) {
            CompassApplication.statusBarHeight = Utils.getStatusBarHeight(context);
        }

    }

    public static Context getInstance() {
        return sInstance;
    }


}
