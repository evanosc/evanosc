package com.evanosc.controller;

import com.evanosc.model.SystemMenu;
import com.evanosc.service.ISystemMenuService;
import com.evanosc.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 系统菜单
 * Created by evang on 2017/3/2.
 */
@RestController
@RequestMapping("/system/menu")
public class SystemMenuController extends BaseController{

    @Autowired
    private ISystemMenuService systemMenuService;

    @InitBinder("systemMenu")
    public void initBinderSystemMenu(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("systemMenu.");
    }

    @RequestMapping(value = "/list")
    public ModelAndView list(Model model) {
        List<SystemMenu> systemMenus = systemMenuService.selectMenuList();
        model.addAttribute("systemMenus", systemMenus);// 菜单列表
        return new ModelAndView("system/menu/system_menu_list.jsp");
    }

    /**
     * POST 显示/隐藏目录
     * @param menuId
     * @return
     */
//    @RequiresPermissions("menu:audit")
    @RequestMapping(value = "/{menuId}/audit", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult aduit(@PathVariable("menuId") Integer menuId) {
        Integer status = Integer.valueOf(getParameter("status"));
        systemMenuService.updateMenuStatus(menuId, status);
        return success(true);
    }

    /**
     * GET 添加目录
     * @param menuId 父目录ID
     * @param model
     * @return
     */
//    @RequiresPermissions("manage:menu:add")
    @RequestMapping(value = "/{menuId}/add", method = RequestMethod.GET)
    public ModelAndView add(@PathVariable("menuId") Integer menuId, Model model) {
        SystemMenu parentMenu = systemMenuService.selectByPrimaryKey(menuId);
        model.addAttribute("parentMenu", parentMenu);
        return new ModelAndView("system/menu/system_menu_update.jsp");
    }

    /**
     * GET 菜单图标
     * @return
     */
    @RequestMapping(value = "/icon", method = RequestMethod.GET)
    public ModelAndView icon() {
        return new ModelAndView("system/menu/system_menu_icon.jsp");
    }

    /**
     * DELETE 删除目录
     * @param menuId
     * @return
     */
//    @RequiresPermissions("manage:menu:delete")
    @RequestMapping(value = "/{menuId}/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxResult delete(@PathVariable("menuId") Integer menuId) {
        systemMenuService.deleteMenu(menuId);
        return success(true);
    }

    /**
     * GET 修改目录
     * @param menuId 目录ID
     * @param model
     * @return
     */
//    @RequiresPermissions("manage:menu:edit")
    @RequestMapping(value = "/{menuId}/edit", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("menuId") Integer menuId, Model model) {
        SystemMenu systemMenu = systemMenuService.selectByPrimaryKey(menuId);
        model.addAttribute("systemMenu", systemMenu);
        SystemMenu parentMenu = systemMenuService.selectByPrimaryKey(systemMenu.getParentId());
        model.addAttribute("menuName", parentMenu.getMenuName());
        return new ModelAndView("system/menu/system_menu_update.jsp");
    }

    /**
     * POST 创建或修改菜单
     * @param systemMenu
     * @return
     */
//    @RequiresPermissions({"manage:menu:edit","manage:menu:add"})
    @RequestMapping(value="/save",method=RequestMethod.POST)
    @ResponseBody
    public AjaxResult save(@ModelAttribute("systemMenu") SystemMenu systemMenu) {
        if (systemMenu.getMenuId() == null) {
            systemMenuService.insertMenu(systemMenu);
            return success(true, "菜单创建成功!");
        } else {
            systemMenuService.updateMenu(systemMenu);
            return success(true, "菜单修改成功!");
        }
    }

}
