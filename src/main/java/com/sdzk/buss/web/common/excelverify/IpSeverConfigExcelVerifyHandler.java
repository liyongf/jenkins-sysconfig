package com.sdzk.buss.web.common.excelverify;

import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.DicUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.jeecgframework.web.system.pojo.base.IpServerConfig;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 17-9-26.
 */
public class IpSeverConfigExcelVerifyHandler implements IExcelVerifyHandler<IpServerConfig> {

    private SystemService systemService = (SystemService) ApplicationContextUtil.getContext().getBean("systemService");

    @Override
    public ExcelVerifyHanlderResult verifyHandler(IpServerConfig bean) {
        StringBuilder sb = new StringBuilder();
        boolean success = true;

        String privateIp = bean.getPrivateIp();
        if(StringUtils.isBlank(privateIp)){
            sb.append("私有IP不能为空");
        }
        CriteriaQuery cq = new CriteriaQuery(IpServerConfig.class);
        cq.eq("privateIp",privateIp);
        cq.add();
        List<IpServerConfig> ipServerConfigs = this.systemService.getListByCriteriaQuery(cq,false);
        if(null != ipServerConfigs && ipServerConfigs.size()>0){
            sb.append("私有IP不重复导入");
        }
        if(StringUtil.isNotEmpty(sb.toString())){
            success = false;
        }
        return new ExcelVerifyHanlderResult(success,sb.toString());
    }
}
