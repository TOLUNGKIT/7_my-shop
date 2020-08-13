package com.funtl.my.shop.web.admin.web.controller;

import com.funtl.my.shop.commons.constant.ConstantUtils;
import com.funtl.my.shop.commons.utils.CookieUtils;
import com.funtl.my.shop.domain.User;
import com.funtl.my.shop.web.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    private UserService userService;

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
    public String Login(@RequestParam(required = true) String email, @RequestParam(required = true) String password, HttpServletRequest req, HttpServletResponse resp) {

        User user = userService.login(email, password);

        boolean isRemember = req.getParameter("isRemember") == null ? false : true;

        //登录失败
        if (user == null) {
            req.setAttribute("message","用户名或密码错误，请重新登录");
            return login();
        }

        //登录成功
        else {
            if(isRemember==true){
                //用户信息存一周
                CookieUtils.setCookie(req,resp,COOKIE_NAME_USER_INFO,String.format("%s:%s",email,password),7*24*60*60);
            }

            //将登陆信息放入会话
            req.getSession().setAttribute(ConstantUtils.SESSION_USER, user);

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
