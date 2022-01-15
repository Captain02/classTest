package com.jzysoft.module.testdemo;

import com.jzysoft.commonmoudle.lib.config.page.Page;
import com.jzysoft.commonmoudle.lib.config.page.PageData;
import com.jzysoft.commonmoudle.lib.util.BaseJQController;
import com.jzysoft.commonmoudle.lib.util.RJQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 测试Controller
 *
 * @author jzysoft
 * @date 2020-07-10
 */
@Controller
@RequestMapping("/testdemo/testdemo")
public class TestdemoController extends BaseJQController {
    private String prefix = "moudles/testdemo";
    @Autowired
        TestdemoService testdemoService;

    @RequestMapping(value = "")
    public String Testdemo() {
        return prefix + "/testdemo";
    }

    /**
     * 列表
     * sxd
     */
    @ResponseBody
    @GetMapping("/list")
    public RJQ selTestdemoPage(Page page) throws Exception {
        PageData pageData = this.getPageData();
        page.setPd(pageData);
        List<PageData> list = testdemoService.selectTestdemoList(page);
        return RJQ.ok().put("page", page).put("data", list);
    }

    /**
     * 根据id获取数据${tableComment}
     * sxd
     */
    @ResponseBody
    @GetMapping("/findByid")
    public RJQ getTestdemoInfo() throws Exception {
        PageData pageData = this.getPageData();
        PageData data = testdemoService.selectTestdemoById(pageData);
        return RJQ.ok().put("data", data);
    }

    /**
     * 新增${tableComment}
     * sxd
     */
    @ResponseBody
    @PostMapping("/adddata")
    public RJQ saveTestdemoData(@RequestBody PageData pageData) throws Exception {
            testdemoService.insertTestdemo(pageData);
        return RJQ.ok();
    }

    /**
     * 修改${tableComment}
     * sxd
     */
    @ResponseBody
    @PostMapping("/updatedata")
    public RJQ editTestdemoData(@RequestBody PageData pageData) throws Exception {
            testdemoService.updateTestdemo(pageData);
        return RJQ.ok();
    }

    /**
     * 批量删除${tableComment}
     * sxd
     */
    @ResponseBody
    @PostMapping("/del")
    public RJQ delTestdemoDataList(@RequestBody Integer[] ids) throws Exception {
        PageData pageData = this.getPageData();
        for (Integer id : ids) {
            pageData.put("id", id);
                testdemoService.deleteTestdemoByIds(pageData);
        }
        return RJQ.ok();
    }


}
