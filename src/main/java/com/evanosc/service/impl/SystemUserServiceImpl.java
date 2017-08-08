package com.evanosc.service.impl;

import com.evanosc.mapper.SystemUserLoginLogMapper;
import com.evanosc.mapper.SystemUserMapper;
import com.evanosc.mapper.SystemUserRoleMapper;
import com.evanosc.model.QueryUser;
import com.evanosc.model.SystemUser;
import com.evanosc.model.SystemUserLoginLog;
import com.evanosc.model.SystemUserRole;
import com.evanosc.service.ISystemUserService;
import com.evanosc.utils.ShiroUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by evang on 2017/3/2.
 */
@Service("systemUserService")
public class SystemUserServiceImpl implements ISystemUserService {

    @Autowired
    private SystemUserMapper systemUserMapper;

    @Autowired
    private SystemUserRoleMapper systemUserRoleMapper;

    @Autowired
    private SystemUserLoginLogMapper systemUserLoginLogMapper;

    /**
     * 通过用户名查询该用户信息
     * @param username
     * @return
     */
    @Override
    public SystemUser queryByUserName(String username) {
        return systemUserMapper.queryByUserName(username);
    }

    /**
     * 查询用户列表
     * @param map
     * @return
     */
    @Override
    public List<SystemUser> queryList(Map<String, Object> map) {
        return systemUserMapper.queryList(map);
    }

    /**
     * 查询总数
     * @param map
     * @return
     */
    @Override
    public int queryTotal(Map<String, Object> map) {
        return systemUserMapper.queryTotal(map);
    }


    @Override
    public List<SystemUser> selectAllSystemUser(QueryUser queryUser) {
        return systemUserMapper.selectAllSystemUser(queryUser);
    }

    @Override
    public void updateSystemUserStatus(Integer userId, Integer status) {
        SystemUser systemUser = new SystemUser();
        systemUser.setUserId(userId);
        systemUser.setStatus(status);
        systemUser.setUpdateTime(new Date());
        systemUser.setUpdateBy(ShiroUtils.getSystemUserName());
        systemUserMapper.updateByPrimaryKeySelective(systemUser);
    }

    @Override
    public void insertUser(SystemUser systemUser) {
        systemUser.setCreateTime(new Date());
        systemUser.setUpdateBy(ShiroUtils.getSystemUserName());
        systemUserMapper.insert(systemUser);
    }

    @Override
    public void updateUser(SystemUser systemUser) {
        systemUser.setUpdateTime(new Date());
        systemUser.setUpdateBy(ShiroUtils.getSystemUserName());
        systemUserMapper.updateByPrimaryKeySelective(systemUser);
    }

    @Override
    public SystemUser selectByPrimaryKey(Integer userId) {
        return systemUserMapper.selectByPrimaryKey(userId);
    }

    @Override
    public void deleteUser(Integer userId) {
        systemUserMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public void updateLogByUserName(Integer userId, String ipAddr, String userBrowser, String userOperatingSystem) {
        //更新systemUser表用户登录信息
        SystemUser systemUser = new SystemUser();
        systemUser.setUserId(userId);
        systemUser.setLastLoginTime(new Date());
        systemUser.setLastLoginIp(ipAddr);
        systemUserMapper.updateByPrimaryKeySelective(systemUser);
        //创建用户登录日志
        SystemUserLoginLog systemUserLoginLog = new SystemUserLoginLog();
        systemUserLoginLog.setUserId(userId);
        systemUserLoginLog.setUserIp(ipAddr);
        systemUserLoginLog.setBrowser(userBrowser);
        systemUserLoginLog.setOperatingSystem(userOperatingSystem);
        systemUserLoginLog.setLoginTime(new Date());
        systemUserLoginLogMapper.insertSelective(systemUserLoginLog);
    }

    @Override
    public void updateSystemUserPassword(Integer userId, String newPassword) {
        SystemUser systemUser = new SystemUser();
        systemUser.setUserId(userId);
        systemUser.setLoginPassword(newPassword);
        systemUser.setUpdateTime(new Date());
        systemUser.setUpdateBy(ShiroUtils.getSystemUserName());
        systemUserMapper.updateByPrimaryKeySelective(systemUser);
    }

    @Override
    public boolean checkLoginName(String loginName) {
        SystemUser systemUser = new SystemUser();
        systemUser.setLoginName(loginName);
        int count = systemUserMapper.selectCount(systemUser);
        if (count > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void insertSystemUser(SystemUser systemUser, String[] roleIds) {
        //sha256加密
        systemUser.setLoginPassword(new Sha256Hash(systemUser.getLoginPassword()).toHex());
        systemUser.setCreateBy(ShiroUtils.getSystemUserName());
        systemUser.setCreateTime(new Date());
        systemUserMapper.insertSelective(systemUser);//插入用户

        //获取新增用户的ID
        SystemUser user = systemUserMapper.queryByUserName(systemUser.getLoginName());
        systemUser.setUserId(user.getUserId());
        insertUserRoleInfo(systemUser, roleIds);
    }

    @Override
    public void updateSystemUser(SystemUser systemUser, String[] roleIds) {
        systemUser.setUpdateTime(new Date());
        systemUser.setUpdateBy(ShiroUtils.getSystemUserName());
        systemUserMapper.updateByPrimaryKeySelective(systemUser);//更新用户信息
        //根据userId删除该用户权限记录
        Integer userId = systemUser.getUserId();
        if(userId != null && !"".equals(userId)) {
            systemUserRoleMapper.deleteRoleMenuByUserId(userId);// 根据角色id刪除角色授权
        }

        insertUserRoleInfo(systemUser, roleIds);
    }


    private void insertUserRoleInfo(SystemUser systemUser, String[] roleIds) {
        if (roleIds != null && roleIds.length > 0) {
            for(int i = 0; i<roleIds.length; i++){
                SystemUserRole systemUserRole = new SystemUserRole();
                systemUserRole.setUserId(systemUser.getUserId());
                systemUserRole.setCreateTime(new Date());
                systemUserRole.setRoleId(Integer.valueOf(roleIds[i]));
                systemUserRole.setCreateBy(ShiroUtils.getSystemUserName());
                systemUserRoleMapper.insertSelective(systemUserRole);
            }
        }
    }


}
