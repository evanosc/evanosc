package com.evanosc.service;

import com.evanosc.model.ContentArticle;

import java.util.List;

/**
 * Created by evang on 2017/3/24.
 */
public interface IContentArticleService {

    /**
     * 查询文章列表
     * @return
     */
    List<ContentArticle> selectArticleList();

    /**
     * 通过articleId查询文章信息
     * @param articleId
     * @return
     */
    ContentArticle selectByPrimaryKey(Integer articleId);

    /**
     * 创建文章
     * @param contentArticle
     */
    void insertArticle(ContentArticle contentArticle);

    /**
     * 更新文章
     * @param contentArticle
     */
    void updateArticle(ContentArticle contentArticle);
}
