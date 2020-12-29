package org.jeecgframework.web.system.service;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.IpServerConfig;

import java.io.Serializable;

public interface ServerWordServiceI extends CommonService {

 	public void delete(IpServerConfig entity) throws Exception;

 	public Serializable save(IpServerConfig entity) throws Exception;

 	public void saveOrUpdate(IpServerConfig entity) throws Exception;


}
