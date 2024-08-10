package com.jalynn.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
//@RequestMapping("/diagnosis")
public class PageController {

    @RequestMapping("/index")
    public String myIndex() {
        return "index";
    }

}
