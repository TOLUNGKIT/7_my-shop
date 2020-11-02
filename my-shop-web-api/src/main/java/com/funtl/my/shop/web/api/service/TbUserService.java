package com.funtl.my.shop.web.api.service;

import com.funtl.my.shop.domain.TbUser;

/**
 * 会员管理
 * @author: TOLUNGKIT
 * @version: 1.0.0
 * @date: 2020-11-02 13:09
 **/
public interface TbUserService {

    /**
     * 登录
     * @param tbUser
     * @return
     */
    TbUser login(TbUser tbUser);
}
