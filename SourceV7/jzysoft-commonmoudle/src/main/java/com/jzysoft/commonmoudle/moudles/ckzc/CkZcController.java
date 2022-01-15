package

        com.jzysoft.commonmoudle.moudles.ckzc;


import com.jzysoft.common.core.domain.Ztree;
import com.jzysoft.commonmoudle.lib.config.page.Page;
import com.jzysoft.commonmoudle.lib.config.page.PageData;
import com.jzysoft.commonmoudle.lib.util.BaseJQController;
import com.jzysoft.commonmoudle.lib.util.RJQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 测控组成 信息操作处理
 *
 * @author sxd
 * @date 2020-03-28
 */
@Controller
@RequestMapping("/ckZc/ckzc")
public class CkZcController extends BaseJQController //BaseController
{
    private String prefix = "moudles/ckZc";

    @Autowired
      CkZcService ckZcService;


    @RequestMapping(value = "")
    public String ckZc() {
        return prefix + "/ckZc";
    }
    @RequestMapping(value = "/alarm")
    public String ckZcx() {
        return prefix + "/alarm";
    }

    @ResponseBody
    @GetMapping("/list")
    public RJQ listdata() throws Exception {
        PageData pageData = this.getPageData();
        List<PageData> listdata = new ArrayList<>();
        if (pageData.get("Name") != null ||pageData.get("CKZCLXID") != null ) {

            List<PageData> listroot = ckZcService.selecttrees(pageData);
            for (PageData data : listroot) {
                listdata.add(data);
            }
        } else {

            pageData.put("ParentId", 0);

            List<PageData> listroot = ckZcService.selecttree(pageData);
            for (int i = 0; i < listroot.size(); i++) {
                PageData node_root = (PageData) listroot.get(i);
                listdata.add(node_root);
                getChildren(listdata, node_root);
            }

        }
        System.out.println(listdata);
        return RJQ.ok().put("data", listdata);
    }


    public void getChildren(List<PageData> listdata, PageData node) throws Exception {
        //            得到其子级id
        PageData pageData = new PageData();
        pageData.put("ParentId", node.get("ID"));
        List<PageData> children = ckZcService.selecttree(pageData);
        if (children != null && children.size() > 0) {
            for (int i = 0; i < children.size(); i++) {
                PageData node_child = (PageData) children.get(i);
                listdata.add(node_child);
                getChildren(listdata, node_child);
            }
        }
    }


    /**
     * 列表
     * sxd
     */
    @ResponseBody
    @GetMapping("/lists")
    public RJQ selckZcPage(Page page) throws Exception {
        PageData pageData = this.getPageData();
        page.setPd(pageData);
        List<PageData> list = ckZcService.selectCkZcList(page);
        return RJQ.ok().put("page", page).put("data", list);
    }

    /**
     * 根据id获取数据测控组成
     * sxd
     */
    @ResponseBody
    @GetMapping("/findByid")
    public RJQ getckZcInfo() throws Exception {
        PageData pageData = this.getPageData();
        PageData data = ckZcService.selectCkZcById(pageData);
//        byte[] points = (byte[]) data.get("Points");
//        if (points != null) {
//            String s = new String(points, 0, points.length);
//            data.put("Pointsz", s);
//        }
        data.put("ParentIdS", pageData.get("ID"));
        PageData pageData1 = ckZcService.selectParentName(data);
        if (pageData1 != null) {
            data.put("Pame", pageData1.get("Pame"));
        }
        return RJQ.ok().put("data", data);
    }

    /**
     * 新增测控组成
     * sxd
     */
    @ResponseBody
    @PostMapping("/adddata")
    public RJQ saveckZcData(@RequestBody PageData pageData) throws Exception {
        //        获取用户信息
        PageData pageData1 = new PageData();
//        判断是否有父级ID
        if (!pageData.getString("ParentID").equals("")) {
            pageData1.put("ID", pageData.getString("ParentID"));
            PageData pageData2 = ckZcService.selectCkZcById(pageData1);
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

                ckZcService.updateCkZc(pageData3);
            }

        } else {
            pageData.put("ParentID", "0");
            pageData.put("ParentIDS", "0");
            pageData.put("level", "0");

        }

        ckZcService.insertCkZc(pageData);
        return RJQ.ok();
    }


    public PageData saveckZcDatas( PageData pageData) throws Exception {
        //        获取用户信息

        PageData pageData1 = new PageData();
//        判断是否有父级ID
        if (!pageData.getString("ParentID").equals("")) {
            pageData1.put("ID", pageData.getString("ParentID"));
            PageData pageData2 = ckZcService.selectCkZcById(pageData1);
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

                ckZcService.updateCkZc(pageData3);
            }

        } else {
            pageData.put("ParentID", "0");
            pageData.put("ParentIDS", "0");
            pageData.put("level", "0");

        }

        ckZcService.insertCkZc(pageData);
        return pageData;
    }
    /**
     * 修改测控组成
     * sxd
     */
    @ResponseBody
    @PostMapping("/updatedata")
    public RJQ editckZcData(@RequestBody PageData pageData) throws Exception {
        PageData pageData1 = new PageData();
        //取得登录人信息

        pageData1.put("ID", pageData.getString("ParentID"));
//        根据父级id查询是否有子级id
        PageData pageData2 = ckZcService.selectCkZcById(pageData1);
//        如果没有
        if (pageData2 != null) {
            pageData.put("level", pageData2.getInteger("level") + 1);
            pageData.put("ParentIDS", pageData2.getString("ParentIDS") + "," + pageData2.getString("ID"));

            PageData pageData6 = new PageData();

            pageData6.put("ID", pageData.getString("ParentID"));
            pageData6.put("is_leaf", "false");
//            把父级改为不是最下级
            ckZcService.updateCkZc(pageData6);
        }


//        查询其父级id
        PageData pageData3 = ckZcService.selectCkZcById(pageData);
        PageData pageData4 = new PageData();
        int parent = pageData3.getInteger("parent");
        int parentID = pageData.getInteger("ParentID");
        pageData4.put("ID", pageData3.get("parent"));
        if (parent != parentID) {
            //        根据父级id查询其是否还有子级
            List<PageData> pageData5 = ckZcService.selectParent(pageData4);
//        如果没有就改变
            if (pageData5.size() == 1) {
                pageData4.put("is_leaf", "true");
            }
        }
//        修改本身

        ckZcService.updateCkZc(pageData);
//        修改以前的父级
        ckZcService.updateCkZc(pageData4);
        return RJQ.ok();
    }

    /**
     * 批量删除测控组成
     * sxd
     */
    @ResponseBody
    @GetMapping("/del")
    public RJQ delckZcDataList() throws Exception {
        PageData pageData = this.getPageData();
//        得到所有子级信息
        List<PageData> pageData1 = ckZcService.selectParent(pageData);
//        查询自己的所有信息
        PageData pageData3 = ckZcService.selectCkZcById(pageData);
        PageData pageData4 = new PageData();
//        放入父级id
        pageData4.put("ID", pageData3.get("parent"));
//        父级还有多少子级
        List<PageData> pageData5 = ckZcService.selectParent(pageData4);

        if (!pageData1.isEmpty()) {
            pageData.put("msg", false);
        } else {
//            如果查询的数量等于
            if (pageData5.size() == 1) {

                PageData pageData2 = new PageData();
//                放入父级id
                pageData2.put("ID", pageData3.get("parent"));
                pageData2.put("is_leaf", "true");
                ckZcService.updateCkZc(pageData2);

            }

            ckZcService.deleteCkZcByIds(pageData);
        }
        return RJQ.ok().put("data", pageData);
    }

    //    建筑组成树
    @GetMapping("/selectProjectTreejz/{ID}")
    public String selectDeptTreejz(@PathVariable("ID") Long ID, ModelMap mmap) throws Exception {

        mmap.put("dept", ckZcService.selectProjectById(ID));
        return prefix + "/treeckzc";
    }
//    moudles/xtJzZclxJc


    /**
     * 加载建筑组成列表树
     */
    @GetMapping("/treejzData")
    @ResponseBody
    public List<Ztree> treejzData() throws Exception {
        List<Ztree> ztrees = ckZcService.selectProjecttreejz(new PageData());

        return ztrees;
    }

}
