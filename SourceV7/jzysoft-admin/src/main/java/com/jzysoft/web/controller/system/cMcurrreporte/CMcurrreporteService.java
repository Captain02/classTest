package com.jzysoft.web.controller.system.cMcurrreporte;

import com.jzysoft.commonmoudle.lib.config.page.Page;
import com.jzysoft.commonmoudle.lib.config.page.PageData;

import java.util.List;


/**
 * 【请填写功能名称】Service接口
 *
 * @author jzysoft
 * @date 2022-02-15
 */
public interface CMcurrreporteService
{
    //    列表
    List<PageData> selectCMcurrreporteList(Page page) throws Exception;
    //    id
    PageData selectCMcurrreporteById(PageData pageData) throws Exception;

    //    保存
    void insertCMcurrreporte(PageData pageData) throws Exception;

    //    修改
    void updateCMcurrreporte(PageData pageData) throws Exception;

    //    删除
    void deleteCMcurrreporteByIds(PageData pageData) throws Exception;

    List<MCurrEntity> exportReport() throws Exception;
}
