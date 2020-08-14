package com.funtl.my.shop.web.admin.dao;

import com.funtl.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: TOLUNGKIT
 * @version: 1.0.0
 * @date: 2020-08-13 20:47
 **/
@Repository
public interface TbUserDao {
    /**
     * 查询用户全部信息
     * @return
     */
    public List<TbUser> selectAll();

    /**
     * 新增
     * @param tbUser
     */
    void insert(TbUser tbUser);

    /**
     * 删除
     * @param id
     */
    void delete(Long id);


    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    TbUser getById(Long id);

    void updated(TbUser tbUser);

    /**
     * 根据用户名进行模糊匹配
     * @param username
     * @return
     */
    List<TbUser> selectByUsername(String username);

    /**
     * 根据邮箱查询用户信息
     * @param Email
     * @return
     */
    TbUser getByEmail(String Email);

}
