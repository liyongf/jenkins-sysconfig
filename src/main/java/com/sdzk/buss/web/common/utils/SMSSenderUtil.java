package com.sdzk.buss.web.common.utils;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.ResourceUtil;

/**
 * Created by hanshifeng on 17-9-20.
 */
public class SMSSenderUtil {

    private static final Logger logger = Logger.getLogger(SMSSenderUtil.class);
    /**
     * 发短信
     * @param content
     * @return
     */
    public static String sendSMS(String content,String mobiles) {
        String info = null;
        try{
            String url = ResourceUtil.getConfigByName("smsSendUrl");
            HttpClient httpclient = new HttpClient();
            PostMethod post = new PostMethod(url);
            post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
            post.addParameter("userid", "649");
            post.addParameter("account", "hanshifeng");
            post.addParameter("password","a031511113");
            post.addParameter("mobile", mobiles);
            post.addParameter("content", content);
            post.addParameter("sendTime", "");
            post.addParameter("action", "send");
            post.addParameter("extno", "");
            httpclient.executeMethod(post);
            info = new String(post.getResponseBody(),"utf-8");
            System.out.println(info);
            return info;
        }catch (Exception e) {
            e.printStackTrace();
            logger.error(ExceptionUtil.getExceptionMessage(e));
        }
        return null;
    }

    public static String sendSMS2(String content,String mobiles) {
        String info = null;
        try{
            String url = ResourceUtil.getConfigByName("smsSendUrl");
            HttpClient httpclient = new HttpClient();
            PostMethod post = new PostMethod(url);
            post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
            post.addParameter("type","send");
            post.addParameter("username","15152190994");
            post.addParameter("password","F3FF3A2E9A982F65ADC9EF9527517BBB");
            post.addParameter("gwid","86e3863");
            post.addParameter("mobile",mobiles);
            post.addParameter("message",content);
            httpclient.executeMethod(post);
            info = new String(post.getResponseBody(),"utf-8");
            System.out.println(info);
            return info;
        }catch (Exception e) {
            e.printStackTrace();
            logger.error(ExceptionUtil.getExceptionMessage(e));
        }
        return null;
    }

    public static void main(String[] args) {
        sendSMS2("【安监部】通知：您有一条待整改的隐患，限期时间2017-9-21，请尽快处理！退订回T","18260789238");
    }
}
