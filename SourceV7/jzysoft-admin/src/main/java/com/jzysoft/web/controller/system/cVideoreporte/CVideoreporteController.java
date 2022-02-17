package com.jzysoft.web.controller.system.cVideoreporte;

import com.jzysoft.common.annotation.Log;
import com.jzysoft.common.core.domain.AjaxResult;
import com.jzysoft.common.enums.BusinessType;
import com.jzysoft.common.utils.poi.ExcelUtil;
import com.jzysoft.commonmoudle.lib.config.page.Page;
import com.jzysoft.commonmoudle.lib.config.page.PageData;
import com.jzysoft.commonmoudle.lib.util.BaseJQController;
import com.jzysoft.commonmoudle.lib.util.RJQ;
import com.jzysoft.system.domain.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 【请填写功能名称】Controller
 *
 * @author jzysoft
 * @date 2022-02-15
 */
@Controller
@RequestMapping("/cVideoreporte/cVideoreporte")
@Api(value = "/cVideoreporte/cVideoreporte", description = "视频报表")
public class CVideoreporteController extends BaseJQController {
    private String prefix = "moudles/cVideoreporte";
    @Autowired
        CVideoreporteService cVideoreporteService;

    @RequestMapping(value = "")
    public String CVideoreporte() {
        return prefix + "/cVideoreporte";
    }

    /**
     * 列表
     * sxd
     */
    @ResponseBody
    @GetMapping("/list")
    public RJQ selCVideoreportePage(Page page) throws Exception {
        PageData pageData = this.getPageData();
        page.setPd(pageData);
        List<PageData> list = cVideoreporteService.selectCVideoreporteList(page);
        return RJQ.ok().put("page", page).put("data", list);
    }

    /**
     * 根据id获取数据${tableComment}
     * sxd
     */
    @ResponseBody
    @GetMapping("/findByid")
    public RJQ getCVideoreporteInfo() throws Exception {
        PageData pageData = this.getPageData();
        PageData data = cVideoreporteService.selectCVideoreporteById(pageData);
        return RJQ.ok().put("data", data);
    }

    /**
     * 新增${tableComment}
     * sxd
     */
    @ResponseBody
    @PostMapping("/adddata")
    public RJQ saveCVideoreporteData(@RequestBody PageData pageData) throws Exception {
            cVideoreporteService.insertCVideoreporte(pageData);
        return RJQ.ok();
    }

    /**
     * 修改${tableComment}
     * sxd
     */
    @ResponseBody
    @PostMapping("/updatedata")
    public RJQ editCVideoreporteData(@RequestBody PageData pageData) throws Exception {
            cVideoreporteService.updateCVideoreporte(pageData);
        return RJQ.ok();
    }

    /**
     * 批量删除${tableComment}
     * sxd
     */
    @ResponseBody
    @PostMapping("/del")
    public RJQ delCVideoreporteDataList(@RequestBody Integer[] ids) throws Exception {
        PageData pageData = this.getPageData();
        for (Integer id : ids) {
            pageData.put("id", id);
                cVideoreporteService.deleteCVideoreporteByIds(pageData);
        }
        return RJQ.ok();
    }

    @ApiOperation( value = "视频报表", httpMethod = "POST" )
    @PostMapping("/exportReport")
    @ResponseBody
    public AjaxResult exportReport() throws Exception {
        List<VideoEntity> list = cVideoreporteService.exportReport();
        ExcelUtil<VideoEntity> util = new ExcelUtil<VideoEntity>(VideoEntity.class);
        return util.exportExcel(list, "视频报表");
    }


}
