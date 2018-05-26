package com.sdzk.buss.web.sms.controller;

import com.sdzk.buss.api.model.ApiResultJson;
import com.sdzk.buss.web.common.Constants;
import com.sdzk.buss.web.sms.entity.TBSMSEntity;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.*;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sdzk.buss.web.common.utils.SMSSenderUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Title: Controller
 * @Description: 授权
 * @smsor onlineGenerator
 * @date 2017-12-20 10:21:57
 * @version V1.0
 *
 */
@Controller
@RequestMapping("/tBSMSController")
public class TBSMSController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TBSMSController.class);
	@Autowired
	private SystemService systemService;

	/**
	 * 授权列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/sdzk/buss/web/sms/tBSMSList");
	}

    /**
     * 数据组装
     * datagrid
     * */
    @RequestMapping(params = "datagrid")
    public void datagrid(TBSMSEntity tBSMSEntity,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(TBSMSEntity.class, dataGrid);
        String groupName = request.getParameter("groupName");
        if(StringUtil.isNotEmpty(groupName)){
            cq.like("groupName","%"+groupName+"%");
        }
        String mineName = request.getParameter("mineName");
        if(StringUtil.isNotEmpty(mineName)){
            cq.like("mineName","%"+mineName+"%");
        }

        String type = request.getParameter("type");
        if(StringUtil.isNotEmpty(type)){
            cq.eq("type", type);
        }
        String content = request.getParameter("content");
        if(StringUtil.isNotEmpty(content)) {
            cq.like("content","%"+content+"%");
        }
        String phoneNumber = request.getParameter("phoneNumber");
        if(StringUtil.isNotEmpty(phoneNumber)) {
            cq.like("phoneNumber","%"+phoneNumber+"%");
        }
        String handleStatus = request.getParameter("handleStatus");
        if(StringUtil.isNotEmpty(handleStatus)){
            cq.eq("handleStatus", handleStatus);
        }
        try {
            String requestTimeBegin = request.getParameter("requestTime_begin1");
            String requestTimeEnd = request.getParameter("requestTime_end2");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (StringUtil.isNotEmpty(requestTimeBegin)) {
                cq.ge("requestTime", sdf.parse(requestTimeBegin));
            }
            if (StringUtil.isNotEmpty(requestTimeEnd)) {
                cq.le("requestTime", sdf.parse(requestTimeEnd));
            }

            String sendTimeBegin = request.getParameter("sendTime_begin1");
            String sendTimeEnd = request.getParameter("sendTime_end2");
            if (StringUtil.isNotEmpty(sendTimeBegin)) {
                cq.ge("sendTime", sdf.parse(sendTimeBegin));
            }
            if (StringUtil.isNotEmpty(sendTimeEnd)) {
                cq.le("sendTime", sdf.parse(sendTimeEnd));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }

        cq.add();
        this.systemService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    //短信发送接口
    /**
     * 数据组装
     * groupCode
     * groupName
     * mineCode
     * mineName
     * requestTime
     * type: 1 隐患 0 其它
     * content
     * phoneNumber
     * */
    @RequestMapping("/sendSMS.do")
    @ResponseBody
    public ApiResultJson sendSMS(HttpServletRequest request) {
        try {
            String groupName = request.getParameter("groupName");
            if(StringUtil.isEmpty(groupName)){
                return new ApiResultJson(ApiResultJson.CODE_400,ApiResultJson.CODE_400_MSG + ":groupName",null);
            }
            String groupCode = request.getParameter("groupCode");
            if(StringUtil.isEmpty(groupCode)){
                //return new ApiResultJson(ApiResultJson.CODE_400,ApiResultJson.CODE_400_MSG + ":groupCode",null);
                groupCode = groupName;
            }

            String mineName = request.getParameter("mineName");
            if(StringUtil.isEmpty(mineName)){
                return new ApiResultJson(ApiResultJson.CODE_400,ApiResultJson.CODE_400_MSG + ":mineName",null);
            }
            String mineCode = request.getParameter("mineCode");
            if(StringUtil.isEmpty(mineCode)){
                //return new ApiResultJson(ApiResultJson.CODE_400,ApiResultJson.CODE_400_MSG + ":mineCode",null);
                mineCode = mineName;
            }

            String requestTime = request.getParameter("requestTime");
            if(StringUtil.isEmpty(requestTime)){
                return new ApiResultJson(ApiResultJson.CODE_400,ApiResultJson.CODE_400_MSG + ":requestTime",null);
            }
            String type = request.getParameter("type");
            if(StringUtil.isEmpty(type)){
                return new ApiResultJson(ApiResultJson.CODE_400,ApiResultJson.CODE_400_MSG + ":type",null);
            }
            String content = request.getParameter("content");
            if(StringUtil.isEmpty(content)){
                return new ApiResultJson(ApiResultJson.CODE_400,ApiResultJson.CODE_400_MSG + ":content",null);
            }
            String phoneNumber = request.getParameter("phoneNumber");
            if(StringUtil.isEmpty(phoneNumber)){
                return new ApiResultJson(ApiResultJson.CODE_400,ApiResultJson.CODE_400_MSG + ":phoneNumber",null);
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date requestDate = sdf.parse(requestTime);
            TBSMSEntity smsEntity = new TBSMSEntity();
            smsEntity.setGroupCode(groupCode);
            smsEntity.setGroupName(groupName);
            smsEntity.setMineCode(mineCode);
            smsEntity.setMineName(mineName);
            smsEntity.setType(type);
            smsEntity.setContent(content);
            smsEntity.setPhoneNumber(phoneNumber);
            smsEntity.setRequestTime(requestDate);
            smsEntity.setHandleStatus(Constants.SMS_SEND_STATUS_NOT_SENT);
            smsEntity.setTopDepartNames(request.getParameter("topDepartNames"));
            smsEntity.setServerInfo(request.getParameter("serverInfo"));
            Date nowDate = new Date();
            boolean needSend = false;   //true:立即发送；false: 定时发送（待实现）
            if(nowDate.equals(requestDate) || nowDate.after(requestDate)){
                needSend = true;
            } else {
                needSend = false;
            }

            //发送短信
            needSend = true;
            if(needSend){
                String ret = SMSSenderUtil.sendSMS(content, phoneNumber);
                smsEntity.setSendTime(nowDate);
                if(null!=ret){
                    smsEntity.setHandleStatus(Constants.SMS_SEND_STATUS_SENT_SUCCESS);
                    this.systemService.save(smsEntity);
                } else {
                    smsEntity.setHandleStatus(Constants.SMS_SEND_STATUS_SENT_FAILURE);
                    this.systemService.save(smsEntity);
                    return new ApiResultJson(ApiResultJson.CODE_202,ApiResultJson.MSG_SMS_FAIL,null);
                }
            }

            return new ApiResultJson(ApiResultJson.CODE_200,ApiResultJson.CODE_200_MSG,null);
        } catch (Exception e) {
            LogUtil.error("短信发送失败", e);
            return new ApiResultJson(ApiResultJson.CODE_500,ApiResultJson.CODE_500_MSG,null);
        }
    }
 }
