package com.jzysoft.web.controller.system.cMcurr;

import com.jzysoft.commonmoudle.lib.config.page.Page;
import com.jzysoft.commonmoudle.lib.config.page.PageData;
import com.jzysoft.commonmoudle.lib.util.BaseJQController;
import com.jzysoft.commonmoudle.lib.util.RJQ;
import com.jzysoft.commonmoudle.moudles.test.TestService;
import com.jzysoft.web.controller.system.cCurr.CCurrService;
import com.jzysoft.web.controller.system.cTest.CTestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/cMcurr/cMcurr")
@Api(value = "/cMcurr/cMcurr", description = "视频")
public class CMcurrController extends BaseJQController {
    private String prefix = "moudles/cMcurr";
    @Autowired
        CMcurrService cMcurrService;

    @RequestMapping(value = "")
    public String CMcurr() {
        return prefix + "/cMcurr";
    }

    /**
     * 列表
     * sxd
     */
    @ResponseBody
    @GetMapping("/list")
    public RJQ selCMcurrPage(Page page) throws Exception {
        PageData pageData = this.getPageData();
        page.setPd(pageData);
        List<PageData> list = cMcurrService.selectCMcurrList(page);
        int num = 0;
        for (PageData data : list) {
            data.put("leaf_field",false);
            data.put("expanded",true);
            data.put("level_field",num);
            data.put("idstr",data.getString("id"));
            if (num != 1){
                num += 1;
            }
        }
        return RJQ.ok().put("page", page).put("data", list);
    }

    /**
     * 根据id获取数据${tableComment}
     * sxd
     */
    @ResponseBody
    @GetMapping("/findByid")
    public RJQ getCMcurrInfo() throws Exception {
        PageData pageData = this.getPageData();
        PageData data = cMcurrService.selectCMcurrById(pageData);
        return RJQ.ok().put("data", data);
    }

    /**
     * 新增${tableComment}
     * sxd
     */
    @ResponseBody
    @PostMapping("/adddata")
    public RJQ saveCMcurrData(@RequestBody PageData pageData) throws Exception {
            cMcurrService.insertCMcurr(pageData);
        return RJQ.ok();
    }

    /**
     * 修改${tableComment}
     * sxd
     */
    @ResponseBody
    @PostMapping("/updatedata")
    public RJQ editCMcurrData(@RequestBody PageData pageData) throws Exception {
            cMcurrService.updateCMcurr(pageData);
        return RJQ.ok();
    }

    /**
     * 批量删除${tableComment}
     * sxd
     */
    @ResponseBody
    @PostMapping("/del")
    public RJQ delCMcurrDataList(@RequestBody Integer[] ids) throws Exception {
        PageData pageData = this.getPageData();
        for (Integer id : ids) {
            pageData.put("id", id);
                cMcurrService.deleteCMcurrByIds(pageData);
        }
        return RJQ.ok();
    }

    @Autowired
    CTestService ctestService;
    @Autowired
    CCurrService cCurrService;
    @ApiOperation(
            value = "修改状态",
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageData", value = "id,type={微课堂,题目,课程},isexamine"),
    })
    @ResponseBody
    @PostMapping("/changestatus")
    public RJQ getCCurrInfo() throws Exception {
        PageData pageData = this.getPageData();
        Integer isexamine = pageData.getInteger("isexamine");
        if (isexamine == 1){
            pageData.put("isexamine",2);
        }else {
            pageData.put("isexamine",1);
        }
        String type = pageData.getString("type");
        if (type.equals("微课堂")){
            cMcurrService.updateCMcurr(pageData);
        }else if (type.equals("题目")){
            ctestService.updateCTest(pageData);
        }else if(type.equals("课程")){
            cCurrService.updateCCurr(pageData);
        }
        return RJQ.ok();
    }
}
