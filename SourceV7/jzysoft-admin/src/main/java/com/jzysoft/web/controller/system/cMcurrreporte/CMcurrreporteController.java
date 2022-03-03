package com.jzysoft.web.controller.system.cMcurrreporte;

import com.jzysoft.common.core.domain.AjaxResult;
import com.jzysoft.common.utils.poi.ExcelUtil;
import com.jzysoft.commonmoudle.lib.config.page.Page;
import com.jzysoft.commonmoudle.lib.config.page.PageData;
import com.jzysoft.commonmoudle.lib.util.BaseJQController;
import com.jzysoft.commonmoudle.lib.util.RJQ;
import com.jzysoft.web.controller.system.cVideoreporte.VideoEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 【请填写功能名称】Controller
 *
 * @author jzysoft
 * @date 2022-02-15
 */
@Controller
@RequestMapping("/cMcurrreporte/cMcurrreporte")
@Api(value = "/cMcurrreporte/cMcurrreporte", description = "微课堂报表")
public class CMcurrreporteController extends BaseJQController {
    private String prefix = "moudles/cMcurrreporte";
    @Autowired
        CMcurrreporteService cMcurrreporteService;

    @RequestMapping(value = "")
    public String CMcurrreporte() {
        return prefix + "/cMcurrreporte";
    }

    /**
     * 列表
     * sxd
     */
    @ResponseBody
    @GetMapping("/list")
    public RJQ selCMcurrreportePage(Page page) throws Exception {

        List<PageData> list = cMcurrreporteService.selectCMcurrreporteList(page);
        List<PageData> pageData1 = new ArrayList<>();
        for (PageData data : list) {
            Object mname = data.get("mname");
            if (mname != null){
                pageData1.add(data);
            }
        }


        return RJQ.ok().put("page", page).put("data", pageData1);
    }

    /**
     * 根据id获取数据${tableComment}
     * sxd
     */
    @ResponseBody
    @GetMapping("/findByid")
    public RJQ getCMcurrreporteInfo() throws Exception {
        PageData pageData = this.getPageData();
        PageData data = cMcurrreporteService.selectCMcurrreporteById(pageData);
        return RJQ.ok().put("data", data);
    }

    /**
     * 新增${tableComment}
     * sxd
     */
    @ResponseBody
    @PostMapping("/adddata")
    public RJQ saveCMcurrreporteData(@RequestBody PageData pageData) throws Exception {
            cMcurrreporteService.insertCMcurrreporte(pageData);
        return RJQ.ok();
    }

    /**
     * 修改${tableComment}
     * sxd
     */
    @ResponseBody
    @PostMapping("/updatedata")
    public RJQ editCMcurrreporteData(@RequestBody PageData pageData) throws Exception {
            cMcurrreporteService.updateCMcurrreporte(pageData);
        return RJQ.ok();
    }

    /**
     * 批量删除${tableComment}
     * sxd
     */
    @ResponseBody
    @PostMapping("/del")
    public RJQ delCMcurrreporteDataList(@RequestBody Integer[] ids) throws Exception {
        PageData pageData = this.getPageData();
        for (Integer id : ids) {
            pageData.put("id", id);
                cMcurrreporteService.deleteCMcurrreporteByIds(pageData);
        }
        return RJQ.ok();
    }

    @ApiOperation( value = "微课堂报表", httpMethod = "POST" )
    @PostMapping("/exportReport")
    @ResponseBody
    public AjaxResult exportReport() throws Exception {
        List<MCurrEntity> list = cMcurrreporteService.exportReport();
        ExcelUtil<MCurrEntity> util = new ExcelUtil<MCurrEntity>(MCurrEntity.class);
        List<MCurrEntity> newdata = new ArrayList<>();
        for (MCurrEntity data : list) {
            Object mname = data.getMname();
            if (mname != null){
                newdata.add(data);
            }
        }
        return util.exportExcel(newdata, "微课堂报表");
    }
}
