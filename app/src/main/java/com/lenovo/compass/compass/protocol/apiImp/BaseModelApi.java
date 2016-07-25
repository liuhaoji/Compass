package com.lenovo.compass.compass.protocol.apiImp;

import com.lenovo.compass.compass.bean.BeanConstants;
import com.lenovo.compass.compass.bean.dao.UserInfoManager;
import com.lenovo.compass.compass.log.LogUtil;
import com.lenovo.compass.compass.net.core.KsvcHttpUtil;
import com.lenovo.compass.compass.net.response.KsvcHttpCallback;
import com.lenovo.compass.compass.utils.GsonUtil;

import java.util.HashMap;

/**
 * Created by Liuxin on 2016/7/22.
 */
public class BaseModelApi {
    private static final String TAG = "baseModelApi";
    public static String SERVER_PREFIX = "http://live.qyvideo.net/v1.0";
    //    public static String SERVER_PREFIX = "http://10.4.23.114:10010";
//    protected static String SERVER_PREFIX_INFO = "http://183.131.21.162:8980";
    protected static String SERVER_PREFIX_AUTH = "http://10.4.23.114:8980";
    protected static final String cookie = "";
    protected static final String ACCOUNT = "/account";
    protected static final String COMMON = "/common";
    protected static final String RECOMMEND = "/recommend";
    protected static final String HOME_PAGE = "/homepage";
    protected static final String MESSAGE = "/message";
    protected static final String UNIVERSAL = "/universal";
    protected static final String RELATION = "/relation";
    protected static final String FEEDBACK = "/feedback";
    protected static final String AuthInfo = UNIVERSAL + "/getauthinfo";
    protected static final String UploadInfo = UNIVERSAL + "/queryuploadinfo";
    protected static final String UserLogin = ACCOUNT + "/userlogin";
    protected static final String ThirdPartLogin = ACCOUNT + "/thirdpartylogin";
    protected static final String QueryCookieStat = ACCOUNT + "/querycookiestat";
    protected static final String UserLogout = ACCOUNT + "/userlogout";
    protected static final String UserRegister = ACCOUNT + "/userregister";
    protected static final String SendReCaptcha = ACCOUNT + "/sendrecaptcha";
    protected static final String SetUserAttr = ACCOUNT + "/setuserattr";
    protected static final String QueryUserInfo = ACCOUNT + "/queryuserattr";
    protected static final String QueryRecommendList = RECOMMEND + "/queryrecommendlist";
    protected static final String QueryHomepage = HOME_PAGE + "/queryhomepageinfo";
    protected static final String ROOM = "/room";
    protected static final String QueryPullStreamAddr = ROOM + "/querypullstreamaddr";
    protected static final String BeginLive = ROOM + "/beginlive";
    protected static final String EndLive = ROOM + "/endlive";
    protected static final String LiveStream = "video/livestream";
    protected static final String QueryPushStreamAddr = ROOM + "/querypushstreamaddr";
    protected static final String QueryRoomUserList = ROOM + "/queryroomuserlist";
    protected static final String EnterOrQuitRoom = ROOM + "/userenterorquitroom";
    protected static final String QueryLivingRoom = ROOM + "/querylivingroom";
    protected static final String QueryRoomInfo = ROOM + "/queryroominfo";
    protected static final String SendComment = ROOM + "/sendcomment";
    protected static final String UserState = "/userstate";
    protected static final String ReportUserState = UserState + "/reportuserstate";
    protected static final String GetImToken = MESSAGE + "/getimtoken";
    protected static final String queryglobalconfiglist = UNIVERSAL + "/queryglobalconfiglist";
    protected static final String GetbackPsw = ACCOUNT + "/findpassword";
    protected static final String IsRelated = RELATION + "/isrelated";
    protected static final String CreateRelation = RELATION + "/createrelation";
    protected static final String DeleteRelation = RELATION + "/deleterelation";
    protected static final String QueryRelationList = RELATION + "/queryrelationuserlist";
    protected static final String BindPush = MESSAGE + "/bindpush";
    protected static final String QueryMyGrade = HOME_PAGE + "/queryrankinfo";
    protected static final String Financial = "/financial";
    protected static final String Createtransaction = Financial + "/createtransaction";
    protected static final String Checktransactionstatus = Financial + "/checktransactionstatus";
    protected static final String GetRechargeInfo = Financial + "/getrechargeinfo";
    protected static final String Gift = "/gift";
    protected static final String SendGift = Gift + "/sendgift";
    protected static final String QueryGiftList = Gift + "/querygiftlist";
    protected static final String DoAnchorApplyAction = ACCOUNT + "/anchorsubmitaudit";
    protected static final String QueryAuditState = ACCOUNT + "/anchorqueryauditstat";
    protected static final String QuerycontributionList = Gift + "/querycontributionlist";
    protected static final String CreateFeedback = FEEDBACK + "/createfeedback";
    protected static final String CreateReport = FEEDBACK + "/createreport";
    protected static final String SyncMessage = MESSAGE + "/syncmessage";
    //用户协议URl
    public static final String ServiceAgrement = FEEDBACK + "/GetUserProtocol" + "?BusinessId=";
    //没有加版本号,用户协议和分享的链接地址没有前缀没有版本号(后面要跟服务端商量)
    public static String SERVER_PREFIX_NO_VERSION = "http://183.131.21.162:8000";

    public static final String Queryshopurllist = ACCOUNT + "/queryshopurllist";

    public static final String Setusershopurl = ACCOUNT + "/setusershopurl";


    protected static void exeRequest(String url, Object jsonBean, KsvcHttpCallback ksvcHttpCallback) {
        KsvcHttpUtil.post(url, GsonUtil.bean2Json(jsonBean), new HashMap<String, String>(), ksvcHttpCallback);
    }

    protected static void exeRequest(String url, KsvcHttpCallback ksvcHttpCallback) {
        KsvcHttpUtil.post(url, "", new HashMap<String, String>(), ksvcHttpCallback);
    }


    protected static void exeGetRequest(String url, HashMap<String, String> parms, KsvcHttpCallback ksvcHttpCallback) {
        KsvcHttpUtil.get(url, parms, new HashMap<String, String>(), ksvcHttpCallback);
    }

    protected static void exeRequestWithCookie(String url, Object jsonBean, KsvcHttpCallback ksvcHttpCallback) {
        HashMap<String, String> header = new HashMap<>();
        header.put(BeanConstants.UserKey, UserInfoManager.getCookie());
        KsvcHttpUtil.post(url, GsonUtil.bean2Json(jsonBean), header, ksvcHttpCallback);
        LogUtil.d(TAG, url + " " + GsonUtil.bean2Json(jsonBean));
    }

    protected static void exeRequestWithCookie(String url, String jsonStr, KsvcHttpCallback ksvcHttpCallback) {
        HashMap<String, String> header = new HashMap<>();
        header.put(BeanConstants.UserKey, UserInfoManager.getCookie());
        KsvcHttpUtil.post(url, jsonStr, header, ksvcHttpCallback);
    }

    protected static void exeRequestWithCookie(String url, KsvcHttpCallback ksvcHttpCallback) {
        HashMap<String, String> header = new HashMap<>();
        header.put(BeanConstants.UserKey, UserInfoManager.getCookie());
        KsvcHttpUtil.post(url, "{}", header, ksvcHttpCallback);
        LogUtil.d(TAG, url);
    }

}
