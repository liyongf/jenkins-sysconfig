package com.sdzk.buss.web.common;

/**
 * Created by dell on 2017/6/19.
 */
public class Constants {


    /**
     * 短信发送状态
     */
    public static final String SMS_SEND_STATUS_NOT_SENT = "0";      //短信尚未发送
    public static final String SMS_SEND_STATUS_SENT_SUCCESS = "1";  //短息发送成功
    public static final String SMS_SEND_STATUS_SENT_FAILURE = "2";  //短信发送失败


    /**
     * 数据字典来源
     * 1 = 煤监局
     * 2 = 矿
     */
    public static final String TYPE_GROUP_ORIGIN_MJJ = "1";
    public static final String TYPE_GROUP_ORIGIN_MINE = "2";


    /**
     * 数据字典来源 系统通用是否
     * 0 = 否
     * 1 = 是
     */
    public static final String YES = "1";
    public static final String NO = "0";

}
