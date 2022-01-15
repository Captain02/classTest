package com.jzysoft.module.testdemo;

import com.jzysoft.commonmoudle.lib.config.page.DaoSupport;
import com.jzysoft.commonmoudle.lib.config.page.Page;
import com.jzysoft.commonmoudle.lib.config.page.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 测试Service业务层处理
 *
 * @author jzysoft
 * @date 2020-07-10
 */
@Service
public class TestdemoServiceImpl implements TestdemoService
{
    @Autowired
    DaoSupport daoSupport;

    //    列表
    @Override
    public List<PageData> selectTestdemoList(Page page) throws Exception {
        return (List<PageData>) daoSupport.findForList("TestdemoMapper.selectTestdemolistPage", page);
    }
    //    id
    @Override
    public PageData selectTestdemoById(PageData pageData) throws Exception {
        return (PageData) daoSupport.findForObject("TestdemoMapper.selectTestdemoById", pageData);
    }
    //    保存
    @Override
    public void insertTestdemo(PageData pageData) throws Exception {
        daoSupport.save("TestdemoMapper.insertTestdemo", pageData);
    }

    //    修改
    @Override
    public void updateTestdemo(PageData pageData) throws Exception {
        daoSupport.update("TestdemoMapper.updateTestdemo", pageData);
    }

    //    删除
    @Override
    public void deleteTestdemoByIds(PageData pageData) throws Exception {
        daoSupport.delete("TestdemoMapper.deleteTestdemoById", pageData);
    }
}
