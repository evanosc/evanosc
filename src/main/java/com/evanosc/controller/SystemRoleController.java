package com.evanosc.controller;

import com.alibaba.fastjson.JSON;
import com.evanosc.model.SystemMenu;
import com.evanosc.model.SystemRole;
import com.evanosc.service.ISystemMenuService;
import com.evanosc.service.ISystemRoleService;
import com.evanosc.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 角色管理控制器
 * Created by evang on 2017/3/3.
 */
@RestController
@RequestMapping("/system/role")
public class SystemRoleController extends BaseController{

    @Autowired
    private ISystemRoleService systemRoleService;

    @Autowired
    private ISystemMenuService systemMenuService;

    @InitBinder("systemRole")
    public void initBinderSystemMenu(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("systemRole.");
    }
    /**
     * GET 角色列表
     * @param model
     * @return
     */
//    @RequiresPermissions("sysuser:list:view")
    @RequestMapping(value = "/list")
    public ModelAndView list(Model model) {
        SystemRole systemRole = new SystemRole();
        List<SystemRole> systemRoles = systemRoleService.selectSystemRoleByCondition(systemRole);
        model.addAttribute("systemRoles", systemRoles);// 用户列表
        return new ModelAndView("system/role/system_role_list.jsp");
    }

    /**
     * POST 显示/隐藏角色
     * @param roleId
     * @return
     */
//    @RequiresPermissions("menu:audit")
    @RequestMapping(value = "/{roleId}/audit", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult aduit(@PathVariable("roleId") Integer roleId) {
        Integer status = Integer.valueOf(getParameter("status"));
        systemRoleService.updateSystemRoleStatus(roleId, status);
        return success(true);
    }

    /**
     * GET 添加角色
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add(Model model) {
        List<SystemMenu> systemMenus = systemMenuService.selectSystemMenus();
        model.addAttribute("systemMenus", JSON.toJSON(systemMenus));
        return new ModelAndView("system/role/system_role_update.jsp");
    }

    /**
     * POST 创建或修改角色
     * @param systemRole
     * @return
     */
//    @RequiresPermissions({"manage:menu:edit","manage:menu:add"})
    @RequestMapping(value="/save",method=RequestMethod.POST)
    @ResponseBody
    public AjaxResult save(@ModelAttribute("systemRole") SystemRole systemRole) {
        String[] menuIds = getParameter("menuIds").split(",");
        if (systemRole.getRoleId() == null) {
            systemRoleService.insertSystemRole(systemRole, menuIds);
            return success(true,"角色创建成功!");
        } else {
            systemRoleService.updateSystemRole(systemRole, menuIds);
            return success(true,"角色修改成功!");
        }
    }

    /**
     * GET 修改角色
     * @param roleId 角色ID
     * @param model
     * @return
     */
//    @RequiresPermissions("manage:menu:edit")
    @RequestMapping(value = "/{roleId}/edit", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("roleId") Integer roleId, Model model) {
        List<SystemMenu> systemMenus = systemMenuService.selectCheckedMenus(roleId);
        model.addAttribute("systemMenus", JSON.toJSON(systemMenus));
        SystemRole systemRole = systemRoleService.selectByPrimaryKey(roleId);
        model.addAttribute("systemRole", systemRole);
        return new ModelAndView("system/role/system_role_update.jsp");
    }

    /**
     * DELETE 删除角色
     * @param roleId
     * @return
     */
//    @RequiresPermissions("manage:menu:delete")
    @RequestMapping(value = "/{roleId}/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxResult delete(@PathVariable("roleId") Integer roleId) {
        systemRoleService.deleteRole(roleId);
        return success(true);
    }
}
