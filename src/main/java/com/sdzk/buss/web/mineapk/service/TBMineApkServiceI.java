package com.sdzk.buss.web.mineapk.service;
import com.sdzk.buss.web.mineapk.entity.TBMineApkEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface TBMineApkServiceI extends CommonService{
	
 	public void delete(TBMineApkEntity entity) throws Exception;
 	
 	public Serializable save(TBMineApkEntity entity) throws Exception;
 	
 	public void saveOrUpdate(TBMineApkEntity entity) throws Exception;
 	
}
