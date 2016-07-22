/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lenovo.compass.compass.utils;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.provider.Settings.Secure;
import android.text.ClipboardManager;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.TextView;

import com.ksyun.android.ddlive.FlavorConstants;
import com.ksyun.android.ddlive.KsyunApplication;
import com.ksyun.android.ddlive.R;
import com.ksyun.android.ddlive.bean.business.RoomInfo;
import com.ksyun.android.ddlive.bean.protocol.request.BeanConstants;
import com.ksyun.android.ddlive.eventbus.KsyunEventBus;
import com.ksyun.android.ddlive.ui.widget.topsnackbar.KsyunTopSnackBar;
import com.tencent.mm.sdk.openapi.IWXAPI;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;


/**
 * Class containing some static utility methods.
 */
public class Utils {
    private static long lastClickTime;
    private static HashMap<String, String> nations = new HashMap<String, String>();

    @TargetApi(11)
    public static void enableStrictMode() {
        if (Utils.hasGingerbread()) {
            StrictMode.ThreadPolicy.Builder threadPolicyBuilder = new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog();
            StrictMode.VmPolicy.Builder vmPolicyBuilder = new StrictMode.VmPolicy.Builder().detectAll().penaltyLog();

            if (Utils.hasHoneycomb()) {
                threadPolicyBuilder.penaltyFlashScreen();
            }
            StrictMode.setThreadPolicy(threadPolicyBuilder.build());
            StrictMode.setVmPolicy(vmPolicyBuilder.build());
        }
    }

    public static void sendUserRoomState(int enterOrLeft, RoomInfo roomInfo) {
        EventBus.getDefault().post(new KsyunEventBus.EventRoomEnterLeftMessage(roomInfo, enterOrLeft));
    }

    public static boolean hasFroyo() {
        // Can use static final constants like FROYO, declared in later versions
        // of the OS since they are inlined at compile time. This is guaranteed
        // behavior.
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
    }

    public static boolean hasGingerbread() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD;
    }

    public static boolean hasHoneycomb() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    }

    public static boolean hasHoneycombMR1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1;
    }

    public static boolean hasJellyBean() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

    //返回手机型号
    public static String mobileModel() {
        return Build.MODEL;
    }

    //返回系统版本号
    public static String systemVERSION() {
        return Build.VERSION.RELEASE;
    }


    public static int dp2px(Context ctx, float dpValue) {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) ctx.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        float scale = metrics.density;

        return (int) (dpValue * scale + 0.5f);

    }

    public static int px2dp(Context ctx, float pxValue) {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) ctx.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        float scale = metrics.density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static final String regUserName = "^[a-zA-Z0-9_-]{2,30}$";
    public static final String regIpAddress = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    public static boolean isSame(String str0, String str1) {
        if (str0 == str1) {
            // memory addr
            return true;
        }
        if (str0 == null || str1 == null) {
            return false;
        }
        if (str0.length() != str1.length()) {
            return false;
        }
        if (str0.hashCode() != str1.hashCode()) {
            // hashcode
            return false;
        }
        if (str0.equals(str1)) {
            return true;
        }
        return false;
    }

    public static boolean isUserName(String username) {
        if (username == null) {
            return false;
        }

        Pattern p = Pattern.compile(regUserName);
        Matcher m = p.matcher(username);
        return m.find();
    }

    public static boolean isPassword(String text) {
        if (isEmpty(text)) {
            return false;
        }

        int length = text.length();
        if (length < 6) {
            return false;
        }

        return true;
    }

    public static boolean isIp4Address(String ipAddress) {
        if (isEmpty(ipAddress)) {
            return false;
        }

        Pattern pattern = Pattern.compile(regIpAddress);
        Matcher macher = pattern.matcher(ipAddress);
        return macher.matches();
    }


    public static String encodeURL(String url) {
        if (isEmpty(url)) {
            return url;
        }

        String result = "";
        String[] temp = url.split("/");
        int length = temp.length;
        for (int index = 0; index < length; index++) {
            try {
                temp[index] = URLEncoder.encode(temp[index], "UTF-8");
                temp[index] = temp[index].replace("+", "%20");
            } catch (Exception e) {
                e.printStackTrace();
                return url;
            }
            result += temp[index];
            if (index < (length - 1)) {
                result += "/";
            }
        }
        return result;
    }

    public static long valueOfLong(String text) {
        long value = -1;
        try {
            value = Long.valueOf(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public static boolean eventInView(MotionEvent event, View view) {
        if (event == null || view == null) {
            return false;
        }

        int eventX = (int) event.getRawX();
        int eventY = (int) event.getRawY();

        int[] location = new int[2];
        view.getLocationOnScreen(location);

        int width = view.getWidth();
        int height = view.getHeight();
        int left = location[0];
        int top = location[1];
        int right = left + width;
        int bottom = top + height;

        Rect rect = new Rect(left, top, right, bottom);
        boolean contains = rect.contains(eventX, eventY);
        return contains;
    }

    public static Point getViewMiddlePoint(View view) {
        if (view == null) {
            return new Point();
        }

        int[] location = new int[2];
        view.getLocationOnScreen(location);
        return new Point(location[0] + view.getWidth() / 2, location[1] + view.getHeight() / 2);
    }

    public static int getDistance(MotionEvent event1, MotionEvent event2) {
        float x = event1.getX() - event2.getX();
        float y = event1.getY() - event2.getY();
        return (int) Math.sqrt(x * x + y * y);
    }

    public static boolean isEmpty(CharSequence text) {
        if (text == null || text.length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean existsEmpty(String... text) {
        if (text == null || text.length == 0) {
            return true;
        } else {
            for (String str : text) {
                if (isEmpty(str)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static int sizeOf(Collection<?> collection) {
        if (collection == null || collection.isEmpty()) {
            return 0;
        } else {
            return collection.size();
        }
    }

    public static boolean isEmpty(Collection<?> collection) {
        if (collection == null || collection.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEmpty(String[] strings) {
        if (strings == null || strings.length == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 1200) {
            return true;
        }
        lastClickTime = time;
        return false;
    }


    /**
     * 2014-12-16T16:42:00.344+08:00
     */
    @SuppressLint("SimpleDateFormat")
    public static String utcToLocal(String utcDate) {
        try {
            String fromFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";//
            String toFormat = "MM-dd HH:mm:ss";
            SimpleDateFormat sdf = new SimpleDateFormat(fromFormat);
            Date date = sdf.parse(utcDate);
            sdf.applyPattern(toFormat);
            return sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            // return e.getMessage() + "; " + utcDate
            // + "---------------------------------------";
        }
        return utcDate;
    }


    /**
     * get physic size of the device
     */
    public static float physicSize() {
        try {
            Resources resources = KsyunApplication.getInstance().getResources();
            DisplayMetrics dm = resources.getDisplayMetrics();
            double x = Math.pow(dm.widthPixels / dm.xdpi, 2);
            double y = Math.pow(dm.heightPixels / dm.ydpi, 2);
            double screenInches = Math.sqrt(x + y);
            return (float) screenInches;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    public final static int getDip(int d) {
        try {
            Resources resources = KsyunApplication.getInstance().getResources();
            final float density = resources.getDisplayMetrics().density;
            return (int) (d * density);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }

    public final static String getMimeType2(String url) {
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        return mimeType;
    }

    public final boolean openLocalFile(String localPath) {
        try {
            if (isEmpty(localPath)) {
                return false;
            }
            File file = new File(localPath);
            if (file.isDirectory() || !file.exists()) {
                return false;
            }
            String mime = FileUtil.getMimeType(file.getName());
            if (Utils.isEmpty(mime)) {
                return false;
            }
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.setDataAndType(Uri.fromFile(file), mime);
            KsyunApplication.getInstance().startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public final static void pressKey(int keyCode) {
        try {
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("input keyevent " + keyCode);// KeyEvent.KEYCODE_BACK
            // Instrumentation inst = new Instrumentation();
            // inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public final static boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    public final static int measureTextWidth(TextView view, String text) {
        if (view == null || isEmpty(text)) {
            return 0;
        }
        TextPaint paint = view.getPaint();
        int width = (int) paint.measureText(text);
        return width;
    }

    public static String byte2hex(byte[] bytes) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < bytes.length; n++) {
            stmp = (Integer.toHexString(bytes[n] & 0XFF));
            if (stmp.length() == 1) hs += "0" + stmp;
            else hs += stmp;
        }
        return hs.toLowerCase(Locale.ENGLISH);
    }

    public static final String toMaxLength(String message, int max) {
        if (Utils.isEmpty(message)) {
            return null;
        }
        int length = message.length();
        if (length > max) {
            return message.substring(0, max);
        }
        return message;
    }

    /**
     * @param inStr
     * @return
     */
    public static String MD5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];

        byte[] md5Bytes = md5.digest(byteArray);

        StringBuffer hexValue = new StringBuffer();

        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }

        return hexValue.toString();
    }


    public static final String getAndroidId() {
        String m_szAndroidID = Secure.getString(KsyunApplication.getInstance().getContentResolver(), Secure.ANDROID_ID);
        return m_szAndroidID;
    }

    public static boolean isAppInstalled(Context context, String packageName) {
        boolean flag = false;
        try {
            if (isEmpty(packageName)) return flag;
            final PackageManager packageManager = context.getPackageManager();
            List<PackageInfo> mPackageInfo = packageManager.getInstalledPackages(PackageManager.PERMISSION_GRANTED);
            if (mPackageInfo != null) {
                String tempName = null;
                for (PackageInfo packageInfo : mPackageInfo) {
                    tempName = packageInfo.packageName;
                    if (tempName != null && tempName.equals(packageName)) {
                        flag = true;
                        break;
                    }
                }
            }
            return flag;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }


    @SuppressWarnings("deprecation")
    public static void copyToClipboard(Context context, String text) {
        Context appContext = context.getApplicationContext();
        ClipboardManager clipboardManager = (ClipboardManager) appContext.getSystemService(Context.CLIPBOARD_SERVICE);
        clipboardManager.setText(text);
    }

    public static void sendViaSMS(Context context, String text) {
        try {
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"));
            intent.putExtra("sms_body", text);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            sendViaEmail(context, "", text);
        }
        // if (isSimAvailable()) {
        // } else {
        // YToast.showToast(R.string.simunavailable);
        // }
    }

    public static void sendViaSMS(Context context, String text, String sendTo) {
        try {
            if (sendTo == null) {
                sendTo = "";
            }
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + sendTo));
            intent.putExtra("sms_body", text);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            sendViaEmail(context, "", text);
        }
        // if (isSimAvailable()) {
        // } else {
        // YToast.showToast(R.string.simunavailable);
        // }
    }


    public static void sendViaEmail(Context context, String subject, String text) {
        try {
            Uri uri = Uri.parse("mailto:");
            Intent intent = new Intent(Intent.ACTION_SEND, uri);
            // intent.setType("text/plain");
            // intent.setType("plain/text");
            intent.setType("message/rfc822");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{""});
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, text);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(Intent.createChooser(intent, subject));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param dest
     * @param format "#.#"
     * @return
     */
    public static final String formatNum(double dest, String format) {
        if (isEmpty(format)) {
            return dest + "";
        }
        DecimalFormat formatter = new DecimalFormat(format);
        return formatter.format(dest);
    }

    public static boolean isAppHidden(Context context) {
        if (context == null) {
            return true;
        }
        try {
            ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            List<RunningAppProcessInfo> appProcesses = am.getRunningAppProcesses();
            if (appProcesses == null) {
                return false;
            }
            for (RunningAppProcessInfo runningAppProcessInfo : appProcesses) {
                if (runningAppProcessInfo.processName.equals("com.ksyun.android.ddlive")) {
                    int status = runningAppProcessInfo.importance;
                    if (status == RunningAppProcessInfo.IMPORTANCE_BACKGROUND) {
                        return true;
                    }
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isAppForeGround(Context context) {
        if (context == null) {
            return true;
        }
        try {
            ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            List<RunningAppProcessInfo> appProcesses = am.getRunningAppProcesses();
            if (appProcesses == null) {
                return false;
            }
            for (RunningAppProcessInfo runningAppProcessInfo : appProcesses) {
                if (runningAppProcessInfo.processName.equals(context.getApplicationInfo().packageName)) {
                    int status = runningAppProcessInfo.importance;
                    if (status == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                        return true;
                    }
                    return false;
                } else {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    private static Pattern pattern = Pattern.compile("((?:(http|https|Http|Https):\\/\\/" + "(?:(?:[a-zA-Z0-9\\$\\-\\_\\.\\+\\!\\*\\'\\(\\)\\,\\;\\?\\&\\=]|"
            + "(?:\\%[a-fA-F0-9]{2})){1,64}(?:\\:(?:[a-zA-Z0-9\\$\\-\\_\\.\\" + "+\\!\\*\\'\\(\\)\\,\\;\\?\\&\\=]|(?:\\%[a-fA-F0-9]{2})){1,25})"
            + "?\\@)?)?((?:(?:[a-zA-Z0-9][a-zA-Z0-9\\-\\_]{0,64}\\.)" + "+(?:(?:aero|arpa|asia|a[cdefgilmnoqrstuwxz])" + "|(?:biz|b[abdefghijmnorstvwyz])|(?:cat|com|"
            + "coop|c[acdfghiklmnoruvxyz])|d[ejkmoz]|(?:edu|e[cegrstu])|f[ijkmor]" + "|(?:gov|g[abdefghilmnpqrstuwy])|h[kmnrtu]|(?:info|int|i[delmnoqrst])"
            + "|(?:jobs|j[emop])|k[eghimnrwyz]|l[abcikrstuvy]|" + "(?:mil|mobi|museum|m[acdeghklmnopqrstuvwxyz])|" + "(?:name|net|n[acefgilopruz])|(?:org|om)|(?:pro|p[aefghklmnrstwy])|"
            + "qa|r[eouw]|s[abcdeghijklmnortuvyz]|(?:tel|travel|t[cdfghjklmnoprtvwz])" + "|u[agkmsyz]|v[aceginu]|w[fs]|y[etu]|z[amw]))|(?:(?:25[0-5]|2[0-4][0-9]"
            + "|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(?:25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}" + "|[1-9][0-9]|[1-9]|0)\\.(?:25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]"
            + "|[1-9]|0)\\.(?:25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[0-9])))" + "(?:\\:\\d{1,5})?)(\\/(?:(?:[a-zA-Z0-9\\;\\/\\?\\:\\@\\&\\=\\#\\~%\\-\\."
            + "\\+\\!\\*\\'\\(\\)\\,\\_])|(?:\\%[a-fA-F0-9]{2}))*)?");

    public static List<String> extractUrls(String input) {
        List<String> result = new ArrayList<String>();

        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            result.add(matcher.group());
        }

        return result;
    }

    public static void hideInputMethod(Context context, View v) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    public static void scanNewMedia(String path) {
        if (path != null) {
            KsyunApplication.getInstance().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(path))));
        }
    }

    public static StringBuilder printInheritance(Object object) {
        StringBuilder builder = new StringBuilder();
        if (object == null) {
            builder.append("NULL");
            return builder;
        }
        builder.append("hash:" + object.hashCode() + "	");
        Class<?> cls = object.getClass();
        while (true) {
            String name = cls.getName();
            builder.append("name	" + name);
            builder.append("\n");
            if (Object.class.getName().equals(name)) {
                break;
            } else {
                builder.append(" -> ");
                cls = cls.getSuperclass();
            }
        }
        return builder;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static SQLiteDatabase openOrCreateDatabase(Context context, String name, int mode, CursorFactory factory, DatabaseErrorHandler errorHandler) {
        SQLiteDatabase db = null;
        int flags = SQLiteDatabase.CREATE_IF_NECESSARY | SQLiteDatabase.NO_LOCALIZED_COLLATORS;
        Class<?> cls = context.getClass();
        try {
            Method method = cls.getDeclaredMethod("validateFilePath", new Class[]{String.class, boolean.class});
            method.setAccessible(true);
            File f = (File) method.invoke(context, new Object[]{name, true});
            if ((mode & Context.MODE_ENABLE_WRITE_AHEAD_LOGGING) != 0) {
                flags |= SQLiteDatabase.ENABLE_WRITE_AHEAD_LOGGING;
            }
            db = SQLiteDatabase.openDatabase(f.getPath(), factory, flags, errorHandler);
            if (db != null) {
                Method methodSFPF = cls.getDeclaredMethod("setFilePermissionsFromMode", new Class[]{String.class, int.class, int.class});
                methodSFPF.setAccessible(true);
                methodSFPF.invoke(context, new Object[]{db.getPath(), mode, 0});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return db;
    }

    public static long getDirSize(File dir) {
        if (dir == null) {
            return 0;
        }
        if (!dir.isDirectory()) {
            return 0;
        }
        long dirSize = 0;
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                dirSize += file.length();
            } else if (file.isDirectory()) {
                dirSize += file.length();
                dirSize += getDirSize(file);
            }
        }
        return dirSize;
    }

    public static boolean isPasswordVilied(String password) {
        int length = password.length();
        if (length >= 8 && length <= 30) {
            return true;
        } else {
            return false;
        }
    }

    public static String repleaseMobileNumber(String mobile) {
        return mobile.replaceAll("\\+(\\d{2})(\\d{3})\\d{4}(\\d{4})", "$2****$3");
    }

    public static String repleaseMobileNumberWithContact(String mobile) {
        return mobile.replaceAll("\\+(\\d{2})(\\d{3})\\d{4}(\\d{4})", "+$1$2****$3");
    }


    public static boolean isMobileVilied(String area, String mobile) {
        if (area.equals("+86")) {

            if (mobile.length() == 11 && mobile.startsWith("1")) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
//        }
    }

    /**
     * 判断手机号码前三位的格式是否正确
     *
     * @param
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        if (!TextUtils.isEmpty(mobiles)) {
            Pattern p = Pattern.compile("^(1[0-9]{2})\\d{8}$");
            Matcher m = p.matcher(mobiles);
            return m.matches();
        }
        return false;
    }


    public static String getNationsPhoneCode(String areaCode) {
        areaCode = areaCode.toUpperCase();
        String phoneCode = nations.get(areaCode);
        return phoneCode;
    }


    public static int getShareImageCreateUtilSize(int originSize) {
        int size = (int) (originSize * 1.3f);
        return size;
    }

    public static String grepTextBlank(String text) {
        if (!TextUtils.isEmpty(text)) {
            return text.replace(" ", "");
        }
        return text;
    }

    /**
     * new added
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            // 有存储的SDCard
            return true;
        } else {
            return false;
        }
    }

    // 获取手机状态栏高度
    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }


    //弹出软件盘
    public static void showKeyBoard(EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        InputMethodManager inputManager =
                (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(editText, 0);
    }


    /**
     * 键盘如果显示就隐藏 如果隐藏就显示。
     *
     * @param context
     */
    public static void showAndHideKeyBoard(Context context) {
        try {
            if (context == null)
                return;
            // 取消弹出的对话框
            InputMethodManager manager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //隐藏软键盘
    public static void hiddenKeyBoard(Context context) {

        try {
            if (context == null)
                return;
            // 取消弹出的对话框
            InputMethodManager manager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void sharaToSocial(String userName, String userimageUrl, int RoomId, int AnchorOpenId, String shareType, PlatformActionListener paListener) {
        Platform.ShareParams sp = new Platform.ShareParams();
        sp.setTitle(userName + "正在直播");
        sp.setText(userName + "正在直播，精彩刚刚开始，速度过来围观吧~!");
        if (shareType.equals(QQ.NAME)) {
            sp.setImageUrl(userimageUrl);
            sp.setTitleUrl(BeanConstants.SHARE_LINK + "?BusinessId=" + FlavorConstants.BUSINESSID + "&RoomId=" + RoomId + "&AnchorOpenId=" + AnchorOpenId + "&ClientType=2");
            sp.setShareType(Platform.SHARE_WEBPAGE);
        } else if (shareType.equals(WechatMoments.NAME) || shareType.equals(SinaWeibo.NAME) || shareType.equals(Wechat.NAME)) {
            sp.setImageUrl(userimageUrl);
            sp.setUrl(BeanConstants.SHARE_LINK + "?BusinessId=" + FlavorConstants.BUSINESSID + "&RoomId=" + RoomId + "&AnchorOpenId=" + AnchorOpenId + "&ClientType=2");
            sp.setShareType(Platform.SHARE_WEBPAGE);
        } else {
            sp.setImageUrl(userimageUrl);
            sp.setTitleUrl(BeanConstants.SHARE_LINK + "?BusinessId=" + FlavorConstants.BUSINESSID + "&RoomId=" + RoomId + "&AnchorOpenId=" + AnchorOpenId + "&ClientType=2");
        }
        Platform shareName = ShareSDK.getPlatform(shareType);
        shareName.setPlatformActionListener(paListener); // 设置分享事件回调
        // 执行图文分享
        shareName.share(sp);
    }


    //魅力值界面显示的数值，字符串从右到左每隔三位添加一个逗号
    public static String showCharmValue(String value) {
        value = new StringBuilder(value).reverse().toString();     //先将字符串颠倒顺序
        String str = "";
        for (int i = 0; i < value.length(); i++) {
            if (i * 3 + 3 > value.length()) {
                str += value.substring(i * 3, value.length());
                break;
            }
            str += value.substring(i * 3, i * 3 + 3) + ",";
        }
        if (str.endsWith(",")) {
            str = str.substring(0, str.length() - 1);
        }
        //最后再将顺序反转过来
        return new StringBuilder(str).reverse().toString();
    }

    /**
     * 判断网络是否可用
     *
     * @param context
     * @return 如果可用，返回true
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info == null) {
                return false;
            } else {
                if (info.isAvailable()) {
                    return true;
                }

            }
        }
        return false;
    }


    public static String DateToString(Integer time) {
        Long timeStamp = new Long(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sd = sdf.format(new Date(Long.parseLong(String.valueOf(timeStamp * 1000))));   // 时间戳转换成时间
        return sd;
    }

    public static String getRealPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = KsyunApplication.getInstance().getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    public static boolean selectVailedImage(Uri uri) {
        if (uri.getScheme().toLowerCase().equals("file")) {
            return true;
        }
        String realPath = getRealPathFromURI(uri);
        if ((realPath != null) && (realPath.toLowerCase().endsWith(".jpeg") || realPath.toLowerCase().endsWith(".jpg") || realPath.toLowerCase().endsWith(".png"))) {
            return true;
        }
        return false;
    }


    public static boolean isWXAppInstalledAndSupported(Context context,
                                                       IWXAPI api) {
        boolean sIsWXAppInstalledAndSupported = api.isWXAppInstalled()
                && api.isWXAppSupportAPI();
        if (!sIsWXAppInstalledAndSupported) {
            KsyunTopSnackBar.make(KsyunApplication.getInstance(),KsyunApplication.getInstance().getResources().getString(R.string.wechat_client_inavailable), KsyunTopSnackBar.LENGTH_LONG).show();
        }

        return sIsWXAppInstalledAndSupported;
    }
}
