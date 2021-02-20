package com.sdzk.buss.web.service.impl.sysconfig;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sdzk.buss.web.service.sysconfig.SysconfigServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("sysconfigService")
@Transactional
public class SysconfigServiceImpl extends CommonServiceImpl implements SysconfigServiceI {

}