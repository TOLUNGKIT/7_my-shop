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
}
