package com.evanosc.controller;

import com.evanosc.model.*;
import com.evanosc.service.ISystemRoleService;
import com.evanosc.service.ISystemUserLoginLogService;
import com.evanosc.service.ISystemUserRoleService;
import com.evanosc.service.ISystemUserService;
import com.evanosc.shiro.SystemAuthorizingUser;
import com.evanosc.utils.AjaxResult;
import com.evanosc.utils.ShiroUtils;
import com.evanosc.utils.WebUtil;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 系统用户
 * Created by evang on 2017/3/3.
 */
@RestController
@RequestMapping("/system/user")
public class SystemUserController extends BaseController{

    @Autowired
    private ISystemUserService systemUserService;

    @Autowired
    private ISystemRoleService systemRoleService;

    @Autowired
    private ISystemUserRoleService systemUserRoleService;

    @Autowired
    private ISystemUserLoginLogService systemUserLoginLogService;

    @InitBinder("systemUser")
    public void initBinderSystemMenu(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("systemUser.");
    }
    /**
     * GET 用户列表
     * @param model
     * @return
     */
//    @RequiresPermissions("sysuser:list:view")
    @RequestMapping(value = "/list")
    public ModelAndView list(Model model, @ModelAttribute("queryUser") QueryUser queryUser) {
        List<SystemUser> systemUsers = systemUserService.selectAllSystemUser(queryUser);
        model.addAttribute("systemUsers", systemUsers);// 用户列表
        return new ModelAndView("system/user/system_user_list.jsp");
    }

    /**
     * POST 显示/隐藏用户
     * @param userId
     * @return
     */
//    @RequiresPermissions("menu:audit")
    @RequestMapping(value = "/{userId}/audit", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult aduit(@PathVariable("userId") Integer userId) {
        Integer status = Integer.valueOf(getParameter("status"));
        systemUserService.updateSystemUserStatus(userId, status);
        return success(true);
    }

    /**
     * GET 添加目录
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add(Model model) {
        SystemRole systemRole = new SystemRole();
        List<SystemRole> systemRoles = systemRoleService.selectSystemRoleByCondition(systemRole);
        model.addAttribute("systemRoles", systemRoles);
        return new ModelAndView("system/user/system_user_update.jsp");
    }

    /**
     * POST 创建或修改用户
     * @param systemUser
     * @return
     */
//    @RequiresPermissions({"manage:menu:edit","manage:menu:add"})
    @RequestMapping(value="/save",method=RequestMethod.POST)
    @ResponseBody
    public AjaxResult save(@ModelAttribute("systemUser") SystemUser systemUser) {
        String[] roleIds = getParameterValues("roleId");
        if(!WebUtil.isEmail(systemUser.getEmail())){
            return fail(false, "请输入正确的电子邮箱");
        }
        if(!WebUtil.isTelephone(systemUser.getTelephone())){
            return fail(false, "请输入正确的手机号码");
        }
        if (systemUser.getUserId() == null) {
            if(systemUserService.checkLoginName(systemUser.getLoginName())){
                return fail(false, "该用户名已被使用");
            }
            systemUserService.insertSystemUser(systemUser, roleIds);//创建用户及插入角色记录
            return success(true, "用户创建成功!");
        } else {
            systemUserService.updateSystemUser(systemUser, roleIds);//更新用户及角色记录
            return success(true, "用户信息修改成功!");
        }
    }

    /**
     * GET 修改用户
     * @param userId 用户ID
     * @param model
     * @return
     */
//    @RequiresPermissions("manage:menu:edit")
    @RequestMapping(value = "/{userId}/edit", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("userId") Integer userId, Model model) {
        SystemUser systemUser = systemUserService.selectByPrimaryKey(userId);
        model.addAttribute("systemUser", systemUser);
        SystemRole systemRole = new SystemRole();
        List<SystemRole> systemRoles = systemRoleService.selectSystemRoleByCondition(systemRole);
        model.addAttribute("systemRoles", systemRoles);//所有角色
        List<SystemUserRole> systemRoleList = systemUserRoleService.selectRoleListByUserId(userId);
        model.addAttribute("systemRoleList", systemRoleList);//分配角色
        return new ModelAndView("system/user/system_user_update.jsp");
    }

    /**
     * DELETE 删除用户
     * @param userId
     * @return
     */
//    @RequiresPermissions("manage:menu:delete")
    @RequestMapping(value = "/{userId}/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxResult delete(@PathVariable("userId") Integer userId) {
        systemUserService.deleteUser(userId);
        return success(true);
    }

    /**
     * GET 管理员个人信息界面
     * @return
     */
//    @RequiresPermissions("sysuser:info:view")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ModelAndView view(Model model) {
        SystemAuthorizingUser sysUser = ShiroUtils.getSystemUser();
        if (sysUser != null) {
            SystemUser systemUser = systemUserService.queryByUserName(sysUser.getLoginName());
            model.addAttribute("systemUser", systemUser);// 用户信息

            List<SystemUserLoginLog> systemUserLoginLogList = systemUserLoginLogService
                    .selectUserLoginLog(systemUser.getUserId());
            model.addAttribute("systemUserLoginLogList", systemUserLoginLogList);// 用户日志

           /* List<SystemUserRole> systemUserRoles = systemUserRoleService.selectRoleListByUserId(systemUser.getUserId());
            StringBuilder userRole = new StringBuilder();
            for (SystemUserRole systemUserRole : systemUserRoles) {
                userRole.append(systemUserRole.get);
                userRole.append("&nbsp");
            }
            model.addAttribute("userRole", userRole);// 用户权限*/
        }
        return new ModelAndView("system/user/system_user_info.jsp");
    }

    /**
     * POST 更新管理员信息
     * @param systemUser
     * @return
     */
//    @RequiresPermissions("sysuser:info:edit")
    @RequestMapping(value = "/info/edit", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult edit(@ModelAttribute("systemUser") SystemUser systemUser){
        SystemAuthorizingUser sysUser = ShiroUtils.getSystemUser();
        if (sysUser != null) {
            systemUser.setUserId(sysUser.getUserId());
            systemUserService.updateUser(systemUser);
            return success(true);
        }else{
            return fail(false, "您未登录或者登录已超时,请先登录!");
        }
    }

    /**
     * 修改密码
     * @param request
     * @return
     */
//    @RequiresPermissions("sysuser:info:edit")
    @RequestMapping(value = "/info/edit/password", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult editPwd(HttpServletRequest request){
        SystemAuthorizingUser sysUser = ShiroUtils.getSystemUser();
        if (sysUser != null) {
            SystemUser systemUser = systemUserService.queryByUserName(sysUser.getLoginName());
            // 原密码
            String nowPassword = request.getParameter("nowPassword") == null ? ""
                    : request.getParameter("nowPassword");
            // 新密码
            String newPassword = request.getParameter("newPassword") == null ? ""
                    : request.getParameter("newPassword");
            // 确认密码
            String confirmPwd = request.getParameter("confirmPwd") == null ? ""
                    : request.getParameter("confirmPwd");

            //原密码
            nowPassword = new Sha256Hash(nowPassword).toHex();

            if(!nowPassword.equals(systemUser.getLoginPassword())){
                return fail(false, "原密码不正确!");
            }
            if(!WebUtil.isPassword(newPassword)){
                return fail(false, "密码长度8~16位，其中数字，字母和符号至少包含两种!");
            }
            if(!newPassword.equals(confirmPwd)){
                return fail(false, "两次输入的新密码不一致!");
            }
            //新密码
            newPassword = new Sha256Hash(newPassword).toHex();
            systemUserService.updateSystemUserPassword(systemUser.getUserId(), newPassword);
            return success(true, "修改成功!");
        }else{
            return fail(false, "您未登录或者登录已超时,请先登录!");
        }
    }


    /**
     * 通讯录
     * @param model
     * @return
     */
    @RequestMapping(value = "/phone")
    public ModelAndView phone(Model model) {
        return new ModelAndView("system/user/system_user_phone.jsp");
    }

    /**
     * 头像
     * @param model
     * @return
     */
    @RequestMapping(value = "/avatar")
    public ModelAndView avatar(Model model) {
        return new ModelAndView("system/user/system_user_avatar.jsp");
    }

}
