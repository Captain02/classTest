package com.jzysoft.web.controller.system.video;

import com.jzysoft.common.config.Global;
import com.jzysoft.common.config.ServerConfig;
import com.jzysoft.common.core.domain.AjaxResult;
import com.jzysoft.common.utils.file.FileUploadUtils;
import com.jzysoft.commonmoudle.lib.config.page.Page;
import com.jzysoft.commonmoudle.lib.config.page.PageData;
import com.jzysoft.commonmoudle.lib.util.BaseJQController;
import com.jzysoft.commonmoudle.lib.util.RJQ;
import com.jzysoft.framework.util.ShiroUtils;
import com.jzysoft.web.controller.system.cCurr.CCurrService;
import com.jzysoft.web.controller.system.cVideoreporte.VideoEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * 【请填写功能名称】Controller
 *
 * @author jzysoft
 * @date 2022-02-08
 */
@Controller
@RequestMapping("/video/video")
@Api(value = "/video/video", description = "视频")
public class CVideoController extends BaseJQController {
    private String prefix = "moudles/video";
    @Autowired
    CVideoService cVideoService;

    @RequestMapping(value = "")
    public String CCurr() {
        return prefix + "/video";
    }

    @RequestMapping(value = "/{id}")
    public String CTestItem(@PathVariable("id") Long id, ModelMap mmap) {
        mmap.put("mcurrId", id);
        return prefix + "/videoStudent";
    }

    /**
     * 列表
     * sxd
     */
    @ApiOperation(
            value = "修改列表",
            httpMethod = "GET"
    )
    @ResponseBody
    @GetMapping("/list")
    public RJQ selCCurrPage(Page page) throws Exception {
        PageData pageData = this.getPageData();
        page.setPd(pageData);
        List<PageData> list = cVideoService.selectCVideoListPage(page);
        List<PageData> newdata = new ArrayList<>();
        for (PageData data : list) {
            Object mname = data.get("mname");
            if (mname != null){
                newdata.add(data);
            }
        }
        return RJQ.ok().put("page", page).put("data", newdata);
    }

    @Autowired
    private ServerConfig serverConfig;
    @ApiOperation(
            value = "修改视频",
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageData", value = "id"),
            @ApiImplicitParam(name = "file", value = "上传文件",type = "file")
    })
    @ResponseBody
    @PostMapping("/updatedata")
    public RJQ editCCurrData(MultipartFile file) throws Exception {
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
            pageData.remove("createtime");
            cVideoService.updateCCurr(pageData);
            return RJQ.ok();
        }
        catch (Exception e)
        {
            System.out.println(e);
            return RJQ.error();
        }

    }

    @ApiOperation(
            value = "按照id查询视频",
            httpMethod = "GET"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageData", value = "id"),
    })
    @ResponseBody
    @GetMapping("/findByid")
    public RJQ getCCurrInfo() throws Exception {
        PageData pageData = this.getPageData();
        PageData data = cVideoService.selectCCurrById(pageData);
        return RJQ.ok().put("data", data);
    }


    @ApiOperation(
            value = "记录访问视频",
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageData", value = "currid"),
    })
    @ResponseBody
    @PostMapping("/visitVideo")
    public RJQ visitVideo() throws Exception {
        PageData pageData = this.getPageData();
        Long userId = ShiroUtils.getUserId();
        pageData.put("userid",userId);
        cVideoService.visitVideo(pageData);
        return RJQ.ok();
    }
}
