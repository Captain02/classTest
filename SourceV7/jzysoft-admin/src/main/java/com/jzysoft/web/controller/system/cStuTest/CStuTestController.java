package com.jzysoft.web.controller.system.cStuTest;

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
@RequestMapping("/cStuTest/cStuTest")
public class CStuTestController extends BaseJQController {
    private String prefix = "moudles/cStuTest";
    @Autowired
        CStuTestService cStuTestService;

    @RequestMapping(value = "")
    public String CStuTest() {
        return prefix + "/cStuTest";
    }

    /**
     * 列表
     * sxd
     */
    @ResponseBody
    @GetMapping("/list")
    public RJQ selCStuTestPage(Page page) throws Exception {
        PageData pageData = this.getPageData();
        page.setPd(pageData);
        List<PageData> list = cStuTestService.selectCStuTestList(page);
        return RJQ.ok().put("page", page).put("data", list);
    }

    /**
     * 根据id获取数据${tableComment}
     * sxd
     */
    @ResponseBody
    @GetMapping("/findByid")
    public RJQ getCStuTestInfo() throws Exception {
        PageData pageData = this.getPageData();
        PageData data = cStuTestService.selectCStuTestById(pageData);
        return RJQ.ok().put("data", data);
    }

    /**
     * 新增${tableComment}
     * sxd
     */
    @ResponseBody
    @PostMapping("/adddata")
    public RJQ saveCStuTestData(@RequestBody PageData pageData) throws Exception {
            cStuTestService.insertCStuTest(pageData);
        return RJQ.ok();
    }

    /**
     * 修改${tableComment}
     * sxd
     */
    @ResponseBody
    @PostMapping("/updatedata")
    public RJQ editCStuTestData(@RequestBody PageData pageData) throws Exception {
            cStuTestService.updateCStuTest(pageData);
        return RJQ.ok();
    }

    /**
     * 批量删除${tableComment}
     * sxd
     */
    @ResponseBody
    @PostMapping("/del")
    public RJQ delCStuTestDataList(@RequestBody Integer[] ids) throws Exception {
        PageData pageData = this.getPageData();
        for (Integer id : ids) {
            pageData.put("id", id);
                cStuTestService.deleteCStuTestByIds(pageData);
        }
        return RJQ.ok();
    }


}
