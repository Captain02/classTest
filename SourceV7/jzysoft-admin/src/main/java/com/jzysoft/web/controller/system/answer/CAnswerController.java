package com.jzysoft.web.controller.system.answer;

import com.jzysoft.commonmoudle.lib.config.page.PageData;
import com.jzysoft.commonmoudle.lib.util.BaseJQController;
import com.jzysoft.commonmoudle.lib.util.RJQ;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/canswer/canswer")
@Api(value = "/canswer/canswer", description = "在线测评中心")
public class CAnswerController extends BaseJQController{

    private String prefix = "moudles/canswer";
    @Autowired
    CAnswerService cAnswerService;

    @RequestMapping(value = "")
    public String CClass() {
        return prefix + "/canswer";
    }

    @ResponseBody
    @ApiOperation( value = "查询题目", httpMethod = "POST" )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageData", value = "mclassid"),
    })
    @PostMapping("/canswer")
    public RJQ selCClassPage() throws Exception {
        PageData pageData = this.getPageData();
        List<PageData> list = cAnswerService.selectTest(pageData);
        return RJQ.ok().put("data", list);
    }
}
