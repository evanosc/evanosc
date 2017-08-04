package com.evanosc.mapper;

import com.evanosc.model.SystemMenu;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

public interface SystemMenuMapper {
    int deleteByPrimaryKey(Integer menuId);

    int insert(SystemMenu record);

    int insertSelective(SystemMenu record);

    SystemMenu selectByPrimaryKey(Integer menuId);

    int updateByPrimaryKeySelective(SystemMenu record);

    int updateByPrimaryKey(SystemMenu record);

    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     */
    List<SystemMenu> queryListParentId(Integer parentId);

    /**
     * 查询系统目录
     * @param status 状态
     * @param menuType 权限类型
     * @return List<SystemMenu>
     */
    List<SystemMenu> selectSystemMenu(@Param("status") Integer status, @Param("menuType") Integer menuType);

    /**
     * 查询系统菜单
     * @param menuType 权限类型
     * @return
     */
    List<SystemMenu> selectMenus(@Param("menuType") Integer menuType);

    /**
     * <p>
     * 删除（根据ID 批量删除）
     * </p>
     * @param menuIds
     * 				主键ID列表
     * @return int
     */
    int deleteBatchIds(@Param("menuIds") List<? extends Serializable> menuIds);

    /**
     * 查询用户授权列表
     * @return
     */
    List<SystemMenu> selectSystemMenus();
}