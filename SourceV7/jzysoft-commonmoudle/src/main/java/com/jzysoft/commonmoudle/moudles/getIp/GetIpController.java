package com.jzysoft.commonmoudle.moudles.getIp;

import com.jzysoft.commonmoudle.lib.config.page.PageData;
import com.jzysoft.commonmoudle.lib.util.BaseJQController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("getip")
@RestController
public class GetIpController extends BaseJQController {

    @Autowired
    GetIpService getIpService;

    @RequestMapping("/adddata")
    public void getIp() throws Exception {
        PageData pageData = this.getPageData();
        getIpService.insertVisitrecord(pageData);


    }
}
