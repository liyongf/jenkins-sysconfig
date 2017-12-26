package com.sdzk.buss.web.calculate.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzk.buss.api.model.ApiResultJson;
import com.sdzk.buss.web.common.Constants;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.jeecgframework.core.util.DicUtil;
import org.jeecgframework.core.util.JSONHelper;
import org.jeecgframework.p3.core.utils.common.StringUtils;
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
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.sdzk.buss.web.calculate.entity.TBCalculateEntity;
import com.sdzk.buss.web.calculate.service.TBCalculateServiceI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
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

/**   
 * @Title: Controller
 * @Description: 统计信息
 * @author zhangdaihao
 * @date 2017-12-21 15:11:20
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/tBCalculateController")
public class TBCalculateController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TBCalculateController.class);

	@Autowired
	private TBCalculateServiceI tBCalculateService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;

	


	/**
	 * 统计信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/sdzk/buss/web/calculate/tBCalculateList");
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
	public void datagrid(TBCalculateEntity tBCalculate,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        String mineName = tBCalculate.getMineName();
        tBCalculate.setMineName(null);
		CriteriaQuery cq = new CriteriaQuery(TBCalculateEntity.class, dataGrid);
        if (StringUtil.isNotEmpty(mineName)) {
            cq.like("mineName","%"+mineName+"%");
        }
        cq.add();
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tBCalculate, request.getParameterMap());
        this.tBCalculateService.getDataGridReturn(cq, true);
        if(dataGrid != null && dataGrid.getResults() != null && dataGrid.getResults().size() > 0) {
            List<TBCalculateEntity> tempList = dataGrid.getResults();
            for(TBCalculateEntity bean : tempList){
                bean.setMineName(DicUtil.getTypeNameByCode("mineCode",bean.getMinecode()));
            }
        }
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除统计信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TBCalculateEntity tBCalculate, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		tBCalculate = systemService.getEntity(TBCalculateEntity.class, tBCalculate.getId());
		message = "统计信息删除成功";
		tBCalculateService.delete(tBCalculate);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


    /**
     * 接口接收统计信息
     */
    @RequestMapping("/reportSumNumAll.do")
    @ResponseBody
    public ApiResultJson addressDangerRelReport(String mineCode, String reportContent) {
        if (StringUtil.isEmpty(mineCode)|| StringUtil.isEmpty(reportContent)) {
            return new ApiResultJson(ApiResultJson.CODE_400, ApiResultJson.CODE_400_MSG, null);
        }

        try {
            JSONArray ja = JSONHelper.toJSONArray(reportContent);
            if (ja != null && ja.size() > 0) {
                try {
                    for (int i = 0; i < ja.size(); i++) {
                        JSONObject jo = ja.optJSONObject(i);
                        if (jo == null) {
                            continue;
                        }
                       String sumDangerSource =  jo.optString("sumDangerSource");
                       String sumHiddenDanger = jo.optString("sumHiddenDanger");
                       String sumThreeViolations = jo.optString("sumThreeViolations");
                       String sumMajorHiddenDanger= jo.optString("sumMajorHiddenDanger");
                       TBCalculateEntity temp = new TBCalculateEntity();
                        temp.setMinecode(mineCode);
                        temp.setMineName(DicUtil.getTypeNameByCode("mineCode",mineCode));
                        temp.setNumDangerSource(sumDangerSource);
                        temp.setNumHiddenDanger(sumHiddenDanger);
                        temp.setNumThreeViolations(sumThreeViolations);
                        temp.setNumMajorHiddenDanger(sumMajorHiddenDanger);
                        List<TBCalculateEntity> tempList = systemService.findByProperty(TBCalculateEntity.class,"minecode",mineCode);
                        if(tempList.size()>0&&!tempList.isEmpty()){
                            TBCalculateEntity t = tempList.get(0);
                            MyBeanUtils.copyBeanNotNull2Bean(temp, t);
                            systemService.saveOrUpdate(t);
                        }else{
                            systemService.save(temp);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            logger.error(e);
            return new ApiResultJson(ApiResultJson.CODE_500,ApiResultJson.CODE_500_MSG,null);
        }
       return new ApiResultJson(ApiResultJson.CODE_200,ApiResultJson.CODE_200_MSG,null);
    }


	/**
	 * 添加统计信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TBCalculateEntity tBCalculate, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(tBCalculate.getId())) {
			message = "统计信息更新成功";
			TBCalculateEntity t = tBCalculateService.get(TBCalculateEntity.class, tBCalculate.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(tBCalculate, t);
				tBCalculateService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "统计信息更新失败";
			}
		} else {
			message = "统计信息添加成功";
			tBCalculateService.save(tBCalculate);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}



	/**
	 * 统计信息列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TBCalculateEntity tBCalculate, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tBCalculate.getId())) {
			tBCalculate = tBCalculateService.getEntity(TBCalculateEntity.class, tBCalculate.getId());
			req.setAttribute("tBCalculatePage", tBCalculate);
		}
		return new ModelAndView("com/sdzk/buss/web/calculate/tBCalculate");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<TBCalculateEntity> list() {
		List<TBCalculateEntity> listTBCalculates=tBCalculateService.getList(TBCalculateEntity.class);
		return listTBCalculates;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		TBCalculateEntity task = tBCalculateService.get(TBCalculateEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody TBCalculateEntity tBCalculate, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<TBCalculateEntity>> failures = validator.validate(tBCalculate);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		tBCalculateService.save(tBCalculate);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = tBCalculate.getId();
		URI uri = uriBuilder.path("/rest/tBCalculateController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody TBCalculateEntity tBCalculate) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<TBCalculateEntity>> failures = validator.validate(tBCalculate);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		tBCalculateService.saveOrUpdate(tBCalculate);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		tBCalculateService.deleteEntityById(TBCalculateEntity.class, id);
	}
}
