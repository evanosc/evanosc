package com.evanosc.controller;

import com.evanosc.model.ContentFolder;
import com.evanosc.service.IContentFolderService;
import com.evanosc.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 类目管理控制器
 * Created by evang on 2017/3/24.
 */
@RestController
@RequestMapping("/content/folder")
public class ContentFolderController extends BaseController {

    @Autowired
    private IContentFolderService contentFolderService;

    @InitBinder("contentFolder")
    public void initBinderSystemMenu(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("contentFolder.");
    }

    @RequestMapping(value = "/list")
    public ModelAndView list(Model model) {
        List<ContentFolder> contentFolders = contentFolderService.selectFolderList();
        model.addAttribute("contentFolders", contentFolders);// 类目列表
        return new ModelAndView("content/folder/content_folder_list.jsp");
    }

    /**
     * POST 显示/隐藏类目
     * @param folderId
     * @return
     */
//    @RequiresPermissions("menu:audit")
    @RequestMapping(value = "/{folderId}/audit", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult aduit(@PathVariable("folderId") Integer folderId) {
        Integer status = Integer.valueOf(getParameter("status"));
        contentFolderService.updateFolderStatus(folderId, status);
        return success(true);
    }

    /**
     * GET 添加类目
     * @param model
     * @return
     */
//    @RequiresPermissions("manage:menu:add")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add(Model model) {

        return new ModelAndView("content/folder/content_folder_update.jsp");
    }

    /**
     * POST 创建类目
     * @param contentFolder
     * @return
     */
//    @RequiresPermissions({"manage:menu:edit","manage:menu:add"})
    @RequestMapping(value="/save",method=RequestMethod.POST)
    @ResponseBody
    public AjaxResult save(@ModelAttribute("contentFolder") ContentFolder contentFolder) {
        if (contentFolder.getFolderId() == null) {
            contentFolderService.insertFolder(contentFolder);
            return success(true, "类别创建成功!");
        } else {
            contentFolderService.updateFolder(contentFolder);
            return success(true, "类别修改成功!");
        }
    }

    /**
     * GET 修改类目
     * @param folderId 类目ID
     * @param model
     * @return
     */
//    @RequiresPermissions("manage:menu:edit")
    @RequestMapping(value = "/{folderId}/edit", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("folderId") Integer folderId, Model model) {
        ContentFolder contentFolder = contentFolderService.selectByPrimaryKey(folderId);
        model.addAttribute("contentFolder",contentFolder);
        return new ModelAndView("content/folder/content_folder_update.jsp");
    }

    /**
     * DELETE 删除类目
     * @param folderId
     * @return
     */
//    @RequiresPermissions("manage:menu:delete")
    @RequestMapping(value = "/{folderId}/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxResult delete(@PathVariable("folderId") Integer folderId) {
        contentFolderService.deleteFolder(folderId);
        return success(true);
    }
}
