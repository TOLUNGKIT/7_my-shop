package com.funtl.my.shop.web.admin.web.interceptor;

import com.funtl.my.shop.commons.constant.ConstantUtils;
import com.funtl.my.shop.domain.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 *
 * @author: TOLUNGKIT
 * @version: 1.0.0
 * @date: 2020-08-12 16:50
 **/
public class LoginInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        User user = (User) httpServletRequest.getSession().getAttribute(ConstantUtils.SESSION_USER);

        //未登录
        if (user == null) {
            httpServletResponse.sendRedirect("/login");
        }

        //放行
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


}
