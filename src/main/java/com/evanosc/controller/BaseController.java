package com.evanosc.controller;

import com.evanosc.utils.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 公共组件
 * Created by evang on 2017/3/2.
 */
public abstract class BaseController {

    protected Logger LOGGER = LoggerFactory.getLogger(getClass());

    /**
     * 成功,返回状态
     * @param success 状态true/false
     * @return
     */
    public AjaxResult success(Boolean success) {
        return new AjaxResult(success);
    }

    /**
     * 成功,返回状态
     * @param success 状态true/false
     * @return
     */
    public AjaxResult success(Boolean success, String message) {
        return new AjaxResult(success, message);
    }

    /**
     * 返回json数据
     * @param success 状态true/false
     * @param data 实体
     * @return
     */
    public AjaxResult json(Boolean success, Object data) {
        return new AjaxResult(success, data);
    }

    /**
     * 失败,返回状态及原因
     * @param success 状态true/false
     * @param message 消息
     * @return
     */
    public AjaxResult fail(Boolean success, String message) {
        return new AjaxResult(success, message);
    }

    /**
     * 获取页面地址url
     * @param path
     * @return
     */
    protected static String getViewPath( String path ){
        StringBuilder viewPath = new StringBuilder();
        viewPath.append(path);
        return viewPath.toString();
    }

    /**
     * 获取当前请求对象
     * @return
     */
    public static HttpServletRequest getRequest(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
    /**
     * getParameter系列的方法主要用于处理“请求数据”，是服务器端程序获取浏览器所传递参数的主要接口。
     * @param name 表单name属性
     * @return
     */
    public String getParameter(String name) {
        return getRequest().getParameter(name);
    }

    /**
     * getParameterValues这个方法是获得传过来的参数名相同的一个数组;
     * @param name
     * @return
     */
    public String[] getParameterValues(String name){
        return getRequest().getParameterValues(name);
    }

    /**
     * 重定向至地址 url
     * @param url 请求地址
     * @return
     */
    protected String redirectTo( String url ) {
        StringBuilder rto = new StringBuilder("redirect:");
        rto.append(url);
        return rto.toString();
    }
}
