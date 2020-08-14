package com.funtl.my.shop.web.admin.web.controller;

import com.funtl.my.shop.commons.constant.ConstantUtils;
import com.funtl.my.shop.domain.TbUser;
import com.funtl.my.shop.web.admin.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: TOLUNGKIT
 * @version: 1.0.0
 * @date: 2020-08-12 19:19
 **/
@Controller
public class LoginController {

    @Autowired
    private TbUserService tbUserService;

    private static final String COOKIE_NAME_USER_INFO = "userInfo";



    /**
     *  跳转登录页面
     * @return
     */
    @RequestMapping(value = {"" , "login"}, method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    /**
     * 登录逻辑
     * @param email
     * @param password
     * @return
     */
    @RequestMapping(value = "login" , method = RequestMethod.POST)
    public String Login(@RequestParam(required = true) String email, @RequestParam(required = true) String password, HttpServletRequest req, HttpServletResponse resp, Model model) {

        TbUser tbUser = tbUserService.login(email, password);

        //登陆失败
        if(tbUser == null){

            model.addAttribute("message","用户名或密码错误，请重新输入");
            return login();
        }

        //登陆成功
        else {
            //将登陆信息放入会话
            req.getSession().setAttribute(ConstantUtils.SESSION_USER, tbUser);
            //重定向到首页
            return "redirect:/main";
        }

    }

    /**
     * 注销
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest httpServletRequest){

        httpServletRequest.getSession().invalidate();
        return login();
    }
}
