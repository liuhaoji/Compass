package com.lenovo.compass.compass.bean;


import com.lenovo.compass.compass.protocol.apiImp.BaseModelApi;

/**
 * Created by hansentian on 5/21/16.
 */
public class BeanConstants {
    public static final int ENUM_DATA_INT = 1; //整形
    public static final int ENUM_DATA_STRING = 2; //字符串

    public static final int ENUM_USER_All = -1; //查询所有的用户属性
    public static final int ENUM_USER_NAME = 1; //用户昵称，默认值""，类型string
    public static final int ENUM_USER_URL = 2; //用户头像，默认值""，类型string
    public static final int ENUM_USER_SEX = 3; //用户性别，默认值3，类型int
    public static final int ENUM_ANCHOR_TYPE = 4; //用户是否是主播，1是，2不是，默认值2，类型int
    public static final int ENUM_ANCHOR_ROOMID = 5; //主播房间号，默认值0，类型int
    public static final int ENUM_YUNPIAO_NUM = 6; //云票数，默认值0，类型int
    public static final int ENUM_CONCERN_NUM = 7; //关注数，默认值0，类型int
    public static final int ENUM_CONCERNED_NUM = 8; //被关注数，默认值0，类型int
    public static final int ENUM_HISTORY_PLAY_NUM = 9; //历史播放次数，默认值0，类型int
    public static final int ENUM_USER_POS = 10; //主播位置，默认值""，类型string
    public static final int ENUM_USER_ABSTRACT = 11; //主播简介，默认值""，类型string
    public static final int ENUM_USER_LEVEL = 12; //用户等级
    public static final int ENUM_USER_MONEY = 13; //账户余额，单位RMB分
    public static final int ENUM_USER_THUMBNAIL = 14; //用户直播的略缩图
    public static final int ENUM_USER_DIAMOND = 15; //用户钻
    public static final int ENUM_USER_CHARM = 16; //用户魅力值
    public static final int ENUM_USER_EXPERIENCE_VALUE = 17; //用户经验值
    public static final int ENUM_USER_INVEST = 18; //用户投资额

    //错误码定义
    public static final int RESULT_OK = 200; //成功
    public static final int RESULT_NOT_IMPLEMENTED = 501; //未实现
    public static final int CHECK_CONTENT_ERROR = 1000; //检查content失败
    public static final int CHECK_COOKIE_ERROR = 1010; //校验cookie失败
    public static final int SYS_INTERNAL_ERROR = 1020; //系统内部错误
    public static final int LOGIN_NO_FOUND_ACCOUNT_ERROR = 1030; //登录账号不存在
    public static final int LOGIN_PASSWORD_ERROR = 1040; //登录密码错误
    public static final int QUERY_DB_ERROR = 1050; //查询DB失败
    public static final int UPDATE_DB_ERROR = 1051; //写入DB失败
    public static final int REGISTER_HAD_ACCOUNT_ERROR = 1060; //注册用户名已存在
    public static final int CREATE_COOKIE_ERROR = 1070; //生成cookie失败
    public static final int RPC_FAILED_ERROR = 1080; //远程调用失败
    public static final int GENERATE_CONTENT_ERROR = 1090; //PB生成JSON失败
    public static final int CHECK_PARAM_ERROR = 1100; //校验参数错误
    public static final int CHECK_PERMISSION_ERROR = 1110; //没有权限
    public static final int OUT_SERVICE_FAILED_ERROR = 1120; //远程调用失败
    public static final int GET_ACCESS_TOKEN_ERROR = 1130; //取access_token失败
    public static final int GET_THIRD_USER_INFO_ERROR = 1140; //取第三方用户数据失败
    public static final int RECAPTCHA_CHECK_VALUE_ERROR = 1150; //验证码不正确
    public static final int RECAPTCHA_CHECK_TIME_ERROR = 1160; //验证码已过期
    public static final int ANCHOR_HEAD_URL_NEED_CHECK = 1170; //上传属性成功，但主播头像需要审核
    public static final int UER_IN_BLACKLIST_ERROR = 1180; //用户在黑名单中，不允许登录
    public static final int ANCHOR_IN_BLACKLIST_ERROR = 1190; //主播被封禁，无法开播
    public static final int DUPLICATE_OPERATION = 1200; //重复操作
    public static final int USER_ATTR_EMPTY = 1210; //用户信息不全
    public static final int VERIFICODE_WRONG = 1220; //验证码错误
    public static final int INFO_NOT_EXISTS = 2001;   //信息不存在
    public static final int BALANCE_NOT_ENOUGH = 3001; //账户余额不足
    public static final int ILLEGAL_REQUEST = 4001;//请求不合法
    public static final int FINANCIAL_APPLEPAY_FAIL = 5001;//apple pay失败


    //是否关注
    public static final int CONCERN = 1; //关注
    public static final int NOTCONCERN = 2; //未关注


    public static final int ENUM_SEX_MAN = 1; //男
    public static final int ENUM_SEX_WOMAN = 2; //女
    public static final int ENUM_SEX_SECRET = 3; //保密
    //用户状态定义
    public static final int USER_ONLINE_HOME = 1; //用户在线,在大厅里
    public static final int USER_ONLINE_ROOM = 2; //用户在线，在房间里
    public static final int USER_OFFLINE = 3; //用户离线
    public static final String COOKIE = "UserKey";
    public static final String UserKey = "UserKey";
    public static final String TOKEN = "Token";
    public static final String USER_OPENID = "useropenid";
    //用户账号状态
    public static final int ENUM_COOKIE_NORMAL = 1;//cookie正常
    public static final int ENUM_COOKIE_NOT_INIT = 2;//cookie未初始化
    public static final int ENUM_COOKIE_NO_FOUND = 3;//cookie未找到

    //推荐列表查询类型
    public static final int ENUM_LIST_ATTENTION = 1;//查询关注列表
    public static final int ENUM_LIST_RECOMMEND = 2;//查询推荐列表
    public static final int ENUM_LIST_NEW = 3; //查询最新

    //进退房间请求
    public static final int ENUM_ENTER_ROOM_TYPE = 1; //进房
    public static final int ENUM_QUIT_ROOM_TYPE = 2; //退房

    public static final int QUERY_ALL_GLOBAL_LIST = -1;

    //全局配置相关信息
    public static final int ENUM_GLOBAL_THUMBUP = 120; //点赞间隔时间
    public static final int ENUM_GLOBAL_HEARTBEAT_OUTROOM = 121; //房间外心跳间隔时间
    public static final int ENUM_GLOBAL_HEARTBEAT_INROOM = 122; //房间内心跳间隔时间
    public static final int ENUM_GLOBAL_ABOUT_URL = 123;//关于页面地址
    public static final int ENUM_GLOBAL_USER_PROTOCOL = 124; //用户协议界面URL

    public static final int ENUM_CONCERN = 1; //关注
    public static final int ENUM_CONCERNED = 2; //被关注
    public static final int ENUM_BLOCK = 3; //拉黑
    public static final int ENUM_BLOCKED = 4; //被拉黑

    public static final int ENUM_LOGIN_NORMAL = 1; //正常登录
    public static final int ENUM_LOGIN_WECHAT = 2; //微信登录
    public static final int ENUM_LOGIN_QQ = 3; //QQ登录
    public static final int ENUM_LOGIN_WEIBO = 4; //微博登录

    public static final String TOGGLEFANS = "ToggleFans";//消息提醒设置当有人关注了我
    public static final String TOGGLEFOLLOW = "ToggleFollow";//我关注的人直播提醒
    public static final int ENUM_PUSH_ON_WHEN_CONCERNED = 103;//当有人关注了你，是否开启推送
    public static final int ENUM_PUSH_ON_WHEN_START_LIVE = 104;//当你关注的主播开播，是否开启推送
    public static final int ENUM_ESTATUS_OPEN = 1;
    public static final int ENUM_ESTATUS_CLOSE = 2;
    public static final int PUSH_TYPE_WHEN_CONCERNED = 0;
    public static final int PUSH_TYPE_WHEN_START_LIVE = 1;

    public static final int ENUM_NOT_SUBMIT_AUDIT = 1; //尚未提交审核
    public static final int ENUM_AUDIT_LOADING = 2;//审核进行中
    public static final int ENUM_AUDIT_SUCCESS = 3; //审核通过
    public static final int ENUM_AUDIT_REFUSE = 4; //第一次审核被拒绝
    public static final int ENUM_AUDIT_FIRST_SUCCESS = 5; //第一次审核通过，等待第二次审核
    public static final int ENUM_AUDIT_SECOND_REFUSE = 6; //第二次审核被拒绝
    public static final int ENUM_AUDIT_ANCHOR_FORBID = 7;//主播被禁播
    public static final String AUDIT_SUCCESS = "audit_success";//sp中保存的key


    public static final int ENUM_CHARMVALUE = 0; //魅力值页
    public static final int ENUM_FOLLOW = 1; //关注页
    public static final int ENUM_FANS = 2; //粉丝页


    public static final String SHARE_LINK = BaseModelApi.SERVER_PREFIX_NO_VERSION + "/static/views/index.html"; //粉丝页

    public static final String ANCHOR_OPENID = "Anchor_openId";

    public static final String CONTRIBUTION_TYPE = "ContributionType";
    public static final String HAS_RIGHT = "HasRight";

    public static final int SYNC_MESSAGE_TYPE = 4; //提醒消息TYPE

    public static final int REMIND_MESSAGE_TYPE = 3; //提醒消息TYPE
    public static final String SNAPSHOT = "snapshot";//消息快照

    //礼物相关的TYPE
    public static final int UPANIM = 0;//动画位置：上排
    public static final int DOWNANIM = 1;//动画位置：下排
    //画类型,1普通图,2逐帧动画
    public static final int SINGLE_ANIMATION = 1;
    public static final int FRAMES_ANIMATION = 2;
    ////1连送礼物,0非连送礼物
    public static final int NOT_CONTINUE = 0;
    public static final int IS_CONTINUE = 1;
    //客户端系统类型
    public static final int ENUM_CLIENT_IOS = 1; //IOS
    public static final int ENUM_CLIENT_ANDROID = 2; //安卓

    public static final String SERVICE_AGREMENT_URL = "http://183.131.21.162:8980/feedback/GetUserProtocol?BusinessId=1000";

    public static final String SNAPSHOTMESSAGE = "snapshotmessage";//消息快照

    public static final String SYSTEM_MESSAGE = "SystemMessage";
    public static final String LIVING_MESSAGE = "LivingMessage";


    public static final int UPLOAD_TYPE_HEADER = 1;//上传头像
    public static final int UPLOAD_TYPE_IDENTIFY = 2;//上传身份证
    public static final String FAIL_REASON = "fail_reason";
    public static final int LIVE_TYPE_LIVING = 1;
    public static final int LIVE_TYPE_NOT_LIVING = 2;


    //房间聊天区消息的不同类型
    public static final int CHAT_TYPE_WATCHERJOINORLEAVEARRIVAL = 0;
    public static final int CHAT_TYPE_ROOMMESSAGEARRIVAL = 1;
    public static final int CHAT_TYPE_FOLLOWROOMOWNERARRIVAL = 2;
    public static final int CHAT_TYPE_SYSTEMMESSAGEARRIVAL = 3;
    public static final int CHAT_TYPE_COMMENTARRIVAL = 4;
    public static final int CHAT_TYPE_FAVOURITEARRIVAL = 5;
    public static final int CHAT_TYPE_GIFTMESSAGEARRIVAL = 6;
    public static final int CHAT_TYPE_SYSTEMMESSAGERULEARRIVAL = 7;

}
