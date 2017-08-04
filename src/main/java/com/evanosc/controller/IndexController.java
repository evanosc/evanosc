package com.evanosc.controller;

import com.evanosc.model.*;
import com.evanosc.service.*;
import com.evanosc.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by evang on 2017/3/15.
 */
@Controller
@RequestMapping("/system/main")
public class IndexController extends BaseController{

    @Autowired
    private ISystemUserService systemUserService;

    @Autowired
    private ISystemMenuService systemMenuService;

    @Autowired
    private ISystemRoleService systemRoleService;

    @Autowired
    private ISystemUserRoleService systemUserRoleService;

    /**
     * 进入操作中心
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView main(Model model) {
        //用户信息
        model.addAttribute("systemUser", ShiroUtils.getSystemUser());
        //获取用户角色名称
        List<SystemUserRole> systemUserRoleList = systemUserRoleService.selectRoleListByUserId(ShiroUtils.getSystemUser().getUserId());
        if(systemUserRoleList != null && systemUserRoleList.size()>0){
            Integer roleId = systemUserRoleList.get(0).getRoleId();
            SystemRole systemRole = systemRoleService.selectByPrimaryKey(roleId);
            model.addAttribute("roleName", systemRole.getRoleName());
        }
        if(ShiroUtils.getSystemUser().getUserId() == 1000){  //超级管理员获取所有菜单信息
            List<SystemMenu> systemMenus = systemMenuService.selectSystemMenu();
            model.addAttribute("systemMenus", systemMenus);
        }else {
            //获取用户菜单
            List<SystemMenu> systemMenus = systemMenuService.selectSystemMenuByUserId(ShiroUtils.getSystemUser().getUserId());
            model.addAttribute("systemMenus", systemMenus);
        }
        return new ModelAndView("system/main/index.jsp");
    }

    /**
     * 后台管理主界面初始化首页
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView mainIndex(Model model) {
        Map<String, Object> webCountMap = new HashMap<>();
        model.addAttribute("webCountMap", webCountMap);
        return new ModelAndView("system/main/main.jsp");
    }

}
