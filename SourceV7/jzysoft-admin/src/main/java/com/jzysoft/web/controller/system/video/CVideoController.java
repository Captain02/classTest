package com.jzysoft.web.controller.system.video;

import com.jzysoft.common.config.Global;
import com.jzysoft.common.config.ServerConfig;
import com.jzysoft.common.core.domain.AjaxResult;
import com.jzysoft.common.utils.file.FileUploadUtils;
import com.jzysoft.commonmoudle.lib.config.page.Page;
import com.jzysoft.commonmoudle.lib.config.page.PageData;
import com.jzysoft.commonmoudle.lib.util.BaseJQController;
import com.jzysoft.commonmoudle.lib.util.RJQ;
import com.jzysoft.web.controller.system.cCurr.CCurrService;
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
        return RJQ.ok().put("page", page).put("data", list);
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

}
