package com.jzysoft.commonmoudle.moudles.xtJzZclxJc;


import com.jzysoft.commonmoudle.lib.config.page.Page;
import com.jzysoft.commonmoudle.lib.config.page.PageData;
import com.jzysoft.commonmoudle.lib.util.BaseJQController;
import com.jzysoft.commonmoudle.lib.util.RJQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.github.pagehelper.page.PageMethod.startPage;

/**
 * 建筑组成类型：道路、车道Controller
 *
 * @author jzysoft
 * @date 2019-10-15
 */
@Controller
@RequestMapping("/xtJzZclxJc/xtJzZclxJc")
public class XtJzZclxJcController extends BaseJQController {
    private String prefix = "moudles/xtJzZclxJc";
    @Autowired
    XtJzZclxJcService xtJzZclxJcService;

    @RequestMapping(value = "")
    public String XtJzZclxJc() {
        return prefix + "/xtJzZclxJc";
    }

    /**
     * 列表
     * sxd
     */
    @ResponseBody
    @GetMapping("/list")
    public RJQ selXtJzZclxJcPage(Page page, HttpServletRequest request) throws Exception {

        PageData pageData = this.getPageData();

        page.setPd(pageData);

        List<PageData> list = xtJzZclxJcService.selectXtJzZclxJcList(page);
        return RJQ.ok().put("page", page).put("data", list);
    }

    /**
     * 根据id获取数据${tableComment}
     * sxd
     */
    @ResponseBody
    @GetMapping("/findByid")
    public RJQ getXtJzZclxJcInfo() throws Exception {
        PageData pageData = this.getPageData();
        PageData data = xtJzZclxJcService.selectXtJzZclxJcById(pageData);
        return RJQ.ok().put("data", data);
    }

    /**
     * 新增${tableComment}
     * sxd
     */
    @ResponseBody
    @PostMapping("/adddata")
    public RJQ saveXtJzZclxJcData(@RequestBody PageData pageData) throws Exception {

        xtJzZclxJcService.insertXtJzZclxJc(pageData);
        return RJQ.ok();
    }

    /**
     * 修改${tableComment}
     * sxd
     */
    @ResponseBody
    @PostMapping("/updatedata")
    public RJQ editXtJzZclxJcData(@RequestBody PageData pageData) throws Exception {
        xtJzZclxJcService.updateXtJzZclxJc(pageData);
        return RJQ.ok();
    }

    /**
     * 批量删除${tableComment}
     * sxd
     */
    @ResponseBody
    @PostMapping("/del")
    public RJQ delXtJzZclxJcDataList(@RequestBody Integer[] ids) throws Exception {
        PageData pageData = this.getPageData();
        for (Integer id : ids) {
            pageData.put("ID", id);
            xtJzZclxJcService.deleteXtJzZclxJcByIds(pageData);
        }
        return RJQ.ok();
    }

    public static void main(String[] args) {
        String x = "XtJzZclxJcMapper.selectXtJzZclxJc";
        boolean count = x.endsWith("_COUNT");
        System.out.println(count);
    }
}
