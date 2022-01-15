package com.jzysoft.commonmoudle.moudles.test;

import com.jzysoft.commonmoudle.lib.config.page.Page;
import com.jzysoft.commonmoudle.lib.config.page.PageData;

import java.util.List;


/**
 * 【请填写功能名称】Service接口
 *
 * @author jzysoft
 * @date 2020-04-07
 */
public interface TestService
{
    //    列表
    List<PageData> selectTestList(Page page) throws Exception;
    //    id
    PageData selectTestById(PageData pageData) throws Exception;

    //    保存
    void insertTest(PageData pageData) throws Exception;

    //    修改
    void updateTest(PageData pageData) throws Exception;

    //    删除
    void deleteTestByIds(PageData pageData) throws Exception;
}
