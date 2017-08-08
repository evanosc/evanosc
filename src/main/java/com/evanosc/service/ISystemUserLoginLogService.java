package com.evanosc.service;

import com.evanosc.model.SystemUserLoginLog;

import java.util.List;

/**
 * 系统用户日志service
 * Created by evang on 2017/4/27.
 */
public interface ISystemUserLoginLogService {

    List<SystemUserLoginLog> selectUserLoginLog(Integer userId);
}
