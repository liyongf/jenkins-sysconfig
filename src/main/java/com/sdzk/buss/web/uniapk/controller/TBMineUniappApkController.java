package com.sdzk.buss.web.uniapk.controller;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.WriterException;
import com.sdzk.buss.web.common.Constants;
import com.sdzk.buss.web.mineapk.entity.TBMineApkEntity;
import com.sdzk.buss.web.mineorg.entity.TBMineOrgEntity;
import com.sdzk.buss.web.uniapk.entity.TBMineUniappApkEntity;
import com.sdzk.buss.web.uniapk.service.TBMineUniappApkServiceI;
import com.sdzk.buss.web.uniapk.util.QRCodeGenerator;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.model.common.UploadFile;
import org.jeecgframework.core.util.*;
import org.jeecgframework.web.system.pojo.base.TSDocument;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.pojo.base.TSTypegroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.jeecgframework.core.beanvalidator.BeanValidators;

import java.util.Map;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.net.URI;
import org.springframework.http.MediaType;
import org.jeecgframework.core.common.exception.BusinessException;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @Title: Controller
 * @Description: uniapp 版本控制
 * @author zhangdaihao
 * @date 2021-01-18 15:31:50
 * @version V1.0
 *
 */
@Controller
@RequestMapping("/tBMineUniappApkController")
public class TBMineUniappApkController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TBMineUniappApkController.class);

	@Autowired
	private TBMineUniappApkServiceI tBMineUniappApkService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;



	/**
	 * 矿井APK配置新增页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "QrCodeGenerete")
	public ModelAndView QrCodeGenerete(TBMineUniappApkEntity uniappApkEntity, HttpServletRequest req) throws IOException, WriterException {

		if (StringUtil.isNotEmpty(uniappApkEntity.getMineId())) {
			uniappApkEntity.setIsCurrentVersion("1");
			CriteriaQuery cq = new CriteriaQuery(TBMineUniappApkEntity.class);
			cq.eq("mineId",uniappApkEntity.getMineId());
			cq.eq("isCurrentVersion",uniappApkEntity.getIsCurrentVersion());
			cq.add();
			List<TBMineUniappApkEntity>  telist =  systemService.getListByCriteriaQuery(cq, false);
			QRCodeGenerator qr = new QRCodeGenerator();
			String basePath = req.getScheme()+"://"+req.getServerName()+":"+
					req.getServerPort()+req.getContextPath()+"/";
			String tpath = basePath+ telist.get(0).getUrl();
			String newStr = tpath.substring(tpath.indexOf("&"),tpath.length());
			String  testp = basePath+"tBMineUniappApkController.do?QrCodeWeixin&apkUrl="+tpath;
			req.setAttribute("testp", testp);
			req.setAttribute("newStr", newStr);
		}

		return new ModelAndView("com/sdzk/buss/web/uniapk/QrCode");
	}
	@RequestMapping(params = "QrCodeWeixin")
	public ModelAndView QrCodeWeixin(TBMineUniappApkEntity uniappApkEntity, HttpServletRequest req) throws IOException, WriterException {
		String wp = (String)req.getParameter("apkUrl");
		String field = (String)req.getParameter("fileid");
		req.setAttribute("wp", wp+"&fileid="+field);
		return new ModelAndView("com/sdzk/buss/web/uniapk/QrCodeWeixin");
	}

	/**
	 * uniapp 版本控制列表 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/sdzk/buss/web/uniapk/tBMineUniList");
	}

	@RequestMapping(params = "uniList")
	public ModelAndView uniList(HttpServletRequest request) {
		String mineId = request.getParameter("mineId");
		request.setAttribute("mineId",mineId);
		return new ModelAndView("com/sdzk/buss/web/uniapk/tBMineUniappApkList");
	}
	/**
	 * 矿井APK配置新增页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(TBMineUniappApkEntity uniappApkEntity, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(uniappApkEntity.getId())) {
			uniappApkEntity = tBMineUniappApkService.getEntity(TBMineUniappApkEntity.class, uniappApkEntity.getId());
		}
		req.setAttribute("tBMineUniPage", uniappApkEntity);
		return new ModelAndView("com/sdzk/buss/web/uniapk/tBMineUniApp-add");
	}


	@RequestMapping(params = "uploadMyApk")
	@ResponseBody
	public AjaxJson uploadMyApk( HttpServletRequest request,TSDocument document) {
		String message = "添加成功";
		// 文件标题
		String documentTitle = oConvertUtils.getString(request.getParameter("documentTitle"));
		AjaxJson j = new AjaxJson();

		String bussId = request.getParameter("bussId");
		TSTypegroup tsTypegroup=systemService.getTypeGroup("fieltype","文档分类");
		TSType tsType = systemService.getType("emergencyFile","附件", tsTypegroup);
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
			Map<String, Object> attributes = new HashMap<String, Object>();
			document = systemService.uploadFile(uploadFile);
			systemService.addLog("文件上传成功",Globals.Log_Leavel_INFO,Globals.Log_Type_UPLOAD);
			attributes.put("url", document.getRealpath());
			attributes.put("fileKey", document.getId());
			attributes.put("name", document.getAttachmenttitle());
			attributes.put("viewhref", "commonController.do?objfileList&fileKey=" + document.getId());
			attributes.put("delurl", "commonController.do?delObjFile&fileKey=" + document.getId());
			j.setAttributes(attributes);
			String realPath = uploadFile.getMultipartRequest().getSession().getServletContext().getRealPath("/")+"/"+document.getRealpath();
			File savefile = new File(realPath);

			TBMineUniappApkEntity uniApkEntity = systemService.getEntity(TBMineUniappApkEntity.class,bussId);
			if(null!=uniApkEntity){
				double size = ((double)savefile.length()/1024/1024);
				uniApkEntity.setSize((new BigDecimal(size).setScale(2,BigDecimal.ROUND_HALF_UP)).toString());
				uniApkEntity.setUrl("commonController.do?viewFile&fileid="+document.getId());
			}
			this.systemService.saveOrUpdate(uniApkEntity);
		}

		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除uniapp配置
	 *
	 * @return
	 */
	@RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "删除成功";
		try{
			for(String id:ids.split(",")){
				TBMineUniappApkEntity tBMineUniApk = systemService.getEntity(TBMineUniappApkEntity.class,
						id
				);
				tBMineUniappApkService.delete(tBMineUniApk);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);

				//删除关联的apk
				CriteriaQuery cq = new CriteriaQuery(TSDocument.class);
				cq.eq("businessKey",id);
				cq.add();
				List<TSDocument> documentList = this.tBMineUniappApkService.getListByCriteriaQuery(cq,false);
				if(null!=documentList && documentList.size()>0){
					for(TSDocument doc : documentList){
						doc.setStatus(0);
					}
					this.systemService.batchSave(documentList);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}



	/**
	 * easyui AJAX请求数据
	 *
	 * @param request
	 * @param response
	 * @param dataGrid
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(TBMineUniappApkEntity tBMineUniappApk,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TBMineUniappApkEntity.class, dataGrid);
		tBMineUniappApk.setVersionName(null);
		tBMineUniappApk.setUpdateContent(null);
//		tBMineApk.setMineName(null);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tBMineUniappApk, request.getParameterMap());
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
		this.tBMineUniappApkService.getDataGridReturn(cq, true);
		if(dataGrid != null && dataGrid.getResults() != null){
			List<TBMineUniappApkEntity> list = dataGrid.getResults();
			for(TBMineUniappApkEntity t : list){
				if(StringUtil.isNotEmpty(t.getMineId())){
					TBMineOrgEntity mineOrgEntity = systemService.getEntity(TBMineOrgEntity.class,t.getMineId());
					if(null!=mineOrgEntity){
						t.setMineName(mineOrgEntity.getName());
					}
				}

				CriteriaQuery cqDoc = new CriteriaQuery(TSDocument.class);
				cqDoc.eq("businessKey",t.getId());
				cqDoc.add();
				List<TSDocument> docList = this.tBMineUniappApkService.getListByCriteriaQuery(cqDoc,false);
				if(null!=docList && docList.size()>0){
					t.setDocumentId(docList.get(0).getId());
				}
			}

		}
		TagUtil.datagrid(response, dataGrid);
	}
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(TBMineUniappApkEntity tBUniApk, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "版本配置添加成功";
		try{
			if(Constants.YES.equals(tBUniApk.getIsCurrentVersion())){
				if(StringUtil.isNotEmpty(tBUniApk.getMineId())) {
					systemService.executeSql("update t_b_mine_uniapp_apk set is_current_version='0' where mine_id='" + tBUniApk.getMineId() + "'");
					systemService.executeSql("update t_b_mine_org set uniapp_version='"+tBUniApk.getVersionName()+"' where id='" + tBUniApk.getMineId() + "'");
				} else {
					systemService.executeSql("update t_b_mine_uniapp_apk set is_current_version='0' where mine_id is null");
				}
			}
			tBMineUniappApkService.save(tBUniApk);
			String id = tBUniApk.getId();
			Map<String,Object> retMap = new HashMap<String,Object>();
			retMap.put("id",id);
			j.setAttributes(retMap);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "版本配置添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 删除uniapp 版本控制
	 *
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TBMineUniappApkEntity tBMineUniappApk, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		tBMineUniappApk = systemService.getEntity(TBMineUniappApkEntity.class, tBMineUniappApk.getId());
		message = "uniapp 版本控制删除成功";
		tBMineUniappApkService.delete(tBMineUniappApk);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加uniapp 版本控制
	 *
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TBMineUniappApkEntity tBMineUniappApk, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(tBMineUniappApk.getId())) {
			message = "uniapp 版本控制更新成功";
			TBMineUniappApkEntity t = tBMineUniappApkService.get(TBMineUniappApkEntity.class, tBMineUniappApk.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(tBMineUniappApk, t);
				tBMineUniappApkService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "uniapp 版本控制更新失败";
			}
		} else {
			message = "uniapp 版本控制添加成功";
			tBMineUniappApkService.save(tBMineUniappApk);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * uniapp 版本控制列表页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TBMineUniappApkEntity tBMineUniappApk, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tBMineUniappApk.getId())) {
			tBMineUniappApk = tBMineUniappApkService.getEntity(TBMineUniappApkEntity.class, tBMineUniappApk.getId());
			req.setAttribute("tBMineUniappApkPage", tBMineUniappApk);
		}
		return new ModelAndView("com/sdzk/buss/web/uniapk/tBMineUniappApk");
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<TBMineUniappApkEntity> list() {
		List<TBMineUniappApkEntity> listTBMineUniappApks=tBMineUniappApkService.getList(TBMineUniappApkEntity.class);
		return listTBMineUniappApks;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		TBMineUniappApkEntity task = tBMineUniappApkService.get(TBMineUniappApkEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody TBMineUniappApkEntity tBMineUniappApk, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<TBMineUniappApkEntity>> failures = validator.validate(tBMineUniappApk);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		tBMineUniappApkService.save(tBMineUniappApk);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = tBMineUniappApk.getId();
		URI uri = uriBuilder.path("/rest/tBMineUniappApkController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody TBMineUniappApkEntity tBMineUniappApk) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<TBMineUniappApkEntity>> failures = validator.validate(tBMineUniappApk);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		tBMineUniappApkService.saveOrUpdate(tBMineUniappApk);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		tBMineUniappApkService.deleteEntityById(TBMineUniappApkEntity.class, id);
	}
}
