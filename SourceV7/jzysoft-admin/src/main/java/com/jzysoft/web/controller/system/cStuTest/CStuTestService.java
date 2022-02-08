package com.jzysoft.web.controller.system.cStuTest;

import com.jzysoft.commonmoudle.lib.config.page.Page;
import com.jzysoft.commonmoudle.lib.config.page.PageData;

import java.util.List;


/**
 * 【请填写功能名称】Service接口
 *
 * @author jzysoft
 * @date 2022-02-08
 */
public interface CStuTestService
{
    //    列表
    List<PageData> selectCStuTestList(Page page) throws Exception;
    //    id
    PageData selectCStuTestById(PageData pageData) throws Exception;

    //    保存
    void insertCStuTest(PageData pageData) throws Exception;

    //    修改
    void updateCStuTest(PageData pageData) throws Exception;

    //    删除
    void deleteCStuTestByIds(PageData pageData) throws Exception;
}
