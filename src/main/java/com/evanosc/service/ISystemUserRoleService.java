package com.evanosc.service;

import com.evanosc.model.SystemUserRole;

import java.util.List;

/**
 * Created by evang on 2017/4/26.
 */
public interface ISystemUserRoleService {

    /**
     * 通过账号ID查找角色列表
     * @param userId
     * @return
     */
    List<SystemUserRole> selectRoleListByUserId(Integer userId);
}
