package com.jzysoft.web.controller.system.cClass;

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
public class CClassServiceImpl implements CClassService
{
    @Autowired
    DaoSupport daoSupport;

    //    列表
    @Override
    public List<PageData> selectCClassList(Page page) throws Exception {
        return (List<PageData>) daoSupport.findForList("CClassMapper.selectCClasslistPage", page);
    }
    //    id
    @Override
    public PageData selectCClassById(PageData pageData) throws Exception {
        return (PageData) daoSupport.findForObject("CClassMapper.selectCClassById", pageData);
    }
    //    保存
    @Override
    public void insertCClass(PageData pageData) throws Exception {
        daoSupport.save("CClassMapper.insertCClass", pageData);
    }

    //    修改
    @Override
    public void updateCClass(PageData pageData) throws Exception {
        daoSupport.update("CClassMapper.updateCClass", pageData);
    }

    //    删除
    @Override
    public void deleteCClassByIds(PageData pageData) throws Exception {
        daoSupport.delete("CClassMapper.deleteCClassById", pageData);
    }

    @Override
    public void joinStu(PageData pageData) throws Exception {
        daoSupport.save("CClassMapper.joinStu",pageData);
    }


}
