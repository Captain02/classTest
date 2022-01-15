package com.jzysoft.commonmoudle.moudles.test;

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
 * @date 2020-04-07
 */
@Controller
@RequestMapping("/test/test")
public class TestsController extends BaseJQController {
    private String prefix = "moudles/test";
    @Autowired
        TestService testService;

    @RequestMapping(value = "")
    public String Test() {
        return prefix + "/test";
    }

    /**
     * 列表
     * sxd
     */
    @ResponseBody
    @GetMapping("/list")
    public RJQ selTestPage(Page page) throws Exception {
        PageData pageData = this.getPageData();
        page.setPd(pageData);
        List<PageData> list = testService.selectTestList(page);
        return RJQ.ok().put("page", page).put("data", list);
    }

    /**
     * 根据id获取数据${tableComment}
     * sxd
     */
    @ResponseBody
    @GetMapping("/findByid")
    public RJQ getTestInfo() throws Exception {
        PageData pageData = this.getPageData();
        PageData data = testService.selectTestById(pageData);
        return RJQ.ok().put("data", data);
    }

    /**
     * 新增${tableComment}
     * sxd
     */
    @ResponseBody
    @PostMapping("/adddata")
    public RJQ saveTestData(@RequestBody PageData pageData) throws Exception {
            testService.insertTest(pageData);
        return RJQ.ok();
    }

    /**
     * 修改${tableComment}
     * sxd
     */
    @ResponseBody
    @PostMapping("/updatedata")
    public RJQ editTestData(@RequestBody PageData pageData) throws Exception {
            testService.updateTest(pageData);
        return RJQ.ok();
    }

    /**
     * 批量删除${tableComment}
     * sxd
     */
    @ResponseBody
    @PostMapping("/del")
    public RJQ delTestDataList(@RequestBody Integer[] ids) throws Exception {
        PageData pageData = this.getPageData();
        for (Integer id : ids) {
            pageData.put("id", id);
                testService.deleteTestByIds(pageData);
        }
        return RJQ.ok();
    }


}
