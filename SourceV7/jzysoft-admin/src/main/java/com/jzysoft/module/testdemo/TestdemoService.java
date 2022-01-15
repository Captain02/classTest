package com.jzysoft.module.testdemo;

import com.jzysoft.commonmoudle.lib.config.page.Page;
import com.jzysoft.commonmoudle.lib.config.page.PageData;

import java.util.List;


/**
 * 测试Service接口
 *
 * @author jzysoft
 * @date 2020-07-10
 */
public interface TestdemoService
{
    //    列表
    List<PageData> selectTestdemoList(Page page) throws Exception;
    //    id
    PageData selectTestdemoById(PageData pageData) throws Exception;

    //    保存
    void insertTestdemo(PageData pageData) throws Exception;

    //    修改
    void updateTestdemo(PageData pageData) throws Exception;

    //    删除
    void deleteTestdemoByIds(PageData pageData) throws Exception;
}
