package com.jzysoft.commonmoudle.moudles.car;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/common/test")
public class TestConntroller {
    private String prefix = "moudles/test";

    @GetMapping("/test")
    public String test() {
        return prefix + "/test";
    }
}
