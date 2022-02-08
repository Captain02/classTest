package com.jzysoft.web.controller.system.cTestItem;

import com.jzysoft.commonmoudle.lib.config.page.Page;
import com.jzysoft.commonmoudle.lib.config.page.PageData;

import java.util.List;


/**
 * 【请填写功能名称】Service接口
 *
 * @author jzysoft
 * @date 2022-02-08
 */
public interface CTestItemService
{
    //    列表
    List<PageData> selectCTestItemList(Page page) throws Exception;
    //    id
    PageData selectCTestItemById(PageData pageData) throws Exception;

    //    保存
    void insertCTestItem(PageData pageData) throws Exception;

    //    修改
    void updateCTestItem(PageData pageData) throws Exception;

    //    删除
    void deleteCTestItemByIds(PageData pageData) throws Exception;
}
