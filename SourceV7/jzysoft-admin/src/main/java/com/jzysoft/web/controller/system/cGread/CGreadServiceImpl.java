package com.jzysoft.web.controller.system.cGread;

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
public class CGreadServiceImpl implements CGreadService
{
    @Autowired
    DaoSupport daoSupport;

    //    列表
    @Override
    public List<PageData> selectCGreadList(Page page) throws Exception {
        return (List<PageData>) daoSupport.findForList("CGreadMapper.selectCGreadlistPage", page);
    }
    //    id
    @Override
    public PageData selectCGreadById(PageData pageData) throws Exception {
        return (PageData) daoSupport.findForObject("CGreadMapper.selectCGreadById", pageData);
    }
    //    保存
    @Override
    public void insertCGread(PageData pageData) throws Exception {
        daoSupport.save("CGreadMapper.insertCGread", pageData);
    }

    //    修改
    @Override
    public void updateCGread(PageData pageData) throws Exception {
        daoSupport.update("CGreadMapper.updateCGread", pageData);
    }

    //    删除
    @Override
    public void deleteCGreadByIds(PageData pageData) throws Exception {
        daoSupport.delete("CGreadMapper.deleteCGreadById", pageData);
    }
}
