package com.funtl.my.shop.web.admin.dao;

import com.funtl.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

    /**
     * 更新
     * @param tbUser
     */
    void updated(TbUser tbUser);

    /**
     * 根据邮箱查询用户信息
     * @param Email
     * @return
     */
    TbUser getByEmail(String Email);

    /**
     * 批量删除
     * @param ids
     */
    void deleteMulti(String[] ids);

    /**
     * 分页查询
     * @param params,需要两个参数，start/记录开始的位置 length/每页记录数
     * @return
     */
    List<TbUser> page(Map<String, Object> params);

    /**
     * 查询总笔数
     * @return
     */
    int count(TbUser tbUser);
}
