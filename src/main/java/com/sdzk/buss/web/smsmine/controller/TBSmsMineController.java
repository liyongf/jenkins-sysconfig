package com.sdzk.buss.web.smsmine.controller;

import com.sdzk.buss.web.smsmine.entity.TBSmsMineEntity;
import com.sdzk.buss.web.smsmine.service.TBSmsMineServiceI;
import org.apache.log4j.Logger;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.net.URI;
import java.util.List;
import java.util.Set;

/**   
 * @Title: Controller
 * @Description: 短信权限
 * @author zhangdaihao
 * @date 2018-12-20 14:33:47
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/tBSmsMineController")
public class TBSmsMineController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TBSmsMineController.class);

	@Autowired
	private TBSmsMineServiceI tBSmsMineService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 短信权限列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/sdzk/buss/web/smsmine/tBSmsMineList");
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
	public void datagrid(TBSmsMineEntity tBSmsMine,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TBSmsMineEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tBSmsMine, request.getParameterMap());
		this.tBSmsMineService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除短信权限
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TBSmsMineEntity tBSmsMine, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		tBSmsMine = systemService.getEntity(TBSmsMineEntity.class, tBSmsMine.getId());
		message = "短信权限删除成功";
		tBSmsMineService.delete(tBSmsMine);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加短信权限
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TBSmsMineEntity tBSmsMine, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(tBSmsMine.getId())) {
			message = "短信权限更新成功";
			TBSmsMineEntity t = tBSmsMineService.get(TBSmsMineEntity.class, tBSmsMine.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(tBSmsMine, t);
				tBSmsMineService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "短信权限更新失败";
			}
		} else {
			message = "短信权限添加成功";
			tBSmsMineService.save(tBSmsMine);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 短信权限列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TBSmsMineEntity tBSmsMine, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tBSmsMine.getId())) {
			tBSmsMine = tBSmsMineService.getEntity(TBSmsMineEntity.class, tBSmsMine.getId());
			req.setAttribute("tBSmsMinePage", tBSmsMine);
		}
		return new ModelAndView("com/sdzk/buss/web/smsmine/tBSmsMine");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<TBSmsMineEntity> list() {
		List<TBSmsMineEntity> listTBSmsMines=tBSmsMineService.getList(TBSmsMineEntity.class);
		return listTBSmsMines;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		TBSmsMineEntity task = tBSmsMineService.get(TBSmsMineEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody TBSmsMineEntity tBSmsMine, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<TBSmsMineEntity>> failures = validator.validate(tBSmsMine);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		tBSmsMineService.save(tBSmsMine);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = tBSmsMine.getId();
		URI uri = uriBuilder.path("/rest/tBSmsMineController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody TBSmsMineEntity tBSmsMine) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<TBSmsMineEntity>> failures = validator.validate(tBSmsMine);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		tBSmsMineService.saveOrUpdate(tBSmsMine);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		tBSmsMineService.deleteEntityById(TBSmsMineEntity.class, id);
	}
}
