package com.sdzk.buss.web.mineorg.service;
import com.sdzk.buss.web.mineorg.entity.TBMineOrgEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface TBMineOrgServiceI extends CommonService{
	
 	public void delete(TBMineOrgEntity entity) throws Exception;
 	
 	public Serializable save(TBMineOrgEntity entity) throws Exception;
 	
 	public void saveOrUpdate(TBMineOrgEntity entity) throws Exception;
 	
}
