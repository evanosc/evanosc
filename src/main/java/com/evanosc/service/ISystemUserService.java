package com.evanosc.service;

import com.evanosc.model.QueryUser;
import com.evanosc.model.SystemUser;

import java.util.List;
import java.util.Map;

/**
 * 系统用户service接口
 * Created by evang on 2017/3/2.
 */
public interface ISystemUserService {

    /**
     * 根据用户名，查询系统用户
     */
    SystemUser queryByUserName(String username);

    /**
     * 查询用户列表
     */
    List<SystemUser> queryList(Map<String, Object> map);

    /**
     * 查询总数
     */
    int queryTotal(Map<String, Object> map);


    /**
     * 根据查找条件查找所有管理员
     * @param queryUser 查询用户实体类
     * @return List<SystemUser>
     */
    List<SystemUser> selectAllSystemUser(QueryUser queryUser);

    void updateSystemUserStatus(Integer userId, Integer status);

    void insertUser(SystemUser systemUser);

    void updateUser(SystemUser systemUser);

    SystemUser selectByPrimaryKey(Integer userId);

    void deleteUser(Integer userId);

    void updateLogByUserName(Integer userId, String ipAddr, String userBrowser, String userOperatingSystem);

    void updateSystemUserPassword(Integer userId, String newPassword);

    boolean checkLoginName(String loginName);

    void insertSystemUser(SystemUser systemUser, String[] roleIds);

    void updateSystemUser(SystemUser systemUser, String[] roleIds);
}
