package com.jzysoft.commonmoudle.moudles.xtJzZclxJc;



import com.jzysoft.commonmoudle.lib.config.page.DaoSupport;
import com.jzysoft.commonmoudle.lib.config.page.Page;
import com.jzysoft.commonmoudle.lib.config.page.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 建筑组成类型：道路、车道Service业务层处理
 *
 * @author jzysoft
 * @date 2019-10-15
 */
@Service
public class XtJzZclxJcServiceImpl implements XtJzZclxJcService {
    @Autowired
    DaoSupport daoSupport;

    //    列表
    @Override
    public List<PageData> selectXtJzZclxJcList(Page page) throws Exception {
        return (List<PageData>) daoSupport.findForList("XtJzZclxJcMapper.selectXtJzZclxJclistPage", page);
    }

    //    id
    @Override
    public PageData selectXtJzZclxJcById(PageData pageData) throws Exception {
        return (PageData) daoSupport.findForObject("XtJzZclxJcMapper.selectXtJzZclxJcById", pageData);
    }

    //    保存
    @Override
    public void insertXtJzZclxJc(PageData pageData) throws Exception {
        daoSupport.save("XtJzZclxJcMapper.insertXtJzZclxJc", pageData);
        PageData pageData1 = new PageData();
        pageData1.put("InOrder", pageData.get("ID"));
        pageData1.put("ID", pageData.get("ID"));
        updateXtJzZclxJc(pageData1);
    }

    //    修改
    @Override
    public void updateXtJzZclxJc(PageData pageData) throws Exception {
        daoSupport.update("XtJzZclxJcMapper.updateXtJzZclxJc", pageData);
    }

    //    删除
    @Override
    public void deleteXtJzZclxJcByIds(PageData pageData) throws Exception {
        daoSupport.delete("XtJzZclxJcMapper.deleteXtJzZclxJcById", pageData);
    }

}
