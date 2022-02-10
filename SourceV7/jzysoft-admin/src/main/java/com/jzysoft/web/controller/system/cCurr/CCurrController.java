package com.jzysoft.web.controller.system.cCurr;

import com.jzysoft.common.config.Global;
import com.jzysoft.common.config.ServerConfig;
import com.jzysoft.common.core.domain.AjaxResult;
import com.jzysoft.common.utils.file.FileUploadUtils;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 【请填写功能名称】Controller
 *
 * @author jzysoft
 * @date 2022-02-08
 */
@Controller
@RequestMapping("/cCurr/cCurr")
@Api(value = "/cCurr/cCurr", description = "课程管理")
public class CCurrController extends BaseJQController {
    private String prefix = "moudles/cCurr";
    @Autowired
        CCurrService cCurrService;

    @RequestMapping(value = "")
    public String CCurr() {
        return prefix + "/cCurr";
    }

    /**
     * 列表
     * sxd
     */
    @ResponseBody
    @GetMapping("/list")
    public RJQ selCCurrPage(Page page) throws Exception {
        PageData pageData = this.getPageData();
        page.setPd(pageData);
        List<PageData> list = cCurrService.selectCCurrList(page);
        return RJQ.ok().put("page", page).put("data", list);
    }

    /**
     * 根据id获取数据${tableComment}
     * sxd
     */
    @ResponseBody
    @GetMapping("/findByid")
    public RJQ getCCurrInfo() throws Exception {
        PageData pageData = this.getPageData();
        PageData data = cCurrService.selectCCurrById(pageData);
        return RJQ.ok().put("data", data);
    }

    @Autowired
    private ServerConfig serverConfig;
    /**
     * 新增${tableComment}
     * sxd
     */

    @ApiOperation(
            value = "添加课程",
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageData", value = "currname,teacherid,ordernum,mcurr,currtime"),
            @ApiImplicitParam(name = "file", value = "上传文件",type = "file")
    })
    @ResponseBody
    @PostMapping("/adddata")
    public RJQ saveCCurrData(MultipartFile file) throws Exception {
        PageData pageData = this.getPageData();
        try
        {
            // 上传文件路径
            String filePath = Global.getDownloadPath();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            String url = serverConfig.getUrl() + fileName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("fileName", fileName);
            ajax.put("url", url);
            pageData.put("videopath",url);
            cCurrService.insertCCurr(pageData);
            return RJQ.ok();
        }
        catch (Exception e)
        {
            return RJQ.error();
        }
    }

    /**
     * 修改${tableComment}
     * sxd
     */
    @ResponseBody
    @PostMapping("/updatedata")
    public RJQ editCCurrData(@RequestBody PageData pageData) throws Exception {
            cCurrService.updateCCurr(pageData);
        return RJQ.ok();
    }

    /**
     * 批量删除${tableComment}
     * sxd
     */
    @ResponseBody
    @PostMapping("/del")
    public RJQ delCCurrDataList(@RequestBody Integer[] ids) throws Exception {
        PageData pageData = this.getPageData();
        for (Integer id : ids) {
            pageData.put("id", id);
                cCurrService.deleteCCurrByIds(pageData);
        }
        return RJQ.ok();
    }


}
