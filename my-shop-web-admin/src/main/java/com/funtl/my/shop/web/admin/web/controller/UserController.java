package com.funtl.my.shop.web.admin.web.controller;

import com.funtl.my.shop.domain.TbUser;
import com.funtl.my.shop.web.admin.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 用户管理
 *
 * @author: TOLUNGKIT
 * @version: 1.0.0
 * @date: 2020-08-14 20:52
 **/

@Controller
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private TbUserService tbUserService;

    /**
     * 跳转到用户列表页
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model){
        List<TbUser> tbUsers = tbUserService.selectAll();
        model.addAttribute("tbUser",tbUsers);
        return "user_list";
    }
}
