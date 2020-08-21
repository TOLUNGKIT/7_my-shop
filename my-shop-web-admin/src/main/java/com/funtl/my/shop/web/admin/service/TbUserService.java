package com.funtl.my.shop.web.admin.service;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.dto.PageInfo;
import com.funtl.my.shop.domain.TbUser;

import java.util.List;

/**
 * @author: TOLUNGKIT
 * @version: 1.0.0
 * @date: 2020-08-13 20:51
 **/
public interface TbUserService {
    /**
     * 查询全部
     * @return
     */
    public List<TbUser> selectAll();

    /**
     * 保存用户信息
     * @param tbUser
     * @return
     */
    BaseResult save(TbUser tbUser);

    /**
     * 删除用户信息
     * @param id
     */
    void delete(Long id);

    /**
     * 根据 ID 获取用户信息
     * @param id
     * @return
     */
    TbUser getById(Long id);

    /**
     * 更新用户信息
     * @param tbUser
     */
    void update(TbUser tbUser);

    /**
     * 用户登录
     * @param email
     * @param password
     * @return
     */
    TbUser login(String email, String password);

    /**
     * 批量删除
     * @param ids
     */
    void deleteMulti(String[] ids);

    /**
     * 分页查询
     * @param start
     * @param length
     * @return
     */
    PageInfo<TbUser> page(int start, int length, int draw, TbUser tbUser);

    /**
     * 查询总笔数
     * @return
     */
    int count(TbUser tbUser);
}
