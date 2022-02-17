package com.jzysoft.web.controller.system.answer;

import com.jzysoft.commonmoudle.lib.config.page.PageData;
import com.jzysoft.commonmoudle.lib.util.BaseJQController;
import com.jzysoft.commonmoudle.lib.util.RJQ;
import com.jzysoft.framework.util.ShiroUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/canswer/canswer")
@Api(value = "/canswer/canswer", description = "在线测评中心")
public class CAnswerController extends BaseJQController{

    private String prefix = "moudles/canswer";
    @Autowired
    CAnswerService cAnswerService;

    @ApiOperation( value = "跳转答题界面", httpMethod = "GET" )
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

    @ResponseBody
    @ApiOperation( value = "答题", httpMethod = "POST" )
    @PostMapping("/answer")
    public RJQ answer(@RequestBody List<Map> list) throws Exception {
        PageData pageData = this.getPageData();
        Integer score = 0;
        for (Map map : list) {
            String checkedval = map.get("checkedval").toString();
            List<Map> optionlist = (List<Map>) map.get("optionlist");
            for (Map map1 : optionlist) {
                if (Integer.parseInt(map1.get("isture").toString()) == 1){
                    int isture = Integer.parseInt(map1.get("id").toString());
                    if (isture == Integer.parseInt(checkedval)){
                        score += 1;
                    }
                }
            }
        }
        int size = list.size();
        double v = (size * 0.6) % 10;

        double v1 = v * 10;

        int i = score * 10;

        PageData pageData1 = pageData;
        pageData1.put("mcurrscore",i);
        pageData1.put("userid", ShiroUtils.getUserId());
        pageData1.put("TotalScore", size*10);
        if (v1 > i){
            pageData1.put("ispass","不及格");
        }else {
            pageData1.put("ispass","及格");
        }

        cAnswerService.insertscore(pageData1);
//        List<PageData> list = cAnswerService.selectTest(pageData);
        return RJQ.ok().put("data", pageData1);
    }
}
