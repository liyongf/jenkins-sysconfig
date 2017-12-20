package com.sdzk.buss.web.auth.service;

import com.sdzk.buss.web.auth.entity.TBAuthEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface TBAuthServiceI extends CommonService{
	
 	public void delete(TBAuthEntity entity) throws Exception;
 	
 	public Serializable save(TBAuthEntity entity) throws Exception;
 	
 	public void saveOrUpdate(TBAuthEntity entity) throws Exception;

}
