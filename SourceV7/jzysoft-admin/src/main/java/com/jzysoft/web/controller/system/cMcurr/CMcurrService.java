package com.jzysoft.web.controller.system.cMcurr;

import com.jzysoft.commonmoudle.lib.config.page.Page;
import com.jzysoft.commonmoudle.lib.config.page.PageData;

import java.util.List;


/**
 * 【请填写功能名称】Service接口
 *
 * @author jzysoft
 * @date 2022-02-08
 */
public interface CMcurrService
{
    //    列表
    List<PageData> selectCMcurrList(Page page) throws Exception;
    //    id
    PageData selectCMcurrById(PageData pageData) throws Exception;

    //    保存
    void insertCMcurr(PageData pageData) throws Exception;

    //    修改
    void updateCMcurr(PageData pageData) throws Exception;

    //    删除
    void deleteCMcurrByIds(PageData pageData) throws Exception;

    List<PageData> listBystudentlistpage(Page page) throws Exception;
}
