package com.evanosc.service.impl;

import com.evanosc.mapper.ContentFolderMapper;
import com.evanosc.model.ContentFolder;
import com.evanosc.service.IContentFolderService;
import com.evanosc.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by evang on 2017/4/6.
 */
@Service("contentFolderService")
public class ContentFolderServiceImpl implements IContentFolderService {

    @Autowired
    private ContentFolderMapper contentFolderMapper;

    /**
     * 查询类目列表
     * @return
     */
    @Override
    public List<ContentFolder> selectFolderList() {
        return contentFolderMapper.selectFolderList();
    }

    /**
     * TODO 根据类目ID查询类目信息
     * @param folderId
     * @return
     */
    @Override
    public ContentFolder selectByPrimaryKey(Integer folderId) {
        return contentFolderMapper.selectByPrimaryKey(folderId);
    }

    /**
     * TODO 更新类目状态
     * @param folderId 类目ID
     * @param status 状态
     */
    @Override
    public void updateFolderStatus(Integer folderId, Integer status) {
        ContentFolder contentFolder = new ContentFolder();
        contentFolder.setFolderId(folderId);
        contentFolder.setStatus(status);
        contentFolder.setUpdateTime(new Date());
        contentFolder.setUpdateBy(ShiroUtils.getSystemUserName());
        contentFolderMapper.updateByPrimaryKeySelective(contentFolder);
    }

    /**
     * TODO 创建类目
     * @param contentFolder 类目对象
     */
    @Override
    public void insertFolder(ContentFolder contentFolder) {
        contentFolder.setCreateTime(new Date());
        contentFolder.setCreateBy(ShiroUtils.getSystemUserName());
        contentFolderMapper.insert(contentFolder);

    }

    /**
     * TODO 更新类目
     * @param contentFolder 类目对象
     */
    @Override
    public void updateFolder(ContentFolder contentFolder) {
        contentFolder.setUpdateTime(new Date());
        contentFolder.setUpdateBy(ShiroUtils.getSystemUserName());
        contentFolderMapper.updateByPrimaryKeySelective(contentFolder);
    }

    /**
     *  TODO 删除类目
     * @param folderId 类目ID
     */
    @Override
    public void deleteFolder(Integer folderId) {
        contentFolderMapper.deleteByPrimaryKey(folderId);

    }

    /**
     * 根据条件查询类目列表
     * @return
     */
    @Override
    public List<ContentFolder> selectFolderListByCondition(ContentFolder contentFolder) {
        return contentFolderMapper.selectFolderListByCondition(contentFolder);
    }
}
