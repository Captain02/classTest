package com.jzysoft.web.controller.system.cGread;

import com.jzysoft.commonmoudle.lib.config.page.Page;
import com.jzysoft.commonmoudle.lib.config.page.PageData;

import java.util.List;


/**
 * 【请填写功能名称】Service接口
 *
 * @author jzysoft
 * @date 2022-02-08
 */
public interface CGreadService
{
    //    列表
    List<PageData> selectCGreadList(Page page) throws Exception;
    //    id
    PageData selectCGreadById(PageData pageData) throws Exception;

    //    保存
    void insertCGread(PageData pageData) throws Exception;

    //    修改
    void updateCGread(PageData pageData) throws Exception;

    //    删除
    void deleteCGreadByIds(PageData pageData) throws Exception;
}
