package com.sdzk.buss.web.auth.controller;

import com.sdzk.buss.api.model.ApiResultJson;
import com.sdzk.buss.web.common.Constants;
import com.sdzk.buss.web.auth.entity.TBAuthEntity;
import com.sdzk.buss.web.auth.service.TBAuthServiceI;
import com.sdzk.buss.web.quartz.QuartzJob;
import com.sdzk.buss.web.tbpostmanage.entity.TBPostManageEntity;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.*;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.vo.TemplateExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.pojo.base.TSTypegroup;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Title: Controller
 * @Description: 授权
 * @author onlineGenerator
 * @date 2017-12-20 10:21:57
 * @version V1.0
 *
 */
@Controller
@RequestMapping("/tBAuthController")
public class TBAuthController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TBAuthController.class);

	@Autowired
	private TBAuthServiceI tbAuthService;
	@Autowired
	private SystemService systemService;

	/**
	 * 授权列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/sdzk/buss/web/auth/tBAuthList");
	}

    /**
     * 数据组装
     * datagrid
     * */
    @RequestMapping(params = "datagrid")
    public void datagrid(TBAuthEntity tBAuthEntity,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(TBAuthEntity.class, dataGrid);
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tBAuthEntity, request.getParameterMap());
        cq.add();
        this.tbAuthService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    /**
     * 添加年度危险源
     * @param TBAuthEntity
     * @param req
     * @return
     */
    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(HttpServletRequest req) {

        return new ModelAndView("com/sdzk/buss/web/auth/tBAuthAdd");
    }

    /**
     * 添加隐患检查
     *
     * @return
     */
    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(TBAuthEntity tBAuthEntity, HttpServletRequest request) {
        String message = "添加授权信息成功";
        AjaxJson j = new AjaxJson();
        try{
            tbAuthService.save(tBAuthEntity);
            systemService.addLog("授权信息\""+tBAuthEntity.getId()+"\"添加成功",Globals.Log_Leavel_INFO,Globals.Log_Type_INSERT);

        }catch(Exception e){
            e.printStackTrace();
            message = "授权信息添加失败";
            systemService.addLog(message+"："+e.toString(),Globals.Log_Leavel_ERROR,Globals.Log_Type_INSERT);
        }
        j.setMsg(message);
        return j;
    }


    /**
     * 授权编辑页面跳转
     *
     * @return
     */
    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(TBAuthEntity TBAuthEntity, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(TBAuthEntity.getId())) {
            TBAuthEntity = tbAuthService.getEntity(TBAuthEntity.class, TBAuthEntity.getId());
            req.setAttribute("tbAuthPage", TBAuthEntity);

        }
        return new ModelAndView("com/sdzk/buss/web/auth/tBAuthUpdate");
    }


    /**
     * 授权编辑处理
     * */
    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(TBAuthEntity tBAuthEntity, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "授权信息更新成功";
        try {
            tbAuthService.saveOrUpdate(tBAuthEntity);
            systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "授权信息更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }


    /**
     * 删除授权信息
     * @param ids
     * @param request
     * @return
     */
    @RequestMapping(params = "doBatchDel")
    @ResponseBody
    public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "所选授权信息删除成功";
        try {
            if(StringUtil.isNotEmpty(ids)){
                String idStr = "";
                for(String id : ids.split(",")){
                    idStr = idStr + "'" + id + "',";
                }
                if(idStr.length()>0){
                    idStr = idStr.substring(0,idStr.length()-1);
                }
                String sql = "delete from t_b_auth where id in (" + idStr + ")";
                systemService.executeSql(sql);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "操作失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    //手机授权激活
    @RequestMapping("/active.do")
    @ResponseBody
    public ApiResultJson active(String code, String mac, HttpServletRequest request) {
        try {
            if(StringUtil.isNotEmpty(code) && StringUtil.isNotEmpty(mac)){
                CriteriaQuery cq = new CriteriaQuery(TBAuthEntity.class);
                cq.eq("authNum",code);
                cq.add();
                List<TBAuthEntity> authList = this.tbAuthService.getListByCriteriaQuery(cq,false);
                if(authList == null || authList.isEmpty()){
                    return new ApiResultJson(ApiResultJson.CODE_500,ApiResultJson.MSG_AUTH_FAIL,null);
                }
                TBAuthEntity authEntity = authList.get(0);
                if(StringUtil.isNotEmpty(authEntity.getDeviceMac()) && !authEntity.getDeviceMac().equals(mac)){
                    return new ApiResultJson(ApiResultJson.CODE_500,ApiResultJson.MSG_AUTH_FAIL,null);
                }
                authEntity.setDeviceMac(mac);
                tbAuthService.saveOrUpdate(authEntity);
                return new ApiResultJson(ApiResultJson.CODE_200,ApiResultJson.CODE_200_MSG,null);
            }
            else {
                return new ApiResultJson(ApiResultJson.CODE_500,ApiResultJson.CODE_400_MSG,null);
            }
        } catch (Exception e) {
            LogUtil.error("授权激活错误", e);
            return new ApiResultJson(ApiResultJson.CODE_500,ApiResultJson.CODE_500_MSG,null);
        }
    }
 }
