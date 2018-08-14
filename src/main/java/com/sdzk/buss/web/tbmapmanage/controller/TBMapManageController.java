package com.sdzk.buss.web.tbmapmanage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzk.buss.web.tbmapmanage.entity.TBMapManageEntity;
import com.sdzk.buss.web.tbmapmanage.service.TBMapManageServiceI;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;
import javax.validation.Validator;
import java.util.Map;


/**   
 * @Title: Controller  
 * @Description: 矿图管理
 * @author onlineGenerator
 * @date 2018-08-06 16:21:02
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/tBMapManageController")
public class TBMapManageController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TBMapManageController.class);

	@Autowired
	private TBMapManageServiceI tBMapManageService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;


	@RequestMapping(params = "receive")
	public AjaxJson receive(TBMapManageEntity mapManage,HttpServletRequest request) throws Exception {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "矿图接受成功";

		try{
			Map<String,String> sendData = tBMapManageService.receive(request);

			String uploadType = "dwg";

			mapManage = new TBMapManageEntity(sendData.get("mapId"),sendData.get("departName"),sendData.get("filePath"),
					sendData.get("ipAddress"),sendData.get("port"),sendData.get("projectName"),uploadType);

			tBMapManageService.save(mapManage);
			systemService.addLog(message,Globals.Log_Type_UPLOAD,Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "矿图接收失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 矿图管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/sdzk/buss/web/tbmapmanage/tBMapManageList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(TBMapManageEntity tBMapManage, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TBMapManageEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tBMapManage, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.tBMapManageService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	

	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(TBMapManageEntity mapManage,HttpServletRequest request){
		String message = "设置成功";
		AjaxJson j = new AjaxJson();

		String id = request.getParameter("id");
		String status = request.getParameter("status");

		if(StringUtils.isNotBlank(id)){
			try{
				mapManage = tBMapManageService.getEntity(TBMapManageEntity.class,id);
				if(mapManage != null){
					mapManage.setStatus(status);
					tBMapManageService.saveOrUpdate(mapManage);
				}
			}catch (Exception e){
				e.printStackTrace();
				message = "设置失败";
			}
		}

		j.setMsg(message);
		return j;
	}

}
