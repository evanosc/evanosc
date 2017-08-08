package com.evanosc.service;

import com.evanosc.model.ContentFolder;

import java.util.List;

/**
 * Created by evang on 2017/4/6.
 */
public interface IContentFolderService {

    List<ContentFolder> selectFolderList();

    List<ContentFolder> selectFolderListByCondition(ContentFolder contentFolder);

    /**
     * 根据类目ID查询类目信息
     * @param folderId
     * @return
     */
    ContentFolder selectByPrimaryKey(Integer folderId);

    /**
     * 更新类目状态
     * @param folderId 类目ID
     * @param status 状态
     */
    void updateFolderStatus(Integer folderId, Integer status);

    /**
     * 创建类目
     * @param contentFolder 类目对象
     */
    void insertFolder(ContentFolder contentFolder);

    /**
     * 更新类目
     * @param contentFolder 类目对象
     */
    void updateFolder(ContentFolder contentFolder);

    /**
     * 删除类目
     * @param folderId 类目ID
     */
    void deleteFolder(Integer folderId);


}
