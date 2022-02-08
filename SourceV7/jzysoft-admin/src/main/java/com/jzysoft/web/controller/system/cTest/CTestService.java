package com.jzysoft.web.controller.system.cTest;

import com.jzysoft.commonmoudle.lib.config.page.Page;
import com.jzysoft.commonmoudle.lib.config.page.PageData;

import java.util.List;


/**
 * 【请填写功能名称】Service接口
 *
 * @author jzysoft
 * @date 2022-02-08
 */
public interface CTestService
{
    //    列表
    List<PageData> selectCTestList(Page page) throws Exception;
    //    id
    PageData selectCTestById(PageData pageData) throws Exception;

    //    保存
    void insertCTest(PageData pageData) throws Exception;

    //    修改
    void updateCTest(PageData pageData) throws Exception;

    //    删除
    void deleteCTestByIds(PageData pageData) throws Exception;
}
