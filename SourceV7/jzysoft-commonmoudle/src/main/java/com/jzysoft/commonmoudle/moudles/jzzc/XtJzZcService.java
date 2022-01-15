package com.jzysoft.commonmoudle.moudles.jzzc;




import com.jzysoft.common.core.domain.Ztree;
import com.jzysoft.commonmoudle.lib.config.page.Page;
import com.jzysoft.commonmoudle.lib.config.page.PageData;

import java.util.List;

/**
 * 建筑组成Service接口
 *
 * @author jzysoft
 * @date 2019-10-19
 */
public interface XtJzZcService {
    //    列表
    List<PageData> selectXtJzZcList(Page page) throws Exception;


    List<PageData> selecttree(PageData pageData) throws Exception;

    List<PageData> selecttrees(PageData pageData) throws Exception;



    //    查询父级
    PageData selectParentName(PageData pageData) throws Exception;

    //    id
    PageData selectXtJzZcById(PageData pageData) throws Exception;


    List<PageData> selectParent(PageData pageData) throws Exception;

    //    保存
    void insertXtJzZc(PageData pageData) throws Exception;

    //    修改
    void updateXtJzZc(PageData pageData) throws Exception;

    //    删除
    void deleteXtJzZcByIds(PageData pageData) throws Exception;

    //查询工程
    PageData selectProjectById(Long deptId) throws Exception;


    //建筑树状图
    public List<Ztree> selectProjecttreejz(PageData pageData) throws Exception;

}
