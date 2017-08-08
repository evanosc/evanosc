package com.evanosc.service;

import com.evanosc.model.SystemMenu;

import java.util.List;

/**
 * Created by evang on 2017/3/2.
 */
public interface ISystemMenuService {

    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     * @param menuIdList  用户菜单ID
     */
    List<SystemMenu> queryListParentId(Integer parentId, List<Integer> menuIdList);

    /**
     * 查询系统目录,网站目录列表
     * @return List<SystemMenu>
     */
    List<SystemMenu> selectSystemMenu();

    /**
     * 查询系统菜单,按照排序顺序
     */
    List<SystemMenu> selectMenuList();

    /**
     * 更新目录状态
     * @param menuId 目录ID
     * @param status 状态
     */
    void updateMenuStatus(Integer menuId, Integer status);

    /**
     * 根据菜单ID查询菜单信息
     * @param menuId
     * @return
     */
    SystemMenu selectByPrimaryKey(Integer menuId);

    /**
     * 创建菜单
     * @param systemMenu 系统菜单
     */
    void insertMenu(SystemMenu systemMenu);

    /**
     * 更新菜单
     * @param systemMenu 系统菜单
     */
    void updateMenu(SystemMenu systemMenu);

    /**
     * 删除目录，同时删除角色权限表记录
     * @param menuId 目录ID
     */
    void deleteMenu(Integer menuId);

    /**
     * 查询用户授权列表
     * @return
     */
    List<SystemMenu> selectSystemMenus();

    /**
     * 查询选中角色授权表
     * @param roleId
     * @return
     */
    List<SystemMenu> selectCheckedMenus(Integer roleId);

    /**
     * 查询用户权限菜单
     * @param userId
     * @return
     */
    List<SystemMenu> selectSystemMenuByUserId(Integer userId);
}
