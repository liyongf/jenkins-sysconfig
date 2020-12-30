package org.jeecgframework.web.system.controller.core;

import com.sdzk.buss.web.common.excelverify.IpSeverConfigExcelVerifyHandler;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
import org.jeecgframework.poi.excel.entity.vo.TemplateExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.web.system.pojo.base.IpServerConfig;
import org.jeecgframework.web.system.pojo.base.IpServerLog;
import org.jeecgframework.web.system.service.ServerLogServiceI;
import org.jeecgframework.web.system.service.ServerWordServiceI;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName:
 * @Description: TODO(用户管理处理类)
 *
 */
//@Scope("prototype")
@Controller
@RequestMapping("/serverWordController")
public class ServerWordController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ServerWordController.class);
    private static final String defaultPassword = "123456";
	private SystemService systemService;

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	@Autowired
	private ServerWordServiceI serverWordService;

	@Autowired
	private ServerLogServiceI serverLogService;
	/**
	 * 用户列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "ipServerList")
	public ModelAndView ipServerList(HttpServletRequest request) {
		// 给部门查询条件中的下拉框准备数据
		/*List<IpServerConfig> departList = systemService.getList(IpServerConfig.class);
		request.setAttribute("departsReplace", RoletoJson.listToReplaceStr(departList, "departname", "id"));
		departList.clear();
		return "com/sdzk/buss/web/ipServer/ipServerList";*/
		return new ModelAndView("com/sdzk/buss/web/ipServer/ipServerList");
	}

	/**
	 * easyuiAJAX用户列表请求数据 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "datagrid")
	public void datagrid(IpServerConfig ipServerConfig, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(IpServerConfig.class, dataGrid);
		//查询条件组装器
		//org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, ipServerConfig, request.getParameterMap());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String beginDate = request.getParameter("limitDate_begin");
		String endDate = request.getParameter("limitDate_end");
		String publicIp= request.getParameter("publicIp");
		String privateIp= request.getParameter("privateIp");
		/*String queryHandleStatus = request.getParameter("queryHandleStatus");
		*/
		try{
			//自定义追加查询条件
			if(StringUtils.isNotBlank(publicIp)){
				cq.eq("publicIp",publicIp);
			}
			if(StringUtils.isNotBlank(privateIp)){
				cq.eq("privateIp",privateIp);
			}
			if (StringUtils.isNotBlank(beginDate)) {
				cq.ge("limitDate", sdf.parse(beginDate+ " 00:00:00"));
			}
			if (StringUtils.isNotBlank(endDate)) {
				cq.le("limitDate", sdf.parse(endDate+" 23:59:59"));
			}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}

		cq.add();
		this.serverWordService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);


    }


	/**
	 * easyuiAJAX请求数据： 用户选择角色列表
	 *
	 * @param
	 * @param req
	 * @param
	 */
	@RequestMapping(params = "addIp")
	public ModelAndView addIp(IpServerConfig ipServerConfig, HttpServletRequest req) {

		if (StringUtil.isNotEmpty(ipServerConfig.getId())) {
			//tBThreeViolations = tBThreeViolationsService.getEntity(TBThreeViolationsEntity.class, tBThreeViolations.getId());
			req.setAttribute("ipServerConfig", ipServerConfig);
		}

		return new ModelAndView("com/sdzk/buss/web/ipServer/ipContent-add");
	}

	/**
	 * easyuiAJAX请求数据： 用户选择角色列表
	 *
	 * @param
	 * @param req
	 * @param
	 */
	@RequestMapping(params = "goSend")
	public ModelAndView goSend(IpServerConfig ipServerConfig, HttpServletRequest req) {

		if (StringUtil.isNotEmpty(ipServerConfig.getId())) {
			req.setAttribute("ipServerConfig", ipServerConfig);
		}

		return new ModelAndView("com/sdzk/buss/web/ipServer/ipContent-send");
	}


	/**
	 * 添加ip信息
	 *
	 * @param
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(IpServerConfig ipServerConfig, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "添加成功";
		CriteriaQuery cq = new CriteriaQuery(IpServerConfig.class);
		cq.eq("privateIp",ipServerConfig.getPrivateIp());
		cq.add();
		List<IpServerConfig> ipServerConfigs = this.serverWordService.getListByCriteriaQuery(cq,false);
		if(null != ipServerConfigs && ipServerConfigs.size()>0){
			message="私有IP地址已存在，请不要重复添加！";
		}else{
			try{
				serverWordService.save(ipServerConfig);
				systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			}catch(Exception e){
				e.printStackTrace();
				message = "添加失败";
				throw new BusinessException(e.getMessage());
			}
		}
		j.setMsg(message);
		return j;
	}



	/**
	 * 添加ip信息
	 *
	 * @param
	 * @return
	 */
	@RequestMapping(params = "saveReceviceMan")
	@ResponseBody
	public AjaxJson saveReceviceMan(IpServerConfig ipServerConfig, HttpServletRequest request) {
		IpServerConfig config = this.serverWordService.get(IpServerConfig.class,ipServerConfig.getId());
		config.setReceviceMan(ipServerConfig.getReceviceMan());
		serverWordService.updateEntitie(config);
		ipServerConfig = serverWordService.getEntity(IpServerConfig.class, ipServerConfig.getId());
		CriteriaQuery cq = new CriteriaQuery(IpServerLog.class);
		cq.eq("serverId",ipServerConfig.getId());
		cq.eq("passWord",ipServerConfig.getIpWord());
		cq.add();
		List<IpServerLog> ipServerLogList = this.serverLogService.getListByCriteriaQuery(cq,false);
		if (null != ipServerLogList && ipServerLogList.size()>0){
			for (IpServerLog log :ipServerLogList) {
				log.setReceviceMan(ipServerConfig.getReceviceMan());
				this.serverLogService.updateEntitie(log);
			}
		}


		String message = "下发成功";
		AjaxJson j = new AjaxJson();
		j.setMsg(message);
		return j;
	}

	/**
	 * 变更在线用户组织
	 *
	 * @param
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/updatPassWord",method=RequestMethod.GET)
	@ResponseBody
	public AjaxJson updatPassWord(HttpServletRequest req) throws Exception {
		AjaxJson j = new AjaxJson();
		Map<String, Object> attrMap = new HashMap<String, Object>();
		String privateIp = req.getParameter("ip");
		String passWord = req.getParameter("passWord");
		CriteriaQuery cq = new CriteriaQuery(IpServerConfig.class);

		cq.eq("privateIp",privateIp);
		cq.add();
		List<IpServerConfig> ipServerConfigs = this.serverWordService.getListByCriteriaQuery(cq,false);
		List<IpServerLog> ipServerLogList = new ArrayList<>();
		if (null != ipServerConfigs && ipServerConfigs.size()>0){
			for (IpServerConfig config :ipServerConfigs) {
				IpServerLog ipServerLog = new IpServerLog();
				ipServerLog.setPassWord(passWord);
				ipServerLog.setServerId(config.getId());
				ipServerLog.setPrivateIp(config.getPrivateIp());
				ipServerLog.setCreateDate(new Date());
				ipServerLogList.add(ipServerLog);
				config.setIpWord(passWord);
				config.setReceviceMan(null);
				config.setUpdateDate(new Date());
				this.serverWordService.updateEntitie(config);
				this.serverLogService.save(ipServerLog);
			}
		}

		return j;
	}
	@RequestMapping(params = "goDetailList")
	public ModelAndView goDetailList(HttpServletRequest request) {
		String id = request.getParameter("id");
		request.setAttribute("serverId",id);
		return new ModelAndView("com/sdzk/buss/web/ipServer/goDetailList");
	}

	@RequestMapping(params = "datagridList")
	public void datagridList(IpServerLog ipServerLog,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(IpServerLog.class, dataGrid);
		//查询条件组装器
		String serverId = request.getParameter("serverId");

		try{
			if(StringUtils.isNotBlank(serverId)){
				cq.eq("serverId", serverId);
			}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.addOrder("createDate", SortDirection.desc);
		cq.add();
		this.serverLogService.getDataGridReturn(cq, true);

		TagUtil.datagrid(response, dataGrid);
	}


	/**
	 * 导出excel 使模板
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(HttpServletRequest request,HttpServletResponse response ,ModelMap modelMap) {
		modelMap.put(TemplateExcelConstants.FILE_NAME,"服务器信息导入模板");
		TemplateExportParams templateExportParams = new TemplateExportParams();
		templateExportParams.setSheetNum(0);
		templateExportParams.setTemplateUrl("export/template/exportTemp_ipServerConfig.xls");
		modelMap.put(TemplateExcelConstants.PARAMS,templateExportParams);
		Map<String, Object> param =new HashMap<String, Object>();
		modelMap.put(TemplateExcelConstants.MAP_DATA,param);
		return TemplateExcelConstants.JEECG_TEMPLATE_EXCEL_VIEW;
	}

	/**
	 * 导入功能跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","serverWordController");
		return new ModelAndView("common/upload/pub_excel_upload");
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
			params.setTitleRows(1);
			params.setHeadRows(1);
			params.setNeedSave(true);
			params.setVerifyHanlder(new IpSeverConfigExcelVerifyHandler());
			try {
				ExcelImportResult<IpServerConfig> result  = ExcelImportUtil.importExcelVerify(file.getInputStream(),IpServerConfig.class,params);
				if(result.isVerfiyFail()){
					String uploadpathtemp = ResourceUtil.getConfigByName("uploadpathtemp");
					String realPath = multipartRequest.getSession().getServletContext().getRealPath("/") + "/" + uploadpathtemp+"/";// 文件的硬盘真实路径
					File fileTemp = new File(realPath);
					if (!fileTemp.exists()) {
						fileTemp.mkdirs();// 创建根目录
					}
					String name = DateUtils.getDataString(DateUtils.yyyymmddhhmmss)+".xls";
					realPath+=name;
					FileOutputStream fos = new FileOutputStream(realPath);
					result.getWorkbook().write(fos);
					fos.close();
					Map<String, Object> attributes = new HashMap<String, Object>();
					attributes.put("path", uploadpathtemp+"/"+name);
					j.setAttributes(attributes);
					j.setSuccess(false);
					j.setMsg("导入数据校验失败");
				}else{
					for (IpServerConfig bean : result.getList()) {
						serverWordService.save(bean);
					}
					j.setMsg("文件导入成功！");
				}
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


	/**
	 * 用户列表页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "personalIpList")
	public ModelAndView personalIpList(HttpServletRequest request) {
		// 给部门查询条件中的下拉框准备数据
		/*List<IpServerConfig> departList = systemService.getList(IpServerConfig.class);
		request.setAttribute("departsReplace", RoletoJson.listToReplaceStr(departList, "departname", "id"));
		departList.clear();
		return "com/sdzk/buss/web/ipServer/ipServerList";*/
		return new ModelAndView("com/sdzk/buss/web/ipServer/personalIpList");
	}


	/**
	 * easyuiAJAX用户列表请求数据
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "personalDatagrid")
	public void personalDatagrid(IpServerConfig ipServerConfig, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {

		String user = ResourceUtil.getSessionUserName().getRealName();

		CriteriaQuery cq = new CriteriaQuery(IpServerConfig.class, dataGrid);
		//查询条件组装器
		//org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, ipServerConfig, request.getParameterMap());
		/*String queryHandleStatus = request.getParameter("queryHandleStatus");
		*/
		String publicIp= request.getParameter("publicIp");
		String privateIp= request.getParameter("privateIp");
		try{
			//自定义追加查询条件
			if(StringUtils.isNotBlank(user)){
				cq.eq("receviceMan",user);
			}
			if(StringUtils.isNotBlank(publicIp)){
				cq.eq("publicIp",publicIp);
			}
			if(StringUtils.isNotBlank(privateIp)){
				cq.eq("privateIp",privateIp);
			}

		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}

		cq.add();
		this.serverWordService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);


	}

}