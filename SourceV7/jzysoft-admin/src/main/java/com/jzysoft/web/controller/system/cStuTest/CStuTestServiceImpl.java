package com.jzysoft.web.controller.system.cStuTest;

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
public class CStuTestServiceImpl implements CStuTestService
{
    @Autowired
    DaoSupport daoSupport;

    //    列表
    @Override
    public List<PageData> selectCStuTestList(Page page) throws Exception {
        return (List<PageData>) daoSupport.findForList("CStuTestMapper.selectCStuTestlistPage", page);
    }
    //    id
    @Override
    public PageData selectCStuTestById(PageData pageData) throws Exception {
        return (PageData) daoSupport.findForObject("CStuTestMapper.selectCStuTestById", pageData);
    }
    //    保存
    @Override
    public void insertCStuTest(PageData pageData) throws Exception {
        daoSupport.save("CStuTestMapper.insertCStuTest", pageData);
    }

    //    修改
    @Override
    public void updateCStuTest(PageData pageData) throws Exception {
        daoSupport.update("CStuTestMapper.updateCStuTest", pageData);
    }

    //    删除
    @Override
    public void deleteCStuTestByIds(PageData pageData) throws Exception {
        daoSupport.delete("CStuTestMapper.deleteCStuTestById", pageData);
    }
}
