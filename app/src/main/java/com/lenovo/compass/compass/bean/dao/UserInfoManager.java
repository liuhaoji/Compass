package com.lenovo.compass.compass.bean.dao;

import android.text.TextUtils;

import com.lenovo.compass.compass.CompassApplication;
import com.lenovo.compass.compass.bean.BeanConstants;
import com.lenovo.compass.compass.bean.GlobalInfo;
import com.lenovo.compass.compass.bean.business.UserInfo;
import com.lenovo.compass.compass.bean.protocol.reponse.UserLoginResponse;
import com.lenovo.compass.compass.bean.protocol.reponse.UserRegisterResponce;
import com.lenovo.compass.compass.log.LogUtil;
import com.lenovo.compass.compass.net.response.KsvcHttpCallback;
import com.lenovo.compass.compass.net.util.KsvcHttpError;
import com.lenovo.compass.compass.utils.PreferencesUtil;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hansentian on 5/21/16.
 */
public class UserInfoManager {
    public static final String TAG = "UserInfoManager";
    public static UserInfo userInfo;
    public static GlobalInfo globalInfo;
    public static String Cookie = "";
    public static String Token = "";
    public static boolean hasRight = true;
    public static String RemindMsgSnapshot ="";

    static {
        Cookie = PreferencesUtil.getString(BeanConstants.COOKIE);
        Token = PreferencesUtil.getString(BeanConstants.TOKEN);
        List<UserInfo> userList = OrmPersistManager.getInstance().readObjects(UserInfo.class);
        RemindMsgSnapshot = PreferencesUtil.getString(BeanConstants.SNAPSHOT);
        if (userList.size() > 0) {
            userInfo = userList.get(0);
        } else {
            userInfo = new UserInfo();
        }

    }

    //TODO 持久化相关载全局配置信息
    private void loadGlobalInfo() {

    }

    public static void clearUserInfo() {
        PreferencesUtil.removeString(BeanConstants.COOKIE, BeanConstants.TOKEN, BeanConstants.USER_OPENID,BeanConstants.SNAPSHOT);
        OrmPersistManager.getInstance().deleteAllObject(UserInfo.class);
        userInfo = new UserInfo();
    }

    public static String getRemindMsgSnapshot() {
        if (!TextUtils.isEmpty(RemindMsgSnapshot)) {
            return RemindMsgSnapshot;
        } else {
            return "";
        }
    }

    public static void setRemindMsgSnapshot(String remindMsgSnapshot) {
        RemindMsgSnapshot = remindMsgSnapshot;
        PreferencesUtil.putString(BeanConstants.SNAPSHOT, RemindMsgSnapshot);
    }

    public static void setCookie(String cookie) {
        LogUtil.e(TAG, "setUserKey=" + cookie);
        Cookie = cookie;
        PreferencesUtil.putString(BeanConstants.COOKIE, cookie);
    }

    public static String getCookie() {
        if (!TextUtils.isEmpty(Cookie)) {
            return Cookie;
        } else {
            return "";
        }
    }

    public static void setUserInfo(@NotNull UserRegisterResponce userRegisterResponce) {
        userInfo.setUserId(userRegisterResponce.getOpenId());
        saveUserInfo();
    }

    public static void setUserInfo(@NotNull UserLoginResponse userRegisterResponce) {
        userInfo.setUserId(userRegisterResponce.getOpenId());
        saveUserInfo();
    }

    public static void saveUserInfo() {
        OrmPersistManager.getInstance().deleteAllObject(UserInfo.class);
        OrmPersistManager.getInstance().saveObject(userInfo);
    }

    public static String getToken() {
        if (!TextUtils.isEmpty(Token)) {
            return Token;
        } else {
            return "";
        }
    }

    public static void setToken(String token) {
        Token = token;
        PreferencesUtil.putString(BeanConstants.TOKEN, token);
    }

    public static UserInfo getUserInfo() {
        return userInfo;
    }

    public static void setUserInfo(@NotNull UserInfo userInfo) {
        UserInfoManager.userInfo = userInfo;
    }

    public static void setUserInfoAndSave(@NotNull UserInfo userInfo) {
        setUserInfo(userInfo);
        saveUserInfo();
    }

    public static GlobalInfo getGlobalInfo() {
        return globalInfo;
    }

    public static void setGlobalInfo(GlobalInfo globalInfo) {
        UserInfoManager.globalInfo = globalInfo;
    }

    public static boolean isHasRight() {
        hasRight = PreferencesUtil.getBoolean(CompassApplication.getInstance(), BeanConstants.HAS_RIGHT, true);
        return hasRight;
    }

    public static void setHasRight(boolean value) {
        hasRight = value;
        PreferencesUtil.putBoolean(CompassApplication.getInstance(), BeanConstants.HAS_RIGHT, value);
    }



    public static synchronized void refreshUserData() {
        int userid = UserInfoManager.getUserInfo().getUserId();
        List<Integer> mOpenIdList = new ArrayList<>();
        List<Integer> mAttrIdList = new ArrayList<>();
        mOpenIdList.add(userid);
        mAttrIdList.add(BeanConstants.ENUM_USER_All);
        UserApi.doQueryUserInfo(mOpenIdList, mAttrIdList, new KsvcHttpCallback() {
            @Override
            public void onSuccess(JSONObject response) {
                BaseResponse<QueryUserAttrResponse> resp = BaseParser.parseJsonObject(response, QueryUserAttrResponse.class);
                if (resp.isSuccess()) {
                    UserInfo myUserInfo = BaseParser.parseRoomInfo(resp, UserInfoManager.getUserInfo());
                    UserInfoManager.setUserInfoAndSave(myUserInfo);
                } else {
                    //TODO show error or saved userinfo
                }
            }

            @Override
            public void onFailure(KsvcHttpError ksvcHttpError) {

            }
        });
    }
}
