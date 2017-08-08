package com.evanosc.service;

import com.evanosc.model.SystemRole;

import java.util.List;

/**
 * 角色管理service接口
 * Created by evang on 2017/4/17.
 */
public interface ISystemRoleService {

    List<SystemRole> selectSystemRoleByCondition(SystemRole systemRole);

    void updateSystemRoleStatus(Integer roleId, Integer status);

    SystemRole selectByPrimaryKey(Integer roleId);

    void deleteRole(Integer roleId);

    void insertSystemRole(SystemRole systemRole, String[] menuIds);

    void updateSystemRole(SystemRole systemRole, String[] menuIds);
}
