package com.evanosc.controller;

import com.evanosc.model.ContentArticle;
import com.evanosc.model.ContentFolder;
import com.evanosc.service.IContentArticleService;
import com.evanosc.service.IContentFolderService;
import com.evanosc.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 文章管理控制器
 * Created by evang on 2017/3/24.
 */
@RestController
@RequestMapping("/content/article")
public class ContentArticleController extends BaseController {

    @Autowired
    private IContentArticleService contentArticleService;
    @Autowired
    private IContentFolderService contentFolderService;

    @InitBinder("contentArticle")
    public void initBinderSystemMenu(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("contentArticle.");
    }

    @RequestMapping(value = "/list")
    public ModelAndView list(Model model) {
        List<ContentArticle> contentArticles = contentArticleService.selectArticleList();
        model.addAttribute("contentArticles", contentArticles);// 菜单列表
        return new ModelAndView("content/article/content_article_list.jsp");
    }

    /**
     * GET 添加文章
     * @param model
     * @return
     */
//    @RequiresPermissions("manage:menu:add")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add(Model model) {
        ContentFolder contentFolder = new ContentFolder();
        contentFolder.setStatus(1);
        List<ContentFolder> contentFolders = contentFolderService.selectFolderListByCondition(contentFolder);
        model.addAttribute("contentFolders", contentFolders);// 类目列表
        return new ModelAndView("content/article/content_article_update.jsp");
    }

    /**
     * GET 修改文章模版
     * @param articleId 文章ID
     * @param model
     * @return
     */
//    @RequiresPermissions("manage:menu:edit")
    @RequestMapping(value = "/{articleId}/content", method = RequestMethod.GET)
    public ModelAndView content(@PathVariable("articleId") Integer articleId, Model model) {
        ContentArticle contentArticle = contentArticleService.selectByPrimaryKey(articleId);
        model.addAttribute("contentArticle", contentArticle);
        return new ModelAndView("content/article/content_article_content.jsp");
    }

    /**
     * POST 创建或修改文章
     * @param contentArticle
     * @return
     */
//    @RequiresPermissions({"manage:menu:edit","manage:menu:add"})
    @RequestMapping(value="/save",method=RequestMethod.POST)
    @ResponseBody
    public AjaxResult save(@ModelAttribute("contentArticle") ContentArticle contentArticle) {
        if (contentArticle.getArticleId() == null) {
            contentArticleService.insertArticle(contentArticle);
            return success(true, "文章创建成功!");
        } else {
            contentArticleService.updateArticle(contentArticle);
            return success(true, "文章修改成功!");
        }
    }

}
