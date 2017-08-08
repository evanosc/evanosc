package com.evanosc.service.impl;

import com.evanosc.mapper.SystemMenuMapper;
import com.evanosc.mapper.SystemRoleMenuMapper;
import com.evanosc.mapper.SystemUserRoleMapper;
import com.evanosc.model.SystemMenu;
import com.evanosc.model.SystemRoleMenu;
import com.evanosc.model.SystemUserRole;
import com.evanosc.service.ISystemMenuService;
import com.evanosc.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by evang on 2017/3/2.
 */
@Service("systemMenuService")
public class SystemMenuServiceImpl implements ISystemMenuService {

    @Autowired
    private SystemMenuMapper systemMenuMapper;

    @Autowired
    private SystemUserRoleMapper systemUserRoleMapper;

    @Autowired
    private SystemRoleMenuMapper systemRoleMenuMapper;

    /**
     * 根据父菜单，查询子菜单
     * @param parentId
     * @param menuIdList
     * @return
     */
    @Override
    public List<SystemMenu> queryListParentId(Integer parentId, List<Integer> menuIdList) {
        List<SystemMenu> menuList = systemMenuMapper.queryListParentId(parentId);
        if(menuIdList == null){
            return menuList;
        }

        List<SystemMenu> userMenuList = new ArrayList<>();
        for(SystemMenu menu : menuList){
            if(menuIdList.contains(menu.getMenuId())){
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    /**
     * 查询系统菜单
     * @return
     */
    @Override
    public List<SystemMenu> selectSystemMenu() {
        List<SystemMenu> systemMenus = new ArrayList<>();
        // 查询一级目录
        List<SystemMenu> parentMenuList = systemMenuMapper.selectSystemMenu(1, 1);
        // 查询二级目录
        List<SystemMenu> childMenuList = systemMenuMapper.selectSystemMenu(1, 2);

        // 获取根级权限的子级权限
        for (SystemMenu parentMenu : parentMenuList) {
            recursionMenu(systemMenus, childMenuList, parentMenu);
        }
        return systemMenus;
    }

    /**
     * 递归得到每个权限的子级权限
     * @param systemMenus 处理后的目录列表
     * @param childMenuList  二级目录列表
     * @param parentMenu 当前一级目录
     */
    private void recursionMenu(List<SystemMenu> systemMenus, List<SystemMenu> childMenuList, SystemMenu parentMenu){
        List<SystemMenu> childMenus = new ArrayList<>();
        for(SystemMenu menu : childMenuList){
            if(parentMenu.getMenuId() == menu.getParentId()){
                childMenus.add(menu);
            }
        }
        parentMenu.setList(childMenus);
        systemMenus.add(parentMenu);
    }

    /**
     * 查询系统菜单,按照排序顺序
     * @return
     */
    @Override
    public List<SystemMenu> selectMenuList() {
        List<SystemMenu> systemMenus = new ArrayList<>();
        List<SystemMenu> parentMenuList = systemMenuMapper.selectMenus(1);// 查询一级目录
        List<SystemMenu> childMenuList = systemMenuMapper.selectMenus(2);// 查询二级目录
        List<SystemMenu> handleMenuList = systemMenuMapper.selectMenus(0);// 查询操作目录

        for (SystemMenu parentMenu : parentMenuList) {// 遍历一级目录
            systemMenus.add(parentMenu);
            for (SystemMenu childMenu : childMenuList) {// 遍历二级目录
                if (parentMenu.getMenuId() == childMenu.getParentId()) {
                    systemMenus.add(childMenu);
                    for (SystemMenu handleMenu : handleMenuList) {// 遍历操作目录
                        if (childMenu.getMenuId() == handleMenu.getParentId()) {
                            systemMenus.add(handleMenu);
                        }
                    }
                }
            }
        }
        return systemMenus;
    }

    /**
     * 更新目录状态
     * @param menuId 目录ID
     * @param status 状态
     */
    @Override
    public void updateMenuStatus(Integer menuId, Integer status) {
        SystemMenu systemMenu = new SystemMenu();
        systemMenu.setMenuId(menuId);
        systemMenu.setStatus(status);
        systemMenu.setUpdateTime(new Date());
        systemMenu.setUpdateBy(ShiroUtils.getSystemUserName());
        systemMenuMapper.updateByPrimaryKeySelective(systemMenu);
    }

    /**
     * 根据菜单ID查询菜单信息
     * @param menuId
     * @return
     */
    @Override
    public SystemMenu selectByPrimaryKey(Integer menuId) {
        return systemMenuMapper.selectByPrimaryKey(menuId);
    }

    /**
     * 创建菜单
     * @param systemMenu 系统菜单
     */
    @Override
    public void insertMenu(SystemMenu systemMenu) {
        systemMenu.setCreateTime(new Date());
        systemMenu.setCreateBy(ShiroUtils.getSystemUserName());
        systemMenu.setUpdateTime(new Date());
        systemMenu.setUpdateBy(ShiroUtils.getSystemUserName());
        systemMenuMapper.insert(systemMenu);
    }

    /**
     * 更新菜单
     * @param systemMenu 系统菜单
     */
    @Override
    public void updateMenu(SystemMenu systemMenu) {
        systemMenu.setUpdateTime(new Date());
        systemMenu.setUpdateBy(ShiroUtils.getSystemUserName());
        systemMenuMapper.updateByPrimaryKeySelective(systemMenu);
    }

    /**
     * 删除菜单，同时删除角色权限表记录
     * @param menuId 目录ID
     */
    @Override
    public void deleteMenu(Integer menuId) {
        List<Integer> menuIds = new ArrayList<>();
        menuIds.add(menuId);
        getMenuIds(menuIds,menuId);
        systemMenuMapper.deleteBatchIds(menuIds);//删除目录及子目录
//        systemRoleMenuMapper.deleteMenuList(menuIds);//删除角色授权表中记录
    }

    /**
     * 查询用户授权列表
     * @return
     */
    @Override
    public List<SystemMenu> selectSystemMenus() {
        return systemMenuMapper.selectSystemMenus();
    }

    @Override
    public List<SystemMenu> selectCheckedMenus(Integer roleId) {
        SystemRoleMenu systemRoleMenu = new SystemRoleMenu();
        systemRoleMenu.setRoleId(roleId);
        List<SystemRoleMenu> systemRoleMenus = systemRoleMenuMapper.selectSystemRoleMenuByCondition(systemRoleMenu);// 角色拥有的菜单
        List<SystemMenu> systemMenus = systemMenuMapper.selectSystemMenus();
        for (SystemMenu menu : systemMenus) {
            for (SystemRoleMenu roleMenu : systemRoleMenus) {
                if (menu.getMenuId().equals(roleMenu.getMenuId())) {
                    menu.setChecked(true);
                }
            }
        }
        return systemMenus;
    }

    @Override
    public List<SystemMenu> selectSystemMenuByUserId(Integer userId) {
        //用户菜单列表
        List<SystemMenu> systemUserMenus = new ArrayList<>();

        SystemUserRole systemUserRole = systemUserRoleMapper.selectUserRoleByUserId(userId);
        if(systemUserRole != null){
            SystemRoleMenu systemRoleMenu = new SystemRoleMenu();
            systemRoleMenu.setRoleId(systemUserRole.getRoleId());
            List<SystemRoleMenu> systemRoleMenus = systemRoleMenuMapper.selectSystemRoleMenuByCondition(systemRoleMenu);// 角色拥有的菜单
            // 查询状态为1的菜单目录
            List<SystemMenu> systemMenus = systemMenuMapper.selectSystemMenus();
            // 定义一级目录
            List<SystemMenu> parentMenuList = new ArrayList<SystemMenu>();
            // 定义二级目录
            List<SystemMenu> childMenuList = new ArrayList<SystemMenu>();
            // 遍历用户权限目录
            for (SystemMenu menu : systemMenus) {
                for (SystemRoleMenu roleMenu : systemRoleMenus) {
                    if (menu.getMenuId() == roleMenu.getMenuId()) {
                        if(menu.getMenuType() == 1){
                            parentMenuList.add(menu);
                        }else if (menu.getMenuType() == 2){
                            childMenuList.add(menu);
                        }
                    }
                }
            }
            // 获取根级权限的子级权限
            for (SystemMenu parentMenu : parentMenuList) {
                recursionMenu(systemUserMenus, childMenuList, parentMenu);
            }
        }
        return systemUserMenus;
    }


    /**
     * 根据目录ID查找要删除的目录ID的子目录
     * @param menuId 目录ID
     * @return List<Integer>
     */
    private List<Integer> getMenuIds(List<Integer> menuIds, Integer menuId) {
        List<SystemMenu> menus = systemMenuMapper.queryListParentId(menuId);
        if (menus != null) {
            for (SystemMenu menu : menus) {
                menuIds.add(menu.getMenuId());
                if (menu.getMenuType() == 0) {
                    continue;
                }
                getMenuIds(menuIds, menu.getMenuId());
            }
        }
        return menuIds;
    }
}
