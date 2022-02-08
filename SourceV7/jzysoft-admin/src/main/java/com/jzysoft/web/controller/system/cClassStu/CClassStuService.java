package com.jzysoft.web.controller.system.cClassStu;

import com.jzysoft.commonmoudle.lib.config.page.Page;
import com.jzysoft.commonmoudle.lib.config.page.PageData;

import java.util.List;


/**
 * 【请填写功能名称】Service接口
 *
 * @author jzysoft
 * @date 2022-02-08
 */
public interface CClassStuService
{
    //    列表
    List<PageData> selectCClassStuList(Page page) throws Exception;
    //    id
    PageData selectCClassStuById(PageData pageData) throws Exception;

    //    保存
    void insertCClassStu(PageData pageData) throws Exception;

    //    修改
    void updateCClassStu(PageData pageData) throws Exception;

    //    删除
    void deleteCClassStuByIds(PageData pageData) throws Exception;
}
