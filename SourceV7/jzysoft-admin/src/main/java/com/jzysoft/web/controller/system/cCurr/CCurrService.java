package com.jzysoft.web.controller.system.cCurr;

import com.jzysoft.commonmoudle.lib.config.page.Page;
import com.jzysoft.commonmoudle.lib.config.page.PageData;

import java.util.List;


/**
 * 【请填写功能名称】Service接口
 *
 * @author jzysoft
 * @date 2022-02-08
 */
public interface CCurrService
{
    //    列表
    List<PageData> selectCCurrList(Page page) throws Exception;
    //    id
    PageData selectCCurrById(PageData pageData) throws Exception;

    //    保存
    void insertCCurr(PageData pageData) throws Exception;

    //    修改
    void updateCCurr(PageData pageData) throws Exception;

    //    删除
    void deleteCCurrByIds(PageData pageData) throws Exception;

    List<PageData> selectCurrByMCurrIdList(Page page) throws Exception;
}
