package com.jzysoft.commonmoudle.moudles.ckZclxJc;


import com.jzysoft.commonmoudle.lib.config.page.DaoSupport;
import com.jzysoft.commonmoudle.lib.config.page.Page;
import com.jzysoft.commonmoudle.lib.config.page.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 参见"测控组成类型"状态图 服务层实现
 *
 * @author zyc
 * @date 2020-03-28
 */
@Service("ckZclxJcService")
public class CkZclxJcServiceImpl implements CkZclxJcService {
    @Autowired
    DaoSupport daoSupport;

    //    列表
    @Override
    public List<PageData> selectCkZclxJcList(Page page) throws Exception {
        return (List<PageData>) daoSupport.findForList("CkZclxJcMapper.selectCkZclxJclistPage", page);
    }
    //    id
    @Override
    public PageData selectCkZclxJcById(PageData pageData) throws Exception {
        return (PageData) daoSupport.findForObject("CkZclxJcMapper.selectCkZclxJcById", pageData);
    }
    //    保存
    @Override
    public void insertCkZclxJc(PageData pageData) throws Exception {
        daoSupport.save("CkZclxJcMapper.insertCkZclxJc", pageData);

    }

    //    修改
    @Override
    public void updateCkZclxJc(PageData pageData) throws Exception {
        daoSupport.update("CkZclxJcMapper.updateCkZclxJc", pageData);
    }

    //    删除
    @Override
    public void deleteCkZclxJcByIds(PageData pageData) throws Exception {
        daoSupport.delete("CkZclxJcMapper.deleteCkZclxJcById", pageData);
    }

}
