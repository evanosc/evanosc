package com.evanosc.utils;

import com.google.code.kaptcha.Constants;
import com.evanosc.shiro.SystemAuthorizingUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Shiro工具类
 * Created by evang on 2017/3/2.
 */
public class ShiroUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroUtils.class);

    private ShiroUtils() {
    }

    /**
     * 获取验证码
     * @return
     */
    public static String getKaptcha() {
        // 获取Session中验证码
        Object captcha = ServletUtils.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        return captcha.toString();
    }

    /**
     * 获取后台登录用户
     * @return SystemAuthorizingUser
     */
    public static SystemAuthorizingUser getSystemUser() {
        try {
            Subject subject = SecurityUtils.getSubject();
            SystemAuthorizingUser systemUser = (SystemAuthorizingUser) subject.getPrincipal();
            if (systemUser != null) {
                return systemUser;
            }
        } catch (UnavailableSecurityManagerException e) {
            LOGGER.error("SystemUserServiceImpl.getSystemUser", e);
        }
        return null;
    }

    /**
     * 获取后台登录用户昵称
     * @return
     */
    public static String getSystemUserName(){
        return getSystemUser().getUserName();
    }


}
