package com.jzysoft.web.controller.system.cTestItem;

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
@RequestMapping("/cTestItem/cTestItem")
public class CTestItemController extends BaseJQController {
    private String prefix = "moudles/cTestItem";
    @Autowired
        CTestItemService cTestItemService;

    @RequestMapping(value = "")
    public String CTestItem() {
        return prefix + "/cTestItem";
    }

    /**
     * 列表
     * sxd
     */
    @ResponseBody
    @GetMapping("/list")
    public RJQ selCTestItemPage(Page page) throws Exception {
        PageData pageData = this.getPageData();
        page.setPd(pageData);
        List<PageData> list = cTestItemService.selectCTestItemList(page);
        return RJQ.ok().put("page", page).put("data", list);
    }

    /**
     * 根据id获取数据${tableComment}
     * sxd
     */
    @ResponseBody
    @GetMapping("/findByid")
    public RJQ getCTestItemInfo() throws Exception {
        PageData pageData = this.getPageData();
        PageData data = cTestItemService.selectCTestItemById(pageData);
        return RJQ.ok().put("data", data);
    }

    /**
     * 新增${tableComment}
     * sxd
     */
    @ResponseBody
    @PostMapping("/adddata")
    public RJQ saveCTestItemData(@RequestBody PageData pageData) throws Exception {
            cTestItemService.insertCTestItem(pageData);
        return RJQ.ok();
    }

    /**
     * 修改${tableComment}
     * sxd
     */
    @ResponseBody
    @PostMapping("/updatedata")
    public RJQ editCTestItemData(@RequestBody PageData pageData) throws Exception {
            cTestItemService.updateCTestItem(pageData);
        return RJQ.ok();
    }

    /**
     * 批量删除${tableComment}
     * sxd
     */
    @ResponseBody
    @PostMapping("/del")
    public RJQ delCTestItemDataList(@RequestBody Integer[] ids) throws Exception {
        PageData pageData = this.getPageData();
        for (Integer id : ids) {
            pageData.put("id", id);
                cTestItemService.deleteCTestItemByIds(pageData);
        }
        return RJQ.ok();
    }


}
