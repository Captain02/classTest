package

        com.jzysoft.commonmoudle.moudles.ckzc;


import com.jzysoft.common.core.domain.Ztree;
import com.jzysoft.commonmoudle.lib.config.page.Page;
import com.jzysoft.commonmoudle.lib.config.page.PageData;

import java.util.List;

/**
 * 测控组成 服务层
 *
 * @author zsd
 * @date 2020-03-28
 */
public interface CkZcService {

    //    列表
    List<PageData> selectCkZcList(Page page) throws Exception;


    List<PageData> selecttree(PageData pageData) throws Exception;

    List<PageData> selecttrees(PageData pageData) throws Exception;

    //    查询父级
    List<PageData> selectParent(PageData pageData) throws Exception;

    //    查询父级
    PageData selectParentName(PageData pageData) throws Exception;

    //    id
    PageData selectCkZcById(PageData pageData) throws Exception;

    //    保存
    void insertCkZc(PageData pageData) throws Exception;

    PageData insertCkZcByid(PageData pageData) throws Exception;

    //    修改
    void updateCkZc(PageData pageData) throws Exception;

    //    删除
    void deleteCkZcByIds(PageData pageData) throws Exception;

    //查询工程
    PageData selectProjectById(Long deptId) throws Exception;


    //建筑树状图
    public List<Ztree> selectProjecttreejz(PageData pageData) throws Exception;

}
