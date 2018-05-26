package com.sdzk.buss.web.mineorg.controller;
import com.alibaba.fastjson.JSON;
import com.sdzk.buss.web.mineorg.entity.TBMineOrgEntity;
import com.sdzk.buss.web.mineorg.service.TBMineOrgServiceI;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.dao.jdbc.JdbcDao;
import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.common.model.json.TreeGrid;
import org.jeecgframework.core.util.*;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.tag.vo.easyui.ComboTreeModel;
import org.jeecgframework.tag.vo.easyui.TreeGridModel;
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
import java.util.Map;
import java.util.HashMap;

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
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.net.URI;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;
import org.apache.commons.lang3.StringUtils;
import com.alibaba.fastjson.JSONArray;

/**
 * @Title: Controller
 * @Description: 矿井组织机构
 * @author onlineGenerator
 * @date 2018-05-18 11:03:03
 * @version V1.0
 *
 */
@Controller
@RequestMapping("/tBMineOrgController")
public class TBMineOrgController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TBMineOrgController.class);

	@Autowired
	private TBMineOrgServiceI tBMineOrgService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;



	/**
	 * 矿井组织机构列表 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/sdzk/buss/web/mineorg/tBMineOrgList");
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
	@ResponseBody
	public Object datagrid(TBMineOrgEntity tBMineOrg,HttpServletRequest request, HttpServletResponse response, TreeGrid treegrid) {
		CriteriaQuery cq = new CriteriaQuery(TBMineOrgEntity.class);
		if("yes".equals(request.getParameter("isSearch"))){
			treegrid.setId(null);
			tBMineOrg.setId(null);
		}
		if(StringUtil.isNotEmpty(tBMineOrg.getName())){
			cq.like("name","%"+tBMineOrg.getName()+"%");
		} else {
			if (treegrid.getId() == null) {
				cq.isNull("parentOrg");
			}
		}
		if (treegrid.getId() != null) {
			cq.eq("parentOrg.id", treegrid.getId());
		}

		cq.addOrder("code", SortDirection.asc);
		cq.eq("deleteFlag", Globals.Delete_Normal);     //Globals.Delete_Normal 代表正常，即未删除     Globals.Delete_Forbidden 代表删除
		cq.add();
		List<TBMineOrgEntity> mineOrgList =null;
		mineOrgList=systemService.getListByCriteriaQuery(cq, false);
		/*if(mineOrgList.size()==0&&tBMineOrg.getName()!=null){
			cq = new CriteriaQuery(TBMineOrgEntity.class);
			TBMineOrgEntity parMineOrg = new TBMineOrgEntity();
			tBMineOrg.setParentOrg(parMineOrg);
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tBMineOrg);
			mineOrgList =systemService.getListByCriteriaQuery(cq, false);
		}*/
		if(mineOrgList.size()>0){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			for(int i=0;i<mineOrgList.size();i++){
				TBMineOrgEntity mineOrgEntity = mineOrgList.get(i);
				if(null!=mineOrgEntity.getLastDeployTime()){
					mineOrgEntity.setLastDeployTimeTemp(sdf.format(mineOrgEntity.getLastDeployTime()));
				}
				if(null!=mineOrgEntity.getCreateDate()){
					mineOrgEntity.setCreateDateTemp(sdf.format(mineOrgEntity.getCreateDate()));
				}
				if(null!=mineOrgEntity.getUpdateDate()){
					mineOrgEntity.setUpdateDateTemp(sdf.format(mineOrgEntity.getUpdateDate()));
				}
			}
		}
		List<TreeGrid> treeGrids = new ArrayList<TreeGrid>();
		TreeGridModel treeGridModel = new TreeGridModel();
		treeGridModel.setTextField("name");
		treeGridModel.setParentText("parentOrg_name");
		treeGridModel.setParentId("parentOrg_id");
		treeGridModel.setSrc("isConnectToUpper");
		treeGridModel.setIdField("id");
		treeGridModel.setChildList("childOrgs");
		Map<String,Object> fieldMap = new HashMap<String, Object>();
		fieldMap.put("code", "code");
		fieldMap.put("type", "type");
		fieldMap.put("order", "order");
		fieldMap.put("isConnectToUpper", "isConnectToUpper");
		fieldMap.put("riskRecogType", "riskRecogType");
		fieldMap.put("remoteConnectCert", "remoteConnectCert");
		fieldMap.put("remoteConnectType", "remoteConnectType");
		fieldMap.put("remark", "remark");
		fieldMap.put("isCommonVersion", "isCommonVersion");
		fieldMap.put("deployBranch", "deployBranch");
		fieldMap.put("lastDeployTime", "lastDeployTime");
		fieldMap.put("lastDeployer", "lastDeployer");
		fieldMap.put("updateName", "updateName");
		fieldMap.put("updateDate", "updateDate");
		fieldMap.put("updateBy", "updateBy");
		fieldMap.put("createName", "createName");
		fieldMap.put("createDate", "createDate");
		fieldMap.put("createBy", "createBy");
		fieldMap.put("lastDeployTimeTemp", "lastDeployTimeTemp");
		fieldMap.put("createDateTemp", "createDateTemp");
		fieldMap.put("updateDateTemp", "updateDateTemp");
		treeGridModel.setFieldMap(fieldMap);
		treeGrids = systemService.treegrid(mineOrgList, treeGridModel);

		JSONArray jsonArray = new JSONArray();
		for (TreeGrid treeGrid : treeGrids) {
			jsonArray.add(JSON.parse(treeGrid.toJson()));
		}
		return jsonArray;
	}

	/**
	 * 删除矿井组织机构
	 *
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(TBMineOrgEntity tBMineOrg, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		tBMineOrg = systemService.getEntity(TBMineOrgEntity.class, tBMineOrg.getId());
		message = "矿井组织机构删除成功";
		try{
			//tBMineOrgService.delete(tBMineOrg);
			tBMineOrg.setDeleteFlag(Short.parseShort("1"));
			tBMineOrgService.saveOrUpdate(tBMineOrg);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "矿井组织机构删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除矿井组织机构
	 *
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "矿井组织机构删除成功";
		try{
			for(String id:ids.split(",")){
				TBMineOrgEntity tBMineOrg = systemService.getEntity(TBMineOrgEntity.class,
				id
				);
				//tBMineOrgService.delete(tBMineOrg);
				tBMineOrg.setDeleteFlag(Short.parseShort("1"));
				tBMineOrgService.saveOrUpdate(tBMineOrg);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "矿井组织机构删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加矿井组织机构
	 *
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(TBMineOrgEntity tBMineOrg, HttpServletRequest request) {
		String message = null;
		String pid = request.getParameter("parentOrg.id");
		if (pid.equals("")) {
			tBMineOrg.setParentOrg(null);
		}
		tBMineOrg.setDeleteFlag(Short.valueOf("0"));
		AjaxJson j = new AjaxJson();
		message = "矿井组织机构添加成功";
		if (StringUtil.isNotEmpty(tBMineOrg.getId())) {
			systemService.saveOrUpdate(tBMineOrg);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);

		} else {
			if(oConvertUtils.isNotEmpty(pid)){
				TBMineOrgEntity pMineOrg = systemService.findUniqueByProperty(TBMineOrgEntity.class, "id", pid);
				String localMaxCode  = getMaxLocalCode(pMineOrg.getCode());
				tBMineOrg.setCode(YouBianCodeUtil.getSubYouBianCode(pMineOrg.getCode(), localMaxCode));
			}else{
				String localMaxCode  = getMaxLocalCode(null);
				tBMineOrg.setCode(YouBianCodeUtil.getNextYouBianCode(localMaxCode));
			}

			systemService.save(tBMineOrg);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);

		}

		net.sf.json.JSONArray ja = new net.sf.json.JSONArray();
/*		tBMineOrg=tBMineOrg.getParentOrg();
		if(null!=tBMineOrg){
			tBMineOrg = systemService.getEntity(TBMineOrgEntity.class,tBMineOrg.getId());
		}*/
		while(null!=tBMineOrg){
			ja.add(tBMineOrg.getId());
			tBMineOrg=tBMineOrg.getParentOrg();
			if(null!=tBMineOrg){
				tBMineOrg = systemService.getEntity(TBMineOrgEntity.class,tBMineOrg.getId());
			}
		}
		j.setObj(ja);
		j.setMsg(message);
		return j;
	}
	private synchronized String getMaxLocalCode(String parentCode){
		if(oConvertUtils.isEmpty(parentCode)){
			parentCode = "";
		}
		int localCodeLength = parentCode.length() + YouBianCodeUtil.zhanweiLength;
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT org_code FROM t_b_mine_org");

		if(ResourceUtil.getJdbcUrl().indexOf(JdbcDao.DATABSE_TYPE_SQLSERVER)!=-1){
			sb.append(" where LEN(org_code) = ").append(localCodeLength);
		}else{
			sb.append(" where LENGTH(org_code) = ").append(localCodeLength);
		}

		if(oConvertUtils.isNotEmpty(parentCode)){
			sb.append(" and  org_code like '").append(parentCode).append("%'");
		}

		sb.append(" ORDER BY org_code DESC");
		List<Map<String, Object>> objMapList = systemService.findForJdbc(sb.toString(), 1, 1);
		String returnCode = null;
		if(objMapList!=null && objMapList.size()>0){
			returnCode = (String)objMapList.get(0).get("org_code");
		}

		return returnCode;
	}
	/**
	 * 更新矿井组织机构
	 *
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(TBMineOrgEntity tBMineOrg, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "矿井组织机构更新成功";
		TBMineOrgEntity t = tBMineOrgService.get(TBMineOrgEntity.class, tBMineOrg.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(tBMineOrg, t);
			tBMineOrgService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "矿井组织机构更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 父级权限列表
	 *
	 * @param request
	 * @param comboTree
	 * @return
	 */
	@RequestMapping(params = "getMineOrgTree")
	@ResponseBody
	public List<ComboTree> getMineOrgTree(HttpServletRequest request, ComboTree comboTree) {
		CriteriaQuery cq = new CriteriaQuery(TBMineOrgEntity.class);
		if (null != request.getParameter("selfId")) {
			cq.notEq("id", request.getParameter("selfId"));
		}
		if (comboTree.getId() != null) {
			cq.eq("parentOrg.id", comboTree.getId());
		}
		if (comboTree.getId() == null) {
			cq.isNull("parentOrg");
		}
		cq.addOrder("code", SortDirection.asc);
		cq.notEq("deleteFlag", Short.parseShort("1"));
		cq.add();
		List<TBMineOrgEntity> mineOrgList = systemService.getListByCriteriaQuery(cq, false);
		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		ComboTreeModel comboTreeModel = new ComboTreeModel("id", "name", "childOrgs");

/*		if (comboTree.getId() == null) {
			TBMineOrgEntity defaultMineOrg = new TBMineOrgEntity();
			defaultMineOrg.setId("");
			defaultMineOrg.setName("请选择组织机构");
			mineOrgList.add(0, defaultMineOrg);
		}*/

		comboTrees = systemService.ComboTree(mineOrgList, comboTreeModel, null, false);
		//MutiLangUtil.setMutiTree(comboTrees);
		return comboTrees;

	}

	/**
	 * 矿井组织机构新增页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(TBMineOrgEntity tBMineOrg, HttpServletRequest req) {
/*		if (StringUtil.isNotEmpty(tBMineOrg.getId())) {
			tBMineOrg = tBMineOrgService.getEntity(TBMineOrgEntity.class, tBMineOrg.getId());
			req.setAttribute("tBMineOrgPage", tBMineOrg);
		}*/
/*		List<TBMineOrgEntity> mineOrgList = systemService.getList(TBMineOrgEntity.class);
		req.setAttribute("mineOrgList", mineOrgList);*/
		req.setAttribute("pid", tBMineOrg.getId());
		return new ModelAndView("com/sdzk/buss/web/mineorg/tBMineOrg-add");
	}
	/**
	 * 矿井组织机构编辑页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(TBMineOrgEntity tBMineOrg, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tBMineOrg.getId())) {
			tBMineOrg = tBMineOrgService.getEntity(TBMineOrgEntity.class, tBMineOrg.getId());
			req.setAttribute("tBMineOrgPage", tBMineOrg);
		}
		String load = req.getParameter("load");
		req.setAttribute("load",load);
		return new ModelAndView("com/sdzk/buss/web/mineorg/tBMineOrg-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","tBMineOrgController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(TBMineOrgEntity tBMineOrg,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(TBMineOrgEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tBMineOrg, request.getParameterMap());
		List<TBMineOrgEntity> tBMineOrgs = this.tBMineOrgService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"矿井组织机构");
		modelMap.put(NormalExcelConstants.CLASS,TBMineOrgEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("矿井组织机构列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,tBMineOrgs);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(TBMineOrgEntity tBMineOrg,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"矿井组织机构");
    	modelMap.put(NormalExcelConstants.CLASS,TBMineOrgEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("矿井组织机构列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<TBMineOrgEntity> listTBMineOrgEntitys = ExcelImportUtil.importExcel(file.getInputStream(),TBMineOrgEntity.class,params);
				for (TBMineOrgEntity tBMineOrg : listTBMineOrgEntitys) {
					tBMineOrgService.save(tBMineOrg);
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

}
