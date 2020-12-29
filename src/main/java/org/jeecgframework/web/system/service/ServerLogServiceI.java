package org.jeecgframework.web.system.service;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.IpServerLog;

import java.io.Serializable;

public interface ServerLogServiceI extends CommonService {

 	public void delete(IpServerLog entity) throws Exception;

 	public Serializable save(IpServerLog entity) throws Exception;

 	public void saveOrUpdate(IpServerLog entity) throws Exception;


}
