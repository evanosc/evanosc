package com.evanosc.service.impl;

import com.evanosc.mapper.SystemUserLoginLogMapper;
import com.evanosc.model.SystemUserLoginLog;
import com.evanosc.service.ISystemUserLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by evang on 2017/4/27.
 */
@Service("systemUserLoginLogService")
public class SystemUserLoginLogServiceImpl implements ISystemUserLoginLogService {

    @Autowired
    private SystemUserLoginLogMapper systemUserLoginLogMapper;

    @Override
    public List<SystemUserLoginLog> selectUserLoginLog(Integer userId) {
        return systemUserLoginLogMapper.selectUserLoginLog(userId);
    }

}
