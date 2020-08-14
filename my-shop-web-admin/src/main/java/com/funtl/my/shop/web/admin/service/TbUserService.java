package com.funtl.my.shop.web.admin.service;

import com.funtl.my.shop.domain.TbUser;

import java.util.List;

/**
 * @author: TOLUNGKIT
 * @version: 1.0.0
 * @date: 2020-08-13 20:51
 **/
public interface TbUserService {
    public List<TbUser> selectAll();

    void insert(TbUser tbUser);

    void delete(Long id);

    TbUser getById(Long id);

    void update(TbUser tbUser);

    List<TbUser> selectByUsername(String name);
}
