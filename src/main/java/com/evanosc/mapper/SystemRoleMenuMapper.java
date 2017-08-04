package com.evanosc.mapper;

import com.evanosc.model.SystemRoleMenu;

import java.util.List;

public interface SystemRoleMenuMapper {
    int deleteByPrimaryKey(Integer roleMenuId);

    int insert(SystemRoleMenu record);

    int insertSelective(SystemRoleMenu record);

    SystemRoleMenu selectByPrimaryKey(Integer roleMenuId);

    int updateByPrimaryKeySelective(SystemRoleMenu record);

    int updateByPrimaryKey(SystemRoleMenu record);

    void deleteRoleMenuByRoleId(Integer roleId);

    List<SystemRoleMenu> selectSystemRoleMenuByCondition(SystemRoleMenu systemRoleMenu);
}