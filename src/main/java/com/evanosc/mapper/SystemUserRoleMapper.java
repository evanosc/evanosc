package com.evanosc.mapper;

import com.evanosc.model.SystemUserRole;

import java.util.List;

public interface SystemUserRoleMapper {
    int deleteByPrimaryKey(Integer userRoleId);

    int insert(SystemUserRole record);

    int insertSelective(SystemUserRole record);

    SystemUserRole selectByPrimaryKey(Integer userRoleId);

    int updateByPrimaryKeySelective(SystemUserRole record);

    int updateByPrimaryKey(SystemUserRole record);

    List<SystemUserRole> selectRoleListByUserId(Integer userId);

    void deleteRoleMenuByUserId(Integer userId);

    SystemUserRole selectUserRoleByUserId(Integer userId);
}