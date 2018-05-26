package com.sdzk.buss.web.minedeploy.service;
import com.sdzk.buss.web.minedeploy.entity.TBMineDeployEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface TBMineDeployServiceI extends CommonService{
	
 	public void delete(TBMineDeployEntity entity) throws Exception;
 	
 	public Serializable save(TBMineDeployEntity entity) throws Exception;
 	
 	public void saveOrUpdate(TBMineDeployEntity entity) throws Exception;
 	
}
