package com.evanosc.service.impl;

import com.evanosc.mapper.ContentArticleMapper;
import com.evanosc.model.ContentArticle;
import com.evanosc.service.IContentArticleService;
import com.evanosc.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by evang on 2017/3/24.
 */
@Service("contentArticleService")
public class ContentArticleServiceImpl implements IContentArticleService {

    @Autowired
    private ContentArticleMapper contentArticleMapper;

    /**
     * 查询文章列表
     * @return
     */
    @Override
    public List<ContentArticle> selectArticleList() {
        return contentArticleMapper.selectArticleList();
    }

    /**
     * 通过articleId查询文章信息
     * @param articleId
     * @return
     */
    @Override
    public ContentArticle selectByPrimaryKey(Integer articleId) {
        return contentArticleMapper.selectByPrimaryKey(articleId);
    }

    /**
     * 新增文章
     * @param contentArticle
     */
    @Override
    public void insertArticle(ContentArticle contentArticle) {
        contentArticle.setCreateTime(new Date());
        contentArticle.setCreateBy(ShiroUtils.getSystemUserName());
//        contentArticle.setUpdateTime(new Date());
//        contentArticle.setUpdateBy(ShiroUtils.getSystemUserName());
        contentArticleMapper.insert(contentArticle);
    }

    /**
     * 更新文章
     * @param contentArticle
     */
    @Override
    public void updateArticle(ContentArticle contentArticle) {
        contentArticle.setUpdateTime(new Date());
        contentArticle.setUpdateBy(ShiroUtils.getSystemUserName());
        contentArticleMapper.updateByPrimaryKeySelective(contentArticle);
    }
}
