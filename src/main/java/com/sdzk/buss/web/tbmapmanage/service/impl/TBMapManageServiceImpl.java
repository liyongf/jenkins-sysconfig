package com.sdzk.buss.web.tbmapmanage.service.impl;

import com.sdzk.buss.web.tbmapmanage.entity.TBMapManageEntity;
import com.sdzk.buss.web.tbmapmanage.service.TBMapManageServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service("tBMapManageService")
@Transactional
public class TBMapManageServiceImpl extends CommonServiceImpl implements TBMapManageServiceI {

	
 	public void delete(TBMapManageEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(TBMapManageEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(TBMapManageEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(TBMapManageEntity t) throws Exception{
		//-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(TBMapManageEntity t) throws Exception{
		//-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	/**
	 * 删除操作增强业务
	 * @param
	 * @return
	 */
	private void doDelBus(TBMapManageEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(TBMapManageEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("upload_name", t.getUploadName());
		map.put("upload_by", t.getUploadBy());
		map.put("upload_date", t.getUploadDate());
		map.put("file_path", t.getFilePath());
		map.put("is_used", t.getIsUsed());
		map.put("is_delete", t.getIsDelete());
		map.put("upload_type", t.getUploadType());
		map.put("status", t.getStatus());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,TBMapManageEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{upload_name}",String.valueOf(t.getUploadName()));
 		sql  = sql.replace("#{upload_by}",String.valueOf(t.getUploadBy()));
 		sql  = sql.replace("#{upload_date}",String.valueOf(t.getUploadDate()));
 		sql  = sql.replace("#{file_path}",String.valueOf(t.getFilePath()));
 		sql  = sql.replace("#{is_used}",String.valueOf(t.getIsUsed()));
 		sql  = sql.replace("#{is_delete}",String.valueOf(t.getIsDelete()));
 		sql  = sql.replace("#{upload_type}",String.valueOf(t.getUploadType()));
 		sql  = sql.replace("#{status}",String.valueOf(t.getStatus()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
 	
 	/**
	 * 执行JAVA增强
	 */
 	private void executeJavaExtend(String cgJavaType,String cgJavaValue,Map<String,Object> data) throws Exception {
 		if(StringUtil.isNotEmpty(cgJavaValue)){
			Object obj = null;
			try {
				if("class".equals(cgJavaType)){
					//因新增时已经校验了实例化是否可以成功，所以这块就不需要再做一次判断
					obj = MyClassLoader.getClassByScn(cgJavaValue).newInstance();
				}else if("spring".equals(cgJavaType)){
					obj = ApplicationContextUtil.getContext().getBean(cgJavaValue);
				}
				if(obj instanceof CgformEnhanceJavaInter){
					CgformEnhanceJavaInter javaInter = (CgformEnhanceJavaInter) obj;
					javaInter.execute("t_b_map_manage",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}

	public Map<String,String> receive(HttpServletRequest request) throws Exception {
		String filePath = "";

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		String departName = multipartRequest.getParameter("departName");

		String mapId = multipartRequest.getParameter("mapId");

		String ipAddress = multipartRequest.getParameter("ipAddress");
		String port = multipartRequest.getParameter("port");
		String projectName = multipartRequest.getParameter("projectName");

		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile multipartFile = entity.getValue();// 获取上传文件对象
			try {
				String filename = multipartFile.getOriginalFilename();
				String savePath = request.getSession().getServletContext().getRealPath("/plug-in/baidumap/");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String folderName = sdf.format(new Date());
				savePath = savePath + "/" + folderName + "/";
				new File(savePath).mkdir(); //创建保存目录
				InputStream is = multipartFile.getInputStream();
				FileOutputStream fos = new FileOutputStream(savePath+ "/" + filename);
				//创建一个缓冲区
				byte buffer[] = new byte[1024];
				//判断输入流中的数据是否已经读完的标识
				int len = 0;
				//循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
				while((len=is.read(buffer))>0){
					//使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
					fos.write(buffer, 0, len);
				}
				//关闭输入流
				is.close();
				//关闭输出流
				fos.close();
				filePath = "/plug-in/baidumap/" + folderName + "/";
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Map<String,String> sendData = new HashMap<>();
		sendData.put("departName",departName);
		sendData.put("filePath",filePath);
		sendData.put("mapId",mapId);
		sendData.put("ipAddress",ipAddress);
		sendData.put("port",port);
		sendData.put("projectName",projectName);
		return sendData;
	}
}