package com.sdzk.buss.web.controller.sysconfig;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzk.buss.web.auth.entity.TBAuthEntity;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.model.common.UploadFile;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.sdzk.buss.web.entity.sysconfig.SysconfigEntity;
import com.sdzk.buss.web.service.sysconfig.SysconfigServiceI;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.jeecgframework.core.beanvalidator.BeanValidators;

import java.util.Map;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.net.URI;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @Title: Controller
 * @Description: 矿井 配置信息
 * @author zhangdaihao
 * @date 2021-01-13 11:43:41
 * @version V1.0
 *
 */
@Controller
@RequestMapping("/sysconfigController")
public class SysconfigController extends BaseController {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(SysconfigController.class);

    @Autowired
    private SysconfigServiceI sysconfigService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;



    /**
     * 矿井 配置信息列表 页面跳转
     *
     * @return
     */
    @RequestMapping(params = "list")
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/sdzk/buss/web/sysconfig/sysconfigList");
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
    public void datagrid(SysconfigEntity sysconfig,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(SysconfigEntity.class, dataGrid);
        //查询条件组装器

        String belongmineinfo1 = request.getParameter("belongmineinfo1");
        if(StringUtil.isNotEmpty(belongmineinfo1)&&!"".equals(belongmineinfo1)){
            cq.like("belongmineinfo","%"+belongmineinfo1+"%");
        }else{
            org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, sysconfig, request.getParameterMap());
        }
        cq.add();
        this.sysconfigService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    /**
     * 删除矿井 配置信息
     *
     * @return
     */
    @RequestMapping(params = "del")
    @ResponseBody
    public AjaxJson del(SysconfigEntity sysconfig, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        sysconfig = systemService.getEntity(SysconfigEntity.class, sysconfig.getId());
        message = "矿井 配置信息删除成功";
        sysconfigService.delete(sysconfig);
        systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);

        j.setMsg(message);
        return j;
    }


    /**
     * 添加矿井 配置信息
     *
     * @param ids
     * @return
     */
    @RequestMapping(params = "save")
    @ResponseBody
    public AjaxJson save(SysconfigEntity sysconfig, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        if (StringUtil.isNotEmpty(sysconfig.getId())) {
            message = "矿井 配置信息更新成功";
            SysconfigEntity t = sysconfigService.get(SysconfigEntity.class, sysconfig.getId());
            try {
                MyBeanUtils.copyBeanNotNull2Bean(sysconfig, t);
                sysconfigService.saveOrUpdate(t);
                systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
            } catch (Exception e) {
                e.printStackTrace();
                message = "矿井 配置信息更新失败";
            }
        } else {
            message = "矿井 配置信息添加成功";
            String belongminename=sysconfig.getBelongminename();
            if(isdeploy(belongminename)){
                sysconfig.setIsdeploy(1);
            }else {
                sysconfig.setIsdeploy(0);
            };
            sysconfigService.save(sysconfig);
            systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        }
        j.setMsg(message);
        return j;
    }

    /**
     * 矿井 配置信息列表页面跳转
     *
     * @return
     */
    @RequestMapping(params = "addorupdate")
    public ModelAndView addorupdate(SysconfigEntity sysconfig, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(sysconfig.getId())) {
            sysconfig = sysconfigService.getEntity(SysconfigEntity.class, sysconfig.getId());
            req.setAttribute("sysconfigPage", sysconfig);
        }
        return new ModelAndView("com/sdzk/buss/web/sysconfig/sysconfig");
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<SysconfigEntity> list() {
        List<SysconfigEntity> listSysconfigs=sysconfigService.getList(SysconfigEntity.class);
        return listSysconfigs;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable("id") String id) {
        SysconfigEntity task = sysconfigService.get(SysconfigEntity.class, id);
        if (task == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(task, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody SysconfigEntity sysconfig, UriComponentsBuilder uriBuilder) {
        //调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
        Set<ConstraintViolation<SysconfigEntity>> failures = validator.validate(sysconfig);
        if (!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        }

        //保存
        sysconfigService.save(sysconfig);

        //按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
        String id = sysconfig.getId();
        URI uri = uriBuilder.path("/rest/sysconfigController/" + id).build().toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody SysconfigEntity sysconfig) {
        //调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
        Set<ConstraintViolation<SysconfigEntity>> failures = validator.validate(sysconfig);
        if (!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        }

        //保存
        sysconfigService.saveOrUpdate(sysconfig);

        //按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        sysconfigService.deleteEntityById(SysconfigEntity.class, id);
    }

    @RequestMapping(params = "download")
    public void download(HttpServletRequest request, HttpServletResponse response, @RequestParam("filename") String filename,
                         Model model) throws Exception {
        String[] names=filename.split("_");
        String dir=names[0]+"_"+names[1];
        String name=null;
        if(names[2].equals("dbconfig")){
            name="dbconfig.properties";
        }else if(names[2].equals("ehcache")){
            name="ehcache.xml";
        }else if(names[2].equals("sysConfig")){
            name="sysConfig.properties";
        }
        // 下载文件路径
        String separator=File.separator;
        String path = "/mnt/datadisk/jenkins/workspace/" + dir + "/config/"+name;
        path=path.replace("/",separator);
        File file = new File(path);

        //File file = new File(path + File.separator + filename);
        filename = dir + "_"+name;
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachement;filename=" + filename);
        //打开源文件流
        InputStream inputStream = new FileInputStream(file);
        //激活下载流
        ServletOutputStream outputStream = response.getOutputStream();
        //复制文件流，将文件流写出
        byte[] buffer = new byte[1024];
        int num = 0;
        while ((num = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, num);
        }
        outputStream.close();
        inputStream.close();
    }
    public boolean isdeploy(String name){
        String separator=File.separator;
        if(name==null|name.length()==0){
            return false;
        }
        String path = "/mnt/datadisk/jenkins/workspace/" + name;
        path=path.replace("/",separator);
        File file = new File(path);
        if(file.exists()){
            return true;
        }else{
            return false;
        }
    }

    @RequestMapping(params = "userinfo")
    @ResponseBody
    public AjaxJson userinfo(HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();

        TSUser user = ResourceUtil.getSessionUserName();

        j.setMsg(user.getUserName());
        return j;
    }

    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(HttpServletRequest req, @RequestParam("id") String id) {
        if (StringUtil.isNotEmpty(id)) {
            SysconfigEntity sysConfig = sysconfigService.getEntity(SysconfigEntity.class,id);
            req.setAttribute("sysConfig", sysConfig);
        }
        return new ModelAndView("com/sdzk/buss/web/sysconfig/upload");
    }

    @RequestMapping(params = "upload", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson doMigrateIn(HttpServletRequest request,
                                HttpServletResponse response) {
        String separator=File.separator;
        String belongminename = request.getParameter("belongminename");
        AjaxJson j = new AjaxJson();
        String ls_file = "";
        UploadFile uploadFile = new UploadFile(request, ls_file);
        uploadFile.setCusPath("");
        uploadFile.setSwfpath("");
		/*String uploadbasepath = uploadFile.getBasePath();// 文件上传根目录
		if (uploadbasepath == null) {
			uploadbasepath = ResourceUtil.getConfigByName("uploadpath");
		}
		String path = uploadbasepath + "\\";// 文件保存在硬盘的相对路径
		String realPath = uploadFile.getMultipartRequest().getSession()
				.getServletContext().getRealPath("\\")
				+ path;// 文件的硬盘真实路径*/
        String path = "/mnt/datadisk/jenkins/workspace/" + belongminename + "/config/";
        path=path.replace("/",separator);
        try {
            File file = new File(path);
            if (!file.exists()) {
                j.setMsg("项目未自动化部署");
                return j;
            }
            uploadFile.getMultipartRequest().setCharacterEncoding("UTF-8");
            MultipartHttpServletRequest multipartRequest = uploadFile
                    .getMultipartRequest();
            Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
            String fileName = "";
            for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
                MultipartFile mf = entity.getValue();// 获取上传文件对象
                fileName = mf.getOriginalFilename();// 获取文件名
                String[] names=fileName.split("_");
                if(!(names.length==3)){
                    throw new Exception("上传文件名错误");
                }
                String dir=names[0]+"_"+names[1];
                if(!belongminename.equals(dir)){
                    throw new Exception("上传文件名与已选择项目不同");
                }
                if(!(names[2].equals("dbconfig.properties")||names[2].equals("ehcache.xml")||names[2].equals("sysConfig.properties"))){
                    throw new Exception("上传文件名错误");
                }
                String savePath = path + names[2];
                File savefile = new File(savePath);
                if (!savefile.exists()) {
                    j.setMsg("项目未自动化部署");
                    /*return j;*/
                }
                try {
                    FileCopyUtils.copy(mf.getBytes(), savefile);
                    j.setMsg("文件导入成功");
                } catch (IOException e) {
                    j.setMsg("文件复制异常");
                }
            }
        }catch(Exception e){
            if(e.getMessage()==null||e.getMessage().length()<=0){
                j.setMsg("文件导入失败");
            }else{
                j.setMsg(e.getMessage());
            }
        }
        return j;
    }
}
