package com.sdzk.buss.web.smsmine.service.impl;

import com.sdzk.buss.web.smsmine.service.TBSmsMineServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("tBSmsMineService")
@Transactional
public class TBSmsMineServiceImpl extends CommonServiceImpl implements TBSmsMineServiceI {
	
}