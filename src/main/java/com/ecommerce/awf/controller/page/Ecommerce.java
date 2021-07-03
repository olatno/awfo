package com.ecommerce.awf.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Ecommerce {

    @GetMapping("/*")
    public String index() {
        return "index";
    }

    @GetMapping("/home")
    public String home() {
        return "testing";
    }
}
