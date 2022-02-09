package com.jzysoft.web.controller.system.cClass;

import com.jzysoft.commonmoudle.lib.config.page.Page;
import com.jzysoft.commonmoudle.lib.config.page.PageData;

import java.util.List;


/**
 * 【请填写功能名称】Service接口
 *
 * @author jzysoft
 * @date 2022-02-08
 */
public interface CClassService
{
    //    列表
    List<PageData> selectCClassList(Page page) throws Exception;
    //    id
    PageData selectCClassById(PageData pageData) throws Exception;

    //    保存
    void insertCClass(PageData pageData) throws Exception;

    //    修改
    void updateCClass(PageData pageData) throws Exception;

    //    删除
    void deleteCClassByIds(PageData pageData) throws Exception;

    void joinStu(PageData pageData) throws Exception;
}
