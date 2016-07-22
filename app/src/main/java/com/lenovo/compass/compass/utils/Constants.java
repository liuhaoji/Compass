package com.lenovo.compass.compass.utils;

import com.ksyun.android.ddlive.bean.protocol.request.BeanConstants;

/**
 * Created by hansentian on 5/29/16.
 */
public class Constants {
    public static final String DB_NAME = "info.db";
    public static final String KEY_ROOM_INFO = "KEY_ROOM_INFO";
    public static final String KEY_OVER_LIVE = "KEY_OVER_LIVE";
    public static final String KEY_ROOM_ID = "KEY_ROOM_ID";
    public static final String KEY_ANCHOR_OPEN_ID = "KEY_ANCHOR_OPEN_ID";
    public static final String KEY_ANCHOR_NAME = "KEY_ANCHOR_NAME";
    public static final String TAB_RELATION = "TAB_RELATION";
    public static final String KEY_OPEN_ID = "KEY_OPEN_ID";

    public static final int USERSTATE_IN_ROOM = BeanConstants.USER_ONLINE_ROOM;
    public static final int USERSTATE_NOT_IN_OR_OUT_ROOM = -1;
    public static final int USERSTATE_OUT_ROOM = BeanConstants.USER_ONLINE_HOME;

    public static final String STREAMER_TAG = "streamer";

    public static final String ACTIVITY_USER_BUNDLE_FLAG = "activity_bundle";
    public static final String ANCHOR_ROOMID = "anchor_roomid";

    public static final String KEY_END_TYPE = "KEY_END_TYPE";
    public static final String KEY_ANCHOR_URL = "KEY_ANCHOR_URL";
    public static final String KEY_ROOM_USER_NUM = "KEY_ROOM_USER_NUM";
    public static final String KEY_PUSH_STREAM_URL = "KEY_PUSH_STREAM_URL";

    public static final int SEND_FAILED_REASON_NO_MONEY = 1;
    public static final int SEND_FAILED_REASON_NETWORK = 2;

    public static final int ANIMATION_END_TYPE_SUCCESS = 2;
    public static final int ANIMATION_END_TYPE_REMOVE = 1;

    public static final String PICK_IMAGE_TYPE = "image/jpg";
    public static final int REFRESH_NOT_ENOUGH_MONEY = 1;
}
