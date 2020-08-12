package com.funtl.my.shop.web.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: TOLUNGKIT
 * @version: 1.0.0
 * @date: 2020-08-12 19:20
 **/
@Controller
public class MainController {

    @RequestMapping(value = "main", method = RequestMethod.GET)
    public String main() {
        return "main";
    }
}
