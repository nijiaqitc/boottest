package com.njq.boottest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: nijiaqi
 * @date: 2019/6/26
 */
@Controller
public class TtController {

    @RequestMapping("index")
    public String index() {
        System.out.println("1a11a1");
        return "index";

    }
}
