package com.evanosc.utils;

/**
 * 封装Ajax结果,所有Ajax请求返回类型
 * Created by evang on 2017/3/16.
 */
public class AjaxResult {
    /** 返回结果 */
    private boolean success;

    /** 返回信息 */
    private String message;

    /** 返回数据 */
    private Object data;


    public AjaxResult(boolean success) {
        super();
        this.success = success;
    }

    public AjaxResult(boolean success, String message) {
        super();
        this.success = success;
        this.message = message;
    }

    public AjaxResult(boolean success, Object data) {
        super();
        this.success = success;
        this.data = data;
    }

    public AjaxResult(boolean success, String message, Object data) {
        super();
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
