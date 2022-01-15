package com.jzysoft.commonmoudle.moudles.test;

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
 * @date 2020-04-07
 */
@Service
public class TestServiceImpl implements TestService
{
    @Autowired
    DaoSupport daoSupport;

    //    列表
    @Override
    public List<PageData> selectTestList(Page page) throws Exception {
        return (List<PageData>) daoSupport.findForList("TestMapper.selectTestlistPage", page);
    }
    //    id
    @Override
    public PageData selectTestById(PageData pageData) throws Exception {
        return (PageData) daoSupport.findForObject("TestMapper.selectTestById", pageData);
    }
    //    保存
    @Override
    public void insertTest(PageData pageData) throws Exception {
        daoSupport.save("TestMapper.insertTest", pageData);
    }

    //    修改
    @Override
    public void updateTest(PageData pageData) throws Exception {
        daoSupport.update("TestMapper.updateTest", pageData);
    }

    //    删除
    @Override
    public void deleteTestByIds(PageData pageData) throws Exception {
        daoSupport.delete("TestMapper.deleteTestById", pageData);
    }
}
