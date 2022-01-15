package com.jzysoft.commonmoudle.moudles.jzzc;



import com.jzysoft.common.core.domain.Ztree;
import com.jzysoft.common.utils.StringUtils;
import com.jzysoft.commonmoudle.lib.config.page.DaoSupport;
import com.jzysoft.commonmoudle.lib.config.page.Page;
import com.jzysoft.commonmoudle.lib.config.page.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 建筑组成Service业务层处理
 *
 * @author jzysoft
 * @date 2019-10-19
 */
@Service
public class XtJzZcServiceImpl implements XtJzZcService
{
    @Autowired
    DaoSupport daoSupport;

    //    列表
    @Override
    public List<PageData> selectXtJzZcList(Page page) throws Exception {
        return (List<PageData>) daoSupport.findForList("XtJzZcMapper.selectXtJzZclistPage", page);
    }

    @Override
    public List<PageData> selecttree(PageData pageData) throws Exception {
        return (List<PageData>) daoSupport.findForList("XtJzZcMapper.selecttree", pageData);
    }
    @Override
    public List<PageData> selecttrees(PageData pageData) throws Exception {
        return (List<PageData>) daoSupport.findForList("XtJzZcMapper.selecttrees", pageData);
    }


//    建筑组成树
    @Override
    public PageData selectParentName(PageData pageData) throws Exception {
        return (PageData) daoSupport.findForObject("XtJzZcMapper.selectParentName", pageData);
    }
    //    id
    @Override
    public PageData selectXtJzZcById(PageData pageData) throws Exception {
        return (PageData) daoSupport.findForObject("XtJzZcMapper.selectXtJzZcById", pageData);
    }

    @Override
    public List<PageData> selectParent(PageData pageData) throws Exception {
        return (List<PageData>) daoSupport.findForList("XtJzZcMapper.selectParent", pageData);
    }
    //    保存
    @Override
    public void insertXtJzZc(PageData pageData) throws Exception {
        daoSupport.save("XtJzZcMapper.insertXtJzZc", pageData);


        PageData pageData1 = new PageData();
        pageData1.put("InOrder",pageData.get("ID"));
        pageData1.put("ID",pageData.get("ID"));
        updateXtJzZc(pageData1);
    }

    //    修改
    @Override
    public void updateXtJzZc(PageData pageData) throws Exception {
        daoSupport.update("XtJzZcMapper.updateXtJzZc", pageData);
    }

    //    删除
    @Override
    public void deleteXtJzZcByIds(PageData pageData) throws Exception {
        daoSupport.delete("XtJzZcMapper.deleteXtJzZcById", pageData);
    }
    //    工程树
    @Override
    public PageData selectProjectById(Long ID) throws Exception {
        return (PageData) daoSupport.findForObject("XtJzZcMapper.selectProjectById", ID);
    }

    //    列表
    @Override
    public List<Ztree> selectProjecttreejz(PageData pageData) throws Exception {
        List<PageData> pageData1 = (List<PageData>) daoSupport.findForList("XtJzZcMapper.selectxtProjecttree", pageData);
        List<Ztree> ztrees = initZtree(pageData1,null);

        return ztrees;
    }
    public List<Ztree> initZtree(List<PageData> pageData, List<String> roleDeptList) {

        List<Ztree> ztrees = new ArrayList<Ztree>();
        boolean isCheck = StringUtils.isNotNull(roleDeptList);
        for (PageData pageDatas : pageData) {
            Ztree ztree = new Ztree();
            ztree.setId(pageDatas.getLong("ID"));
            ztree.setJzzcId(pageDatas.getLong("JZZCLXID"));
            ztree.setpId(pageDatas.getLong("ParentID"));
            ztree.setName(pageDatas.getString("DirectoryName"));
            ztree.setTitle(pageDatas.getString("DirectoryName"));
            if (isCheck) {
                ztree.setChecked(roleDeptList.contains(pageDatas.getLong("ID") + pageDatas.getString("DirectoryName")));
            }
            ztrees.add(ztree);
        }
        return ztrees;
    }


}
