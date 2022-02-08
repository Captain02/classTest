package com.jzysoft.web.controller.system.cTest;

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
public class CTestServiceImpl implements CTestService
{
    @Autowired
    DaoSupport daoSupport;

    //    列表
    @Override
    public List<PageData> selectCTestList(Page page) throws Exception {
        return (List<PageData>) daoSupport.findForList("CTestMapper.selectCTestlistPage", page);
    }
    //    id
    @Override
    public PageData selectCTestById(PageData pageData) throws Exception {
        return (PageData) daoSupport.findForObject("CTestMapper.selectCTestById", pageData);
    }
    //    保存
    @Override
    public void insertCTest(PageData pageData) throws Exception {
        daoSupport.save("CTestMapper.insertCTest", pageData);
    }

    //    修改
    @Override
    public void updateCTest(PageData pageData) throws Exception {
        daoSupport.update("CTestMapper.updateCTest", pageData);
    }

    //    删除
    @Override
    public void deleteCTestByIds(PageData pageData) throws Exception {
        daoSupport.delete("CTestMapper.deleteCTestById", pageData);
    }
}
