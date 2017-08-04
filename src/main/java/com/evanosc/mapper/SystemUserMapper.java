package com.evanosc.mapper;

import com.evanosc.model.QueryUser;
import com.evanosc.model.SystemUser;

import java.util.List;
import java.util.Map;

public interface SystemUserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(SystemUser record);

    int insertSelective(SystemUser record);

    SystemUser selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(SystemUser record);

    int updateByPrimaryKey(SystemUser record);

    /**
     * 根据用户名，查询系统用户
     */
    SystemUser queryByUserName(String username);

    /**
     * 查询用户的所有菜单ID
     */
    List<Integer> queryAllMenuId(Integer userId);

    List<SystemUser> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    /**
     * 根据查找条件查找所有管理员
     * @param queryUser 查询用户实体类
     * @return List<SystemUser>
     */
    List<SystemUser> selectAllSystemUser(QueryUser queryUser);

    int selectCount(SystemUser systemUser);
}