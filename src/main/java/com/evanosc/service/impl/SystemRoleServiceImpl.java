package com.evanosc.service.impl;

import com.evanosc.mapper.SystemRoleMapper;
import com.evanosc.mapper.SystemRoleMenuMapper;
import com.evanosc.model.SystemRole;
import com.evanosc.model.SystemRoleMenu;
import com.evanosc.service.ISystemRoleService;
import com.evanosc.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 角色管理service实现
 * Created by evang on 2017/3/2.
 */
@Service("systemRoleService")
public class SystemRoleServiceImpl implements ISystemRoleService {

    @Autowired
    private SystemRoleMapper systemRoleMapper;

    @Autowired
    private SystemRoleMenuMapper systemRoleMenuMapper;


    @Override
    public List<SystemRole> selectSystemRoleByCondition(SystemRole systemRole) {
        return systemRoleMapper.selectSystemRoleByCondition(systemRole);
    }

    @Override
    public void updateSystemRoleStatus(Integer roleId, Integer status) {
        SystemRole systemRole = new SystemRole();
        systemRole.setRoleId(roleId);
        systemRole.setStatus(status);
        systemRole.setUpdateTime(new Date());
        systemRole.setUpdateBy(ShiroUtils.getSystemUserName());
        systemRoleMapper.updateByPrimaryKeySelective(systemRole);
    }

    @Override
    public SystemRole selectByPrimaryKey(Integer roleId) {
        return systemRoleMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public void deleteRole(Integer roleId) {
        systemRoleMapper.deleteByPrimaryKey(roleId);
    }

    @Override
    public void insertSystemRole(SystemRole systemRole, String[] menuIds) {
        systemRole.setUpdateTime(new Date());
        systemRole.setUpdateBy(ShiroUtils.getSystemUserName());
        systemRoleMapper.insertSelective(systemRole);// 添加角色
        insertRoleMenu(systemRole, menuIds);
    }

    @Override
    public void updateSystemRole(SystemRole systemRole, String[] menuIds) {
        systemRole.setUpdateTime(new Date());
        systemRole.setUpdateBy(ShiroUtils.getSystemUserName());
        systemRoleMapper.updateByPrimaryKeySelective(systemRole);
        Integer roleId = systemRole.getRoleId();
        if(roleId != null && !"".equals(roleId)) {
            systemRoleMenuMapper.deleteRoleMenuByRoleId(roleId);// 根据角色id刪除角色授权
        }
        insertRoleMenu(systemRole, menuIds);
    }

    /**
     * 添加角色授权
     * @param systemRole
     * @param menuIds
     */
    private void insertRoleMenu(SystemRole systemRole, String[] menuIds) {
        if (menuIds != null && menuIds.length > 0) {
            for (int i = 0; i < menuIds.length; i++) {
                SystemRoleMenu systemRoleMenu = new SystemRoleMenu();
                systemRoleMenu.setMenuId(Integer.valueOf(menuIds[i]));
                systemRoleMenu.setRoleId(systemRole.getRoleId());
                systemRoleMenuMapper.insertSelective(systemRoleMenu);
            }
        }
    }
}
