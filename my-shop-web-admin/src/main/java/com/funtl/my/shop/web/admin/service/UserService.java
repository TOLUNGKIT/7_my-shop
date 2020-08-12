package com.funtl.my.shop.web.admin.service;

import com.funtl.my.shop.domain.User;

/**
 * @author: TOLUNGKIT
 * @version: 1.0.0
 * @date: 2020-08-12 19:14
 **/
public interface UserService {
    /**
     * 登录
     * @param email 邮箱
     * @param password 密码
     * @return
     */
    public User login(String email, String password);
}
