package com.jzysoft.web.controller.system.cClassStu;

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
public class CClassStuServiceImpl implements CClassStuService
{
    @Autowired
    DaoSupport daoSupport;

    //    列表
    @Override
    public List<PageData> selectCClassStuList(Page page) throws Exception {
        return (List<PageData>) daoSupport.findForList("CClassStuMapper.selectCClassStulistPage", page);
    }
    //    id
    @Override
    public PageData selectCClassStuById(PageData pageData) throws Exception {
        return (PageData) daoSupport.findForObject("CClassStuMapper.selectCClassStuById", pageData);
    }
    //    保存
    @Override
    public void insertCClassStu(PageData pageData) throws Exception {
        daoSupport.save("CClassStuMapper.insertCClassStu", pageData);
    }

    //    修改
    @Override
    public void updateCClassStu(PageData pageData) throws Exception {
        daoSupport.update("CClassStuMapper.updateCClassStu", pageData);
    }

    //    删除
    @Override
    public void deleteCClassStuByIds(PageData pageData) throws Exception {
        daoSupport.delete("CClassStuMapper.deleteCClassStuById", pageData);
    }
}
