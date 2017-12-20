package com.sdzk.buss.web.quartz;


import com.sdzk.buss.web.common.Constants;
import com.sdzk.buss.web.quartz.service.QrtzManagerServiceI;
import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.DicUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.quartz.*;
import org.quartz.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 17-9-19.
 */
@Service
public class QuartzJob implements Job {

    @Autowired
    private SystemService systemService;

    @Autowired
    private QrtzManagerServiceI qrtzManagerServiceI;
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // todo
    }
}
