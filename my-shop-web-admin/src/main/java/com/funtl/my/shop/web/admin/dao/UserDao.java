package com.funtl.my.shop.web.admin.dao;

import com.funtl.my.shop.domain.User;

/**
 * @author: TOLUNGKIT
 * @version: 1.0.0
 * @date: 2020-08-12 19:07
 **/
public interface UserDao {
    /**
     * 根据邮箱和密码获取用户信息
     * @param email 邮箱
     * @param passward 密码
     * @return 用户
     */
    public User getUser(String email, String password);
}
