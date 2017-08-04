package com.evanosc.mapper;

import com.evanosc.model.SystemRole;

import java.util.List;

public interface SystemRoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(SystemRole record);

    int insertSelective(SystemRole record);

    SystemRole selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(SystemRole record);

    int updateByPrimaryKey(SystemRole record);

    List<SystemRole> selectSystemRoleByCondition(SystemRole systemRole);
}