package com.evanosc.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 查询用户实体类
 * Created by evang on 2017/3/15.
 */
public class QueryUser {
    /**搜索内容*/
    private String searchContent;

    /**状态 0正常 1冻结 2删除*/
    private Integer status;

    /**查询 开始注册时间*/
    @DateTimeFormat(pattern="yyyy/MM/dd")
    private Date beginCreateTime;

    /**查询 结束注册时间*/
    @DateTimeFormat(pattern="yyyy/MM/dd")
    private Date endCreateTime;

    public String getSearchContent() {
        return searchContent;
    }

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getBeginCreateTime() {
        return beginCreateTime;
    }

    public void setBeginCreateTime(Date beginCreateTime) {
        this.beginCreateTime = beginCreateTime;
    }

    public Date getEndCreateTime() {
        return endCreateTime;
    }

    public void setEndCreateTime(Date endCreateTime) {
        this.endCreateTime = endCreateTime;
    }
}
