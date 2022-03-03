package com.jzysoft.web.controller.system.cTest;

import com.jzysoft.commonmoudle.lib.config.page.Page;
import com.jzysoft.commonmoudle.lib.config.page.PageData;
import com.jzysoft.commonmoudle.lib.util.BaseJQController;
import com.jzysoft.commonmoudle.lib.util.RJQ;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 【请填写功能名称】Controller
 *
 * @author jzysoft
 * @date 2022-02-08
 */
@Controller
@RequestMapping("/cTest/cTest")
public class CTestController extends BaseJQController {
    private String prefix = "moudles/cTest";
    @Autowired
        CTestService cTestService;

    @RequestMapping(value = "")
    public String CTest() {
        return prefix + "/cTest";
    }
    @RequestMapping(value = "cTestCheck")
    public String CTestCheck() {
        return prefix + "/cTestCheck";
    }

    /**
     * 列表
     * sxd
     */
    @ResponseBody
    @GetMapping("/list")
    public RJQ selCTestPage(Page page) throws Exception {
        PageData pageData = this.getPageData();
        page.setPd(pageData);
        List<PageData> list = cTestService.selectCTestList(page);
        List<PageData> newdata = new ArrayList<>();
        for (PageData data : list) {
            Object mname = data.get("mname");
            if (mname != null){
                newdata.add(data);
            }
        }
        return RJQ.ok().put("page", page).put("data", newdata);
    }

    /**
     * 根据id获取数据${tableComment}
     * sxd
     */
    @ResponseBody
    @GetMapping("/findByid")
    public RJQ getCTestInfo() throws Exception {
        PageData pageData = this.getPageData();
        PageData data = cTestService.selectCTestById(pageData);
        return RJQ.ok().put("data", data);
    }

    /**
     * 新增${tableComment}
     * sxd
     */
    @ResponseBody
    @PostMapping("/adddata")
    public RJQ saveCTestData(@RequestBody PageData pageData) throws Exception {
            cTestService.insertCTest(pageData);
        return RJQ.ok();
    }

    /**
     * 修改${tableComment}
     * sxd
     */
    @ResponseBody
    @PostMapping("/updatedata")
    public RJQ editCTestData(@RequestBody PageData pageData) throws Exception {
            cTestService.updateCTest(pageData);
        return RJQ.ok();
    }

    /**
     * 批量删除${tableComment}
     * sxd
     */
    @ResponseBody
    @PostMapping("/del")
    public RJQ delCTestDataList(@RequestBody Integer[] ids) throws Exception {
        PageData pageData = this.getPageData();
        for (Integer id : ids) {
            pageData.put("id", id);
                cTestService.deleteCTestByIds(pageData);
        }
        return RJQ.ok();
    }


}
