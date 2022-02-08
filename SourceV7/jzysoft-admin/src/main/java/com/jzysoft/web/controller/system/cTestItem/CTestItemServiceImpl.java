package com.jzysoft.web.controller.system.cTestItem;

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
public class CTestItemServiceImpl implements CTestItemService
{
    @Autowired
    DaoSupport daoSupport;

    //    列表
    @Override
    public List<PageData> selectCTestItemList(Page page) throws Exception {
        return (List<PageData>) daoSupport.findForList("CTestItemMapper.selectCTestItemlistPage", page);
    }
    //    id
    @Override
    public PageData selectCTestItemById(PageData pageData) throws Exception {
        return (PageData) daoSupport.findForObject("CTestItemMapper.selectCTestItemById", pageData);
    }
    //    保存
    @Override
    public void insertCTestItem(PageData pageData) throws Exception {
        daoSupport.save("CTestItemMapper.insertCTestItem", pageData);
    }

    //    修改
    @Override
    public void updateCTestItem(PageData pageData) throws Exception {
        daoSupport.update("CTestItemMapper.updateCTestItem", pageData);
    }

    //    删除
    @Override
    public void deleteCTestItemByIds(PageData pageData) throws Exception {
        daoSupport.delete("CTestItemMapper.deleteCTestItemById", pageData);
    }
}
