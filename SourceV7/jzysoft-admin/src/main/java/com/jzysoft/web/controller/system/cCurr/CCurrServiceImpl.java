package com.jzysoft.web.controller.system.cCurr;

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
public class CCurrServiceImpl implements CCurrService
{
    @Autowired
    DaoSupport daoSupport;

    //    列表
    @Override
    public List<PageData> selectCCurrList(Page page) throws Exception {
        return (List<PageData>) daoSupport.findForList("CCurrMapper.selectCCurrlistPage", page);
    }
    //    id
    @Override
    public PageData selectCCurrById(PageData pageData) throws Exception {
        return (PageData) daoSupport.findForObject("CCurrMapper.selectCCurrById", pageData);
    }
    //    保存
    @Override
    public void insertCCurr(PageData pageData) throws Exception {
        daoSupport.save("CCurrMapper.insertCCurr", pageData);
    }

    //    修改
    @Override
    public void updateCCurr(PageData pageData) throws Exception {
        daoSupport.update("CCurrMapper.updateCCurr", pageData);
    }

    //    删除
    @Override
    public void deleteCCurrByIds(PageData pageData) throws Exception {
        daoSupport.delete("CCurrMapper.deleteCCurrById", pageData);
    }
}
