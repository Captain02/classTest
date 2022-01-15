package com.jzysoft.commonmoudle.moudles.ckzc;


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
 * 测控组成 服务层实现
 *
 * @author zyc
 * @date 2020-03-28
 */
@Service("ckZcService")
public class CkZcServiceImpl implements CkZcService {
    @Autowired
    DaoSupport daoSupport;

    //    列表
    @Override
    public List<PageData> selectCkZcList(Page page) throws Exception {
        return (List<PageData>) daoSupport.findForList("CkZcMapper.selectCkZclistPage", page);
    }

    //    id
    @Override
    public PageData selectCkZcById(PageData pageData) throws Exception {
        return (PageData) daoSupport.findForObject("CkZcMapper.selectCkZcById", pageData);
    }

    @Override
    public List<PageData> selectParent(PageData pageData) throws Exception {
        return (List<PageData>) daoSupport.findForList("CkZcMapper.selectParent", pageData);
    }

    //    建筑组成树
    @Override
    public PageData selectParentName(PageData pageData) throws Exception {
        return (PageData) daoSupport.findForObject("CkZcMapper.selectParentName", pageData);
    }

    @Override
    public List<PageData> selecttree(PageData pageData) throws Exception {
        return (List<PageData>) daoSupport.findForList("CkZcMapper.selecttree", pageData);
    }

    @Override
    public List<PageData> selecttrees(PageData pageData) throws Exception {
        return (List<PageData>) daoSupport.findForList("CkZcMapper.selecttrees", pageData);
    }

    //    保存
    @Override
    public void insertCkZc(PageData pageData) throws Exception {
        daoSupport.save("CkZcMapper.insertCkZc", pageData);
    }

    //    保存
    @Override
    public PageData insertCkZcByid(PageData pageData) throws Exception {
        PageData pageData1 = new PageData();
//        判断是否有父级ID
        if (!pageData.getString("ParentID").equals("")) {
            pageData1.put("ID", pageData.getString("ParentID"));
            PageData pageData2 = selectCkZcById(pageData1);
            if (pageData2 != null) {
                //            判断父级是否有父级
                if (!pageData2.getString("FullName").equals("")) {

                    pageData.put("FullName", pageData2.getString("FullName") + "/" + pageData.getString("Name"));
                } else {
                    pageData.put("FullName", pageData2.getString("Name") + "/" + pageData.getString("Name"));

                }

                pageData.put("ParentIDS", pageData2.getString("ParentIDS") + "," + pageData2.getString("ID"));
                pageData.put("level", pageData2.getInteger("level") + 1);
                PageData pageData3 = new PageData();
                pageData3.put("ID", pageData2.get("ID"));
                pageData3.put("is_leaf", "false");

                updateCkZc(pageData3);
            }

        } else {
            pageData.put("ParentID", "0");
            pageData.put("ParentIDS", "0");
            pageData.put("level", "0");

        }
        daoSupport.save("CkZcMapper.insertCkZc", pageData);
        PageData pageData2 = new PageData();
        pageData2.put("InOrder",pageData.get("ID"));
        pageData2.put("ID",pageData.get("ID"));
        updateCkZc(pageData2);
        return pageData;
    }

    //    修改
    @Override
    public void updateCkZc(PageData pageData) throws Exception {
        daoSupport.update("CkZcMapper.updateCkZc", pageData);
    }

    //    删除
    @Override
    public void deleteCkZcByIds(PageData pageData) throws Exception {
        daoSupport.delete("CkZcMapper.deleteCkZcById", pageData);
    }

    //    工程树
    @Override
    public PageData selectProjectById(Long ID) throws Exception {
        return (PageData) daoSupport.findForObject("CkZcMapper.selectProjectById", ID);
    }


    //    列表
    @Override
    public List<Ztree> selectProjecttreejz(PageData pageData) throws Exception {
        List<PageData> pageData1 = (List<PageData>) daoSupport.findForList("CkZcMapper.selectxtProjecttree", pageData);
        List<Ztree> ztrees = initZtree(pageData1, null);

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
