package com.evanosc.mapper;

import com.evanosc.model.SystemUserLoginLog;

import java.util.List;

public interface SystemUserLoginLogMapper {
    int deleteByPrimaryKey(Integer logId);

    int insert(SystemUserLoginLog record);

    int insertSelective(SystemUserLoginLog record);

    SystemUserLoginLog selectByPrimaryKey(Integer logId);

    int updateByPrimaryKeySelective(SystemUserLoginLog record);

    int updateByPrimaryKey(SystemUserLoginLog record);

    List<SystemUserLoginLog> selectUserLoginLog(Integer userId);
}