package com.evanosc.mapper;

import com.evanosc.model.ContentArticle;

import java.util.List;

public interface ContentArticleMapper {
    int deleteByPrimaryKey(Integer articleId);

    int insert(ContentArticle record);

    int insertSelective(ContentArticle record);

    ContentArticle selectByPrimaryKey(Integer articleId);

    int updateByPrimaryKeySelective(ContentArticle record);

    int updateByPrimaryKeyWithBLOBs(ContentArticle record);

    int updateByPrimaryKey(ContentArticle record);

    /**
     * 查询文章列表
     * @return
     */
    List<ContentArticle> selectArticleList();
}