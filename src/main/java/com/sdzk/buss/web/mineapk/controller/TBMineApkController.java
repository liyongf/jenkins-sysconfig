package com.sdzk.buss.web.mineapk.controller;
import com.sdzk.buss.web.common.Constants;
import com.sdzk.buss.web.mineapk.entity.TBMineApkEntity;
import com.sdzk.buss.web.mineapk.service.TBMineApkServiceI;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzk.buss.web.mineorg.entity.TBMineOrgEntity;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.model.common.UploadFile;
import org.jeecgframework.core.util.*;
import org.jeecgframework.web.system.pojo.base.*;
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
/*import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;*/

/**   
 * @Title: Controller  
 * @Description: 矿井APK配置
 * @author onlineGenerator
 * @date 2018-06-28 15:15:55
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/tBMineApkController")
public class TBMineApkController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TBMineApkController.class);

	@Autowired
	private TBMineApkServiceI tBMineApkService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * APK配置列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		String mineId = request.getParameter("mineId");
		request.setAttribute("mineId",mineId);
		return new ModelAndView("com/sdzk/buss/web/mineapk/tBMineApkList");
		//return new ModelAndView("com/sdzk/buss/web/mineapk/operationList");
		//return new ModelAndView("system/operation/operationList");
	}

	/**
	 * 矿井APK配置列表 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "mineApkList")
	public ModelAndView mineApkList(HttpServletRequest request) {
		return new ModelAndView("com/sdzk/buss/web/mineapk/tBMineList");
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
	public void datagrid(TBMineApkEntity tBMineApk,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TBMineApkEntity.class, dataGrid);
		tBMineApk.setVersionName(null);
		tBMineApk.setUpdateContent(null);
//		tBMineApk.setMineName(null);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tBMineApk, request.getParameterMap());
		try{
		//自定义追加查询条件
			String versionName = request.getParameter("versionName");
			if(StringUtil.isNotEmpty(versionName)){
				cq.like("versionName","%"+versionName+"%");
			}
			String updateContent = request.getParameter("updateContent");
			if(StringUtil.isNotEmpty(updateContent)){
				cq.like("updateContent","%"+updateContent+"%");
			}
/*			String mineName = request.getParameter("mineName");
			if(StringUtil.isNotEmpty(mineName)){
				cq.like("mineName","%"+mineName+"%");
			}*/
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.tBMineApkService.getDataGridReturn(cq, true);
		if(dataGrid != null && dataGrid.getResults() != null){
			List<TBMineApkEntity> list = dataGrid.getResults();
			for(TBMineApkEntity t : list){
				if(StringUtil.isNotEmpty(t.getMineId())){
					TBMineOrgEntity mineOrgEntity = systemService.getEntity(TBMineOrgEntity.class,t.getMineId());
					if(null!=mineOrgEntity){
						t.setMineName(mineOrgEntity.getName());
					}
				}

				CriteriaQuery cqDoc = new CriteriaQuery(TSDocument.class);
				cqDoc.eq("businessKey",t.getId());
				cqDoc.add();
				List<TSDocument> docList = this.tBMineApkService.getListByCriteriaQuery(cqDoc,false);
				if(null!=docList && docList.size()>0){
					t.setDocumentId(docList.get(0).getId());
				}
			}

		}
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除矿井APK配置
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(TBMineApkEntity tBMineApk, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		tBMineApk = systemService.getEntity(TBMineApkEntity.class, tBMineApk.getId());
		message = "矿井APK配置删除成功";
		try{
			tBMineApkService.delete(tBMineApk);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "矿井APK配置删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除矿井APK配置
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "矿井APK配置删除成功";
		try{
			for(String id:ids.split(",")){
				TBMineApkEntity tBMineApk = systemService.getEntity(TBMineApkEntity.class, 
				id
				);
				tBMineApkService.delete(tBMineApk);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);

				//删除关联的apk
				CriteriaQuery cq = new CriteriaQuery(TSDocument.class);
				cq.eq("businessKey",id);
				cq.add();
				List<TSDocument> documentList = this.tBMineApkService.getListByCriteriaQuery(cq,false);
				if(null!=documentList && documentList.size()>0){
					for(TSDocument doc : documentList){
						doc.setStatus(0);
					}
					this.systemService.batchSave(documentList);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "矿井APK配置删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加矿井APK配置
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(TBMineApkEntity tBMineApk, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "矿井APK配置添加成功";
		try{
			if(Constants.YES.equals(tBMineApk.getIsCurrentVersion())){
				if(StringUtil.isNotEmpty(tBMineApk.getMineId())) {
					systemService.executeSql("update t_b_mine_apk set is_current_version='0' where mine_id='" + tBMineApk.getMineId() + "'");
				} else {
					systemService.executeSql("update t_b_mine_apk set is_current_version='0' where mine_id is null");
				}
			}
			tBMineApkService.save(tBMineApk);
			String id = tBMineApk.getId();
			Map<String,Object> retMap = new HashMap<String,Object>();
			retMap.put("id",id);
			j.setAttributes(retMap);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "矿井APK配置添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新矿井APK配置
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(TBMineApkEntity tBMineApk, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "矿井APK配置更新成功";
		TBMineApkEntity t = tBMineApkService.get(TBMineApkEntity.class, tBMineApk.getId());
		try {
			if(Constants.YES.equals(tBMineApk.getIsCurrentVersion())){
				if(StringUtil.isNotEmpty(tBMineApk.getMineId())) {
					systemService.executeSql("update t_b_mine_apk set is_current_version='0' where mine_id='" + tBMineApk.getMineId() + "'");
				} else {
					systemService.executeSql("update t_b_mine_apk set is_current_version='0' where mine_id is null");
				}
			}

			String delAttachmentId = request.getParameter("delAttachmentId");
			if(StringUtils.isNotBlank(delAttachmentId)){
				String ids [] = delAttachmentId.split(",");
				if(ids != null && ids.length >0){
					for(String idTemp : ids){
						this.systemService.updateBySqlString(" update t_s_document set  status = 0 where id = '"+idTemp+"' ");
						systemService.addLog("文件\""+idTemp+"\"状态更新成功",Globals.Log_Leavel_INFO,Globals.Log_Type_UPDATE);
					}
				}
			}
			MyBeanUtils.copyBeanNotNull2Bean(tBMineApk, t);
			tBMineApkService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "矿井APK配置更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 矿井APK配置新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(TBMineApkEntity tBMineApk, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tBMineApk.getId())) {
			tBMineApk = tBMineApkService.getEntity(TBMineApkEntity.class, tBMineApk.getId());
		}
		req.setAttribute("tBMineApkPage", tBMineApk);
		return new ModelAndView("com/sdzk/buss/web/mineapk/tBMineApk-add");
	}
	/**
	 * 矿井APK配置编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(TBMineApkEntity tBMineApk, HttpServletRequest req) {
		String load = req.getParameter("load");
		req.setAttribute("load",load);
		if (StringUtil.isNotEmpty(tBMineApk.getId())) {
			tBMineApk = tBMineApkService.getEntity(TBMineApkEntity.class, tBMineApk.getId());
			req.setAttribute("tBMineApkPage", tBMineApk);

			List<String> list = this.systemService.findListbySql("select tsa.id FROM t_s_document as document , t_s_attachment as tsa where document.id = tsa.id and document.`status` = '1' and businesskey = '"+tBMineApk.getId()+"'");
			List<TSAttachment> tsaList = new ArrayList<TSAttachment>();
			StringBuffer attachmentIds = new StringBuffer();
			if(list != null && list.size() >0){
				for(String idTemp : list){
					//判断文件是否可用
					TSAttachment tsAttachment = this.systemService.getEntity(TSAttachment.class,idTemp);
					tsaList.add(tsAttachment);
					if(StringUtils.isNotBlank(attachmentIds.toString())){
						attachmentIds.append(",");
					}
					attachmentIds.append(tBMineApk.getId());
				}
			}
			req.setAttribute("attachmentIds",attachmentIds);
			req.setAttribute("list",tsaList);
		}
		return new ModelAndView("com/sdzk/buss/web/mineapk/tBMineApk-update");
	}

	@RequestMapping(params = "doAddApk")
	@ResponseBody
	public AjaxJson doAddApk( HttpServletRequest request,TSDocument document) {
		String message = "APK添加成功";
		//移除删除的附件
		String delAttachmentId = request.getParameter("delAttachmentId");
		if(StringUtils.isNotBlank(delAttachmentId)){
			message = "APK修改成功";
		}
		// 文件标题
		String documentTitle = oConvertUtils.getString(request.getParameter("documentTitle"));
		AjaxJson j = new AjaxJson();

		String bussId = request.getParameter("bussId");
		TSTypegroup tsTypegroup=systemService.getTypeGroup("filetype","文档分类");
		TSType tsType = systemService.getType("apk","app安装包", tsTypegroup);
		// 文件ID
		String fileKey = oConvertUtils.getString(request.getParameter("fileKey"));
		if(document != null){
			if (StringUtil.isNotEmpty(fileKey)) {
				document.setId(fileKey);
				document = systemService.getEntity(TSDocument.class, fileKey);
				document.setDocumentTitle(documentTitle);

			}
			document.setStatus(1);
			document.setSubclassname(MyClassLoader.getPackPath(document));
			document.setCreatedate(DateUtils.gettimestamp());
			document.setTSType(tsType);
			document.setBusinessKey(bussId);
			UploadFile uploadFile = new UploadFile(request, document);
			uploadFile.setCusPath("files");
			uploadFile.setSwfpath("swfpath");
			Map<String, Object> attributes = new HashMap<String, Object>();
			document = systemService.uploadFile(uploadFile);
			systemService.addLog("APK上传成功", Globals.Log_Leavel_INFO, Globals.Log_Type_UPLOAD);

			String realPath = uploadFile.getMultipartRequest().getSession().getServletContext().getRealPath("/")+"/"+document.getRealpath();
			File savefile = new File(realPath);
			TBMineApkEntity mineApkEntity = systemService.getEntity(TBMineApkEntity.class,bussId);
			if(null!=mineApkEntity){
				double size = ((double)savefile.length()/1024/1024);
				mineApkEntity.setSize((new BigDecimal(size).setScale(2,BigDecimal.ROUND_HALF_UP)).toString());
			}
			this.systemService.saveOrUpdate(mineApkEntity);
		}
		j.setMsg(message);
		return j;
	}
}
