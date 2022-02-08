package com.jzysoft.web.controller.system.cClassStu;

import com.jzysoft.commonmoudle.lib.config.page.Page;
import com.jzysoft.commonmoudle.lib.config.page.PageData;
import com.jzysoft.commonmoudle.lib.util.BaseJQController;
import com.jzysoft.commonmoudle.lib.util.RJQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 【请填写功能名称】Controller
 *
 * @author jzysoft
 * @date 2022-02-08
 */
@Controller
@RequestMapping("/cClassStu/cClassStu")
public class CClassStuController extends BaseJQController {
    private String prefix = "moudles/cClassStu";
    @Autowired
        CClassStuService cClassStuService;

    @RequestMapping(value = "")
    public String CClassStu() {
        return prefix + "/cClassStu";
    }

    /**
     * 列表
     * sxd
     */
    @ResponseBody
    @GetMapping("/list")
    public RJQ selCClassStuPage(Page page) throws Exception {
        PageData pageData = this.getPageData();
        page.setPd(pageData);
        List<PageData> list = cClassStuService.selectCClassStuList(page);
        return RJQ.ok().put("page", page).put("data", list);
    }

    /**
     * 根据id获取数据${tableComment}
     * sxd
     */
    @ResponseBody
    @GetMapping("/findByid")
    public RJQ getCClassStuInfo() throws Exception {
        PageData pageData = this.getPageData();
        PageData data = cClassStuService.selectCClassStuById(pageData);
        return RJQ.ok().put("data", data);
    }

    /**
     * 新增${tableComment}
     * sxd
     */
    @ResponseBody
    @PostMapping("/adddata")
    public RJQ saveCClassStuData(@RequestBody PageData pageData) throws Exception {
            cClassStuService.insertCClassStu(pageData);
        return RJQ.ok();
    }

    /**
     * 修改${tableComment}
     * sxd
     */
    @ResponseBody
    @PostMapping("/updatedata")
    public RJQ editCClassStuData(@RequestBody PageData pageData) throws Exception {
            cClassStuService.updateCClassStu(pageData);
        return RJQ.ok();
    }

    /**
     * 批量删除${tableComment}
     * sxd
     */
    @ResponseBody
    @PostMapping("/del")
    public RJQ delCClassStuDataList(@RequestBody Integer[] ids) throws Exception {
        PageData pageData = this.getPageData();
        for (Integer id : ids) {
            pageData.put("id", id);
                cClassStuService.deleteCClassStuByIds(pageData);
        }
        return RJQ.ok();
    }


}
