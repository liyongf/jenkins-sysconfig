package com.sdzk.buss.web.tbmapmanage.service;

import com.sdzk.buss.web.tbmapmanage.entity.TBMapManageEntity;
import org.jeecgframework.core.common.service.CommonService;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Map;

public interface TBMapManageServiceI extends CommonService{
	
 	public void delete(TBMapManageEntity entity) throws Exception;
 	
 	public Serializable save(TBMapManageEntity entity) throws Exception;
 	
 	public void saveOrUpdate(TBMapManageEntity entity) throws Exception;

 	public Map<String,String> receive(HttpServletRequest request) throws Exception;

}
