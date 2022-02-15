package com.jzysoft.web.controller.system.cVideoreporte;

import com.jzysoft.commonmoudle.lib.config.page.Page;
import com.jzysoft.commonmoudle.lib.config.page.PageData;

import java.util.List;


/**
 * 【请填写功能名称】Service接口
 *
 * @author jzysoft
 * @date 2022-02-15
 */
public interface CVideoreporteService
{
    //    列表
    List<PageData> selectCVideoreporteList(Page page) throws Exception;
    //    id
    PageData selectCVideoreporteById(PageData pageData) throws Exception;

    //    保存
    void insertCVideoreporte(PageData pageData) throws Exception;

    //    修改
    void updateCVideoreporte(PageData pageData) throws Exception;

    //    删除
    void deleteCVideoreporteByIds(PageData pageData) throws Exception;
}
