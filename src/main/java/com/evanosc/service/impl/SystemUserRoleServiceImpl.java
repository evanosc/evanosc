package com.evanosc.service.impl;

import com.evanosc.mapper.SystemUserRoleMapper;
import com.evanosc.model.SystemUserRole;
import com.evanosc.service.ISystemUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by evang on 2017/4/26.
 */
@Service("systemUserRoleService")
public class SystemUserRoleServiceImpl implements ISystemUserRoleService {

    @Autowired
    private SystemUserRoleMapper systemUserRoleMapper;

    @Override
    public List<SystemUserRole> selectRoleListByUserId(Integer userId) {
        return systemUserRoleMapper.selectRoleListByUserId(userId);
    }
}
