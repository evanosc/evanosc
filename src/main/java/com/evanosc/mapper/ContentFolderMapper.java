package com.evanosc.mapper;

import com.evanosc.model.ContentFolder;

import java.util.List;

public interface ContentFolderMapper {
    int deleteByPrimaryKey(Integer folderId);

    int insert(ContentFolder record);

    int insertSelective(ContentFolder record);

    ContentFolder selectByPrimaryKey(Integer folderId);

    int updateByPrimaryKeySelective(ContentFolder record);

    int updateByPrimaryKey(ContentFolder record);

    /**
     * 查询类目列表
     * @return
     */
    List<ContentFolder> selectFolderList();

    /**
     * 根据条件查询类目列表
     * @param contentFolder
     * @return
     */
    List<ContentFolder> selectFolderListByCondition(ContentFolder contentFolder);
}