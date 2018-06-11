package com.sdzk.buss.web.minedeploy.controller;
import com.sdzk.buss.web.minedeploy.entity.TBMineDeployEntity;
import com.sdzk.buss.web.minedeploy.service.TBMineDeployServiceI;

import java.math.BigDecimal;
import java.util.*;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzk.buss.web.mineorg.entity.TBMineOrgEntity;
import org.apache.log4j.Logger;
import org.hibernate.transform.Transformers;
import org.jeecgframework.core.util.*;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.common.TreeChildCount;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;

import java.io.OutputStream;

import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.vo.TemplateExcelConstants;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.jeecgframework.core.beanvalidator.BeanValidators;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.net.URI;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;
import org.apache.commons.lang3.StringUtils;
import com.alibaba.fastjson.JSONArray;

/**   
 * @Title: Controller  
 * @Description: 矿井部署
 * @author onlineGenerator
 * @date 2018-05-18 11:04:04
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/tBMineDeployController")
public class TBMineDeployController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TBMineDeployController.class);

	@Autowired
	private TBMineDeployServiceI tBMineDeployService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 矿井部署列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/sdzk/buss/web/minedeploy/tBMineDeployList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(TBMineDeployEntity tBMineDeploy,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TBMineDeployEntity.class, dataGrid);
		//查询条件组装器
		//org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tBMineDeploy, request.getParameterMap());
		try{
			//自定义追加查询条件
			String query_deployDate_begin = request.getParameter("deployDate_begin");
			String query_deployDate_end = request.getParameter("deployDate_end");
			if(StringUtil.isNotEmpty(query_deployDate_begin)){
				cq.ge("deployDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_deployDate_begin));
			}
			if(StringUtil.isNotEmpty(query_deployDate_end)){
				cq.le("deployDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_deployDate_end));
			}

		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.tBMineDeployService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除矿井部署
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(TBMineDeployEntity tBMineDeploy, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		tBMineDeploy = systemService.getEntity(TBMineDeployEntity.class, tBMineDeploy.getId());
		message = "矿井部署删除成功";
		try{
			tBMineDeployService.delete(tBMineDeploy);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "矿井部署删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除矿井部署
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "矿井部署删除成功";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			for(String id:ids.split(",")){
				TBMineDeployEntity tBMineDeploy = systemService.getEntity(TBMineDeployEntity.class, 
				id
				);
				tBMineDeployService.delete(tBMineDeploy);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);

				String mineOrgId = tBMineDeploy.getMineOrg().getId();
				String sql = "select deployer, DATE_FORMAT(deploy_date,'%Y-%m-%d %H:%i:%s') deployDate from t_b_mine_deploy where mine_id='" + mineOrgId + "' " +
						"order by deploy_date desc LIMIT 1";
				List<Map<String,Object>> mineDeployList = systemService.findForJdbc(sql);
				TBMineOrgEntity mineOrgEntity = systemService.getEntity(TBMineOrgEntity.class,mineOrgId);
				if(null!=mineDeployList && mineDeployList.size()>0){
					mineOrgEntity.setLastDeployer((String)mineDeployList.get(0).get("deployer"));
					mineOrgEntity.setLastDeployTime(sdf.parse((String)mineDeployList.get(0).get("deployDate")));
				} else {
					mineOrgEntity.setLastDeployer(null);
					mineOrgEntity.setLastDeployTime(null);
				}
				systemService.saveOrUpdate(mineOrgEntity);

			}
		}catch(Exception e){
			e.printStackTrace();
			message = "矿井部署删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加矿井部署
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(TBMineDeployEntity tBMineDeploy, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "矿井部署添加成功";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		TSUser user = ResourceUtil.getSessionUserName();
		if(null==tBMineDeploy.getMineOrg() || StringUtil.isEmpty(tBMineDeploy.getMineOrg().getId())){
			j.setSuccess(false);
			message = "矿井部署添加失败：部署煤矿为空！";
			j.setMsg(message);
			return j;
		}
		try{
			if(StringUtil.isEmpty(tBMineDeploy.getDeployer())){
				tBMineDeploy.setDeployer(user.getRealName());
			}
			tBMineDeployService.save(tBMineDeploy);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);

			String newMineOrgId = tBMineDeploy.getMineOrg().getId();
			String sql = "select deployer, DATE_FORMAT(deploy_date,'%Y-%m-%d %H:%i:%s') deployDate from t_b_mine_deploy where mine_id='" + newMineOrgId + "' " +
					"order by deploy_date desc LIMIT 1";
			List<Map<String,Object>> mineDeployList = systemService.findForJdbc(sql);
			TBMineOrgEntity newMineOrgEntity = systemService.getEntity(TBMineOrgEntity.class,newMineOrgId);
			if(null!=mineDeployList && mineDeployList.size()>0){
				newMineOrgEntity.setLastDeployer((String)mineDeployList.get(0).get("deployer"));
				newMineOrgEntity.setLastDeployTime(sdf.parse((String)mineDeployList.get(0).get("deployDate")));
			} else {
				newMineOrgEntity.setLastDeployer(null);
				newMineOrgEntity.setLastDeployTime(null);
			}
			systemService.saveOrUpdate(newMineOrgEntity);

		}catch(Exception e){
			e.printStackTrace();
			message = "矿井部署添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新矿井部署
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(TBMineDeployEntity tBMineDeploy, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "矿井部署更新成功";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		TBMineDeployEntity t = tBMineDeployService.get(TBMineDeployEntity.class, tBMineDeploy.getId());
		try {
			String originMineOrgId = t.getMineOrg().getId();
			String newMineOrgId = tBMineDeploy.getMineOrg().getId();
			MyBeanUtils.copyBeanNotNull2Bean(tBMineDeploy, t);
			tBMineDeployService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

			if(StringUtil.isNotEmpty(originMineOrgId) && !originMineOrgId.equals(newMineOrgId)){
				String sql = "select deployer, DATE_FORMAT(deploy_date,'%Y-%m-%d %H:%i:%s') deployDate from t_b_mine_deploy where mine_id='" + originMineOrgId + "' " +
						"order by deploy_date desc LIMIT 1";
				List<Map<String,Object>> mineDeployList = systemService.findForJdbc(sql);
				TBMineOrgEntity originMineOrgEntity = systemService.getEntity(TBMineOrgEntity.class,originMineOrgId);
				if(null!=mineDeployList && mineDeployList.size()>0){
					originMineOrgEntity.setLastDeployer((String)mineDeployList.get(0).get("deployer"));
					originMineOrgEntity.setLastDeployTime(sdf.parse((String)mineDeployList.get(0).get("deployDate")));
				} else {
					originMineOrgEntity.setLastDeployer(null);
					originMineOrgEntity.setLastDeployTime(null);
				}
				systemService.saveOrUpdate(originMineOrgEntity);
			}
			if(StringUtil.isNotEmpty(newMineOrgId)){
				String sql = "select deployer, DATE_FORMAT(deploy_date,'%Y-%m-%d %H:%i:%s') deployDate from t_b_mine_deploy where mine_id='" + newMineOrgId + "' " +
						"order by deploy_date desc LIMIT 1";
				List<Map<String,Object>> mineDeployList = systemService.findForJdbc(sql);
				TBMineOrgEntity newMineOrgEntity = systemService.getEntity(TBMineOrgEntity.class,newMineOrgId);
				if(null!=mineDeployList && mineDeployList.size()>0){
					newMineOrgEntity.setLastDeployer((String)mineDeployList.get(0).get("deployer"));
					newMineOrgEntity.setLastDeployTime(sdf.parse((String)mineDeployList.get(0).get("deployDate")));
				} else {
					newMineOrgEntity.setLastDeployer(null);
					newMineOrgEntity.setLastDeployTime(null);
				}
				systemService.saveOrUpdate(newMineOrgEntity);
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "矿井部署更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 矿井部署新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(TBMineDeployEntity tBMineDeploy, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tBMineDeploy.getId())) {
			tBMineDeploy = tBMineDeployService.getEntity(TBMineDeployEntity.class, tBMineDeploy.getId());
			req.setAttribute("tBMineDeployPage", tBMineDeploy);
		}
		TSUser sessionUser = ResourceUtil.getSessionUserName();
		String userId = sessionUser.getId();
		boolean isAdmin = false;
		String sql = "select r.* from t_s_role_user ur, t_s_role r" +
				" where ur.roleid=r.id and ur.userid='"+userId+"';";
		List<Map<String, Object>> roleList = this.systemService.findForJdbc(sql);
		if (roleList != null && roleList.size() >0) {
			for(int i = 0 ; i < roleList.size() ; i++) {
				String roleCode = (String)roleList.get(i).get("rolecode");
				if("admin".equals(roleCode)){
					isAdmin = true;
					break;
				}
			}
		}
		req.setAttribute("isAdmin",isAdmin);
		req.setAttribute("deployer",sessionUser.getRealName());
		return new ModelAndView("com/sdzk/buss/web/minedeploy/tBMineDeploy-add");
	}
	/**
	 * 矿井部署编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(TBMineDeployEntity tBMineDeploy, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tBMineDeploy.getId())) {
			tBMineDeploy = tBMineDeployService.getEntity(TBMineDeployEntity.class, tBMineDeploy.getId());
			req.setAttribute("tBMineDeployPage", tBMineDeploy);
		}

		return new ModelAndView("com/sdzk/buss/web/minedeploy/tBMineDeploy-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","tBMineDeployController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(TBMineDeployEntity tBMineDeploy,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(TBMineDeployEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tBMineDeploy, request.getParameterMap());
		List<TBMineDeployEntity> tBMineDeploys = this.tBMineDeployService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"矿井部署");
		modelMap.put(NormalExcelConstants.CLASS,TBMineDeployEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("矿井部署列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,tBMineDeploys);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(TBMineDeployEntity tBMineDeploy,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"矿井部署");
    	modelMap.put(NormalExcelConstants.CLASS,TBMineDeployEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("矿井部署列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
    	"导出信息"));
    	modelMap.put(NormalExcelConstants.DATA_LIST,new ArrayList());
    	return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<TBMineDeployEntity> listTBMineDeployEntitys = ExcelImportUtil.importExcel(file.getInputStream(),TBMineDeployEntity.class,params);
				for (TBMineDeployEntity tBMineDeploy : listTBMineDeployEntitys) {
					tBMineDeployService.save(tBMineDeploy);
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}finally{
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return j;
	}

	/*************************** 根据煤矿id查询部署煤矿的风险辨识方法和分支地址  start *************************************/
	/**
	 * 隐患自动关联风险
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "queryByMineOrgId")
	@ResponseBody
	public AjaxJson queryByMineOrgId(String mineOrgId, HttpServletRequest request) {
		String message = null;
		AjaxJson retJson = new AjaxJson();
		message = "成功查询部署煤矿的风险辨识方法和分支地址!";
		try {
			if(StringUtil.isNotEmpty(mineOrgId)){
				String sql = "select org.risk_recog_type,org.deploy_branch from t_b_mine_org org where org.ID ='" + mineOrgId + "'";
				List<String []> orgList = systemService.findListbySql(sql);
				if(null!=orgList && orgList.size()>0){
					Object[] obj = (Object[]) orgList.get(0);
					Map<String,Object> retMap = new HashMap<>();
					retMap.put("riskRecogType",obj[0]);
					retMap.put("deployBranch",obj[1]);
					retJson.setObj(retMap);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "查询出现异常!";
			throw new BusinessException(e.getMessage());
		}
		retJson.setMsg(message);
		return retJson;
	}


	/*************************** 根据煤矿id查询部署煤矿的风险辨识方法和分支地址  end **************************************/

}
