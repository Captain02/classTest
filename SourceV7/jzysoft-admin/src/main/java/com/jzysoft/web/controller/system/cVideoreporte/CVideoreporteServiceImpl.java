package com.jzysoft.web.controller.system.cVideoreporte;

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
public class CVideoreporteServiceImpl implements CVideoreporteService
{
    @Autowired
    DaoSupport daoSupport;

    //    列表
    @Override
    public List<PageData> selectCVideoreporteList(Page page) throws Exception {
        return (List<PageData>) daoSupport.findForList("CVideoreporteMapper.selectCVideoreportelistPage", page);
    }
    //    id
    @Override
    public PageData selectCVideoreporteById(PageData pageData) throws Exception {
        return (PageData) daoSupport.findForObject("CVideoreporteMapper.selectCVideoreporteById", pageData);
    }
    //    保存
    @Override
    public void insertCVideoreporte(PageData pageData) throws Exception {
        daoSupport.save("CVideoreporteMapper.insertCVideoreporte", pageData);
    }

    //    修改
    @Override
    public void updateCVideoreporte(PageData pageData) throws Exception {
        daoSupport.update("CVideoreporteMapper.updateCVideoreporte", pageData);
    }

    //    删除
    @Override
    public void deleteCVideoreporteByIds(PageData pageData) throws Exception {
        daoSupport.delete("CVideoreporteMapper.deleteCVideoreporteById", pageData);
    }

    @Override
    public List<VideoEntity> exportReport() throws Exception {
        return (List<VideoEntity>) daoSupport.findForList("CVideoreporteMapper.exportReport", new PageData());
    }
}
