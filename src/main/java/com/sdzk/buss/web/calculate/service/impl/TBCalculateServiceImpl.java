package com.sdzk.buss.web.calculate.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sdzk.buss.web.calculate.service.TBCalculateServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("tBCalculateService")
@Transactional
public class TBCalculateServiceImpl extends CommonServiceImpl implements TBCalculateServiceI {
	
}