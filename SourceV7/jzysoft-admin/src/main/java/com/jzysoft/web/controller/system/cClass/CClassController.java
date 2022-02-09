package com.jzysoft.web.controller.system.cClass;

import com.jzysoft.commonmoudle.lib.config.page.Page;
import com.jzysoft.commonmoudle.lib.config.page.PageData;
import com.jzysoft.commonmoudle.lib.util.BaseJQController;
import com.jzysoft.commonmoudle.lib.util.RJQ;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 【请填写功能名称】Controller
 *
 * @author jzysoft
 * @date 2022-02-08
 */
@Controller
@RequestMapping("/cClass/cClass")
@Api(value = "/cClass/cClass", description = "班级中心")
public class CClassController extends BaseJQController {
    private String prefix = "moudles/cClass";
    @Autowired
        CClassService cClassService;

    @RequestMapping(value = "")
    public String CClass() {
        return prefix + "/cClass";
    }

    /**
     * 列表
     * sxd
     */
    @ResponseBody
    @GetMapping("/list")
    public RJQ selCClassPage(Page page) throws Exception {
        PageData pageData = this.getPageData();
        page.setPd(pageData);
        List<PageData> list = cClassService.selectCClassList(page);
        return RJQ.ok().put("page", page).put("data", list);
    }

    /**
     * 根据id获取数据${tableComment}
     * sxd
     */
    @ResponseBody
    @GetMapping("/findByid")
    public RJQ getCClassInfo() throws Exception {
        PageData pageData = this.getPageData();
        PageData data = cClassService.selectCClassById(pageData);
        return RJQ.ok().put("data", data);
    }

    /**
     * 新增${tableComment}
     * sxd
     */
    @ResponseBody
    @PostMapping("/adddata")
    public RJQ saveCClassData(@RequestBody PageData pageData) throws Exception {
            cClassService.insertCClass(pageData);
        return RJQ.ok();
    }

    /**
     * 修改${tableComment}
     * sxd
     */
    @ResponseBody
    @PostMapping("/updatedata")
    public RJQ editCClassData(@RequestBody PageData pageData) throws Exception {
            cClassService.updateCClass(pageData);
        return RJQ.ok();
    }

    /**
     * 批量删除${tableComment}
     * sxd
     */
    @ResponseBody
    @PostMapping("/del")
    public RJQ delCClassDataList(@RequestBody Integer[] ids) throws Exception {
        PageData pageData = this.getPageData();
        for (Integer id : ids) {
            pageData.put("id", id);
                cClassService.deleteCClassByIds(pageData);
        }
        return RJQ.ok();
    }

    @ResponseBody
    @ApiOperation( value = "给班级加入学生", httpMethod = "POST" )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageData", value = "id,班级ID。ids,学生id,如[1,2,3]"),
    })
    @PostMapping("/joiSntu")
    public RJQ joiSntu(@RequestBody PageData pageData) throws Exception {
        cClassService.joinStu(pageData);
        return RJQ.ok();
    }
}
