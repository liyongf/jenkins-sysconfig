package com.sdzk.buss.web.controller.sysconfig;

import org.jeecgframework.core.common.model.json.DataGrid;
import org.apache.commons.lang.StringUtils;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import net.sf.json.JSONArray;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections.map.HashedMap;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/magicSelectController")
public class MagicSelectController {
    private SystemService systemService;
    @Autowired
    public void setSystemService(SystemService systemService) {
        this.systemService = systemService;
    }

    @RequestMapping(params = "getBelongmineinfo")
    @ResponseBody
    public JSONArray getUserList(HttpServletRequest request)
            throws Exception {
        String sql = "select id, belongmineinfo from sysconfigProperties";
        List<Map<String, Object>> hazardList = systemService.findForJdbc(sql);
        JSONArray jsonArray = JSONArray.fromObject(hazardList);
        return jsonArray;
    }

}
