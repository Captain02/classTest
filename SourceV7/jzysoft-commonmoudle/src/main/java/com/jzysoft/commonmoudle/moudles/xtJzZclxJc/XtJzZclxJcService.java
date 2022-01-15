package com.jzysoft.commonmoudle.moudles.xtJzZclxJc;


import com.jzysoft.common.core.domain.Ztree;
import com.jzysoft.commonmoudle.lib.config.page.Page;
import com.jzysoft.commonmoudle.lib.config.page.PageData;

import java.util.List;

/**
 * 建筑组成类型：道路、车道Service接口
 *
 * @author jzysoft
 * @date 2019-10-15
 */
public interface XtJzZclxJcService {
    //    列表
    List<PageData> selectXtJzZclxJcList(Page page) throws Exception;

    //    id
    PageData selectXtJzZclxJcById(PageData pageData) throws Exception;

    //    保存
    void insertXtJzZclxJc(PageData pageData) throws Exception;

    //    修改
    void updateXtJzZclxJc(PageData pageData) throws Exception;

    //    删除
    void deleteXtJzZclxJcByIds(PageData pageData) throws Exception;


}
