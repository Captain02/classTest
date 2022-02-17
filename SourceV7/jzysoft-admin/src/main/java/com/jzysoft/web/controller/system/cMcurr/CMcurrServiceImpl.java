package com.jzysoft.web.controller.system.cMcurr;

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
 * @date 2022-02-08
 */
@Service
public class CMcurrServiceImpl implements CMcurrService
{
    @Autowired
    DaoSupport daoSupport;

    //    列表
    @Override
    public List<PageData> selectCMcurrList(Page page) throws Exception {
        return (List<PageData>) daoSupport.findForList("CMcurrMapper.selectCMcurrlistPage", page);
    }
    //    id
    @Override
    public PageData selectCMcurrById(PageData pageData) throws Exception {
        return (PageData) daoSupport.findForObject("CMcurrMapper.selectCMcurrById", pageData);
    }
    //    保存
    @Override
    public void insertCMcurr(PageData pageData) throws Exception {
        daoSupport.save("CMcurrMapper.insertCMcurr", pageData);
    }

    //    修改
    @Override
    public void updateCMcurr(PageData pageData) throws Exception {
        daoSupport.update("CMcurrMapper.updateCMcurr", pageData);
    }

    //    删除
    @Override
    public void deleteCMcurrByIds(PageData pageData) throws Exception {
        daoSupport.delete("CMcurrMapper.deleteCMcurrById", pageData);
    }

    @Override
    public List<PageData> listBystudentlistpage(Page page) throws Exception {
        return (List<PageData>) daoSupport.findForList("CMcurrMapper.listBystudentlistPage", page);

    }
}
