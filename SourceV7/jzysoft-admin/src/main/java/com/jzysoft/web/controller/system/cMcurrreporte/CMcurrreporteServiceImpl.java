package com.jzysoft.web.controller.system.cMcurrreporte;

import com.jzysoft.commonmoudle.lib.config.page.DaoSupport;
import com.jzysoft.commonmoudle.lib.config.page.Page;
import com.jzysoft.commonmoudle.lib.config.page.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author jzysoft
 * @date 2022-02-15
 */
@Service
public class CMcurrreporteServiceImpl implements CMcurrreporteService
{
    @Autowired
    DaoSupport daoSupport;

    //    列表
    @Override
    public List<PageData> selectCMcurrreporteList(Page page) throws Exception {
        return (List<PageData>) daoSupport.findForList("CMcurrreporteMapper.selectCMcurrreportelistPage", page);
    }
    //    id
    @Override
    public PageData selectCMcurrreporteById(PageData pageData) throws Exception {
        return (PageData) daoSupport.findForObject("CMcurrreporteMapper.selectCMcurrreporteById", pageData);
    }
    //    保存
    @Override
    public void insertCMcurrreporte(PageData pageData) throws Exception {
        daoSupport.save("CMcurrreporteMapper.insertCMcurrreporte", pageData);
    }

    //    修改
    @Override
    public void updateCMcurrreporte(PageData pageData) throws Exception {
        daoSupport.update("CMcurrreporteMapper.updateCMcurrreporte", pageData);
    }

    //    删除
    @Override
    public void deleteCMcurrreporteByIds(PageData pageData) throws Exception {
        daoSupport.delete("CMcurrreporteMapper.deleteCMcurrreporteById", pageData);
    }

    @Override
    public List<MCurrEntity> exportReport() throws Exception {
        return (List<MCurrEntity>) daoSupport.findForList("CMcurrreporteMapper.exportReport",new PageData());
    }
}
