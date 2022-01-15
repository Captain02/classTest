package com.jzysoft.commonmoudle.moudles.car;


import com.jzysoft.commonmoudle.lib.config.page.PageData;
import com.jzysoft.commonmoudle.lib.util.RJQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/car")
public class CarConntroller {
    @Autowired
    CarService carService;
    @ResponseBody
    @GetMapping("/car")
    public String test1() throws Exception {
        System.out.println(1);
        return "123";
    }

    @ResponseBody
    @GetMapping("/cars")
    public RJQ test() throws Exception {
        PageData pageData2 = new PageData();
        List<PageData> pageData1 = carService.selectHistory(pageData2);
        return RJQ.ok().put("data", pageData1);
    }
}
