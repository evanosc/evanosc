package com.evanosc.shiro;

/**
 * Created by evang on 2017/4/26.
 */
public class SystemAuthorizingUser {

    /** 用户ID */
    private Integer userId;

    /** 登录名 */
    private String loginName;

    /** 昵称 */
    private String userName;

    /** 真实姓名 */
    private String realName;

    /** 用户头像 */
    private String picImg;


    public SystemAuthorizingUser(Integer userId, String loginName, String userName, String realName, String picImg) {
        this.userId = userId;
        this.loginName = loginName;
        this.userName = userName;
        this.realName = realName;
        this.picImg = picImg;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPicImg() {
        return picImg;
    }

    public void setPicImg(String picImg) {
        this.picImg = picImg;
    }
}
