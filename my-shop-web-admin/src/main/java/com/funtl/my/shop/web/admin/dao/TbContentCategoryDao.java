package com.funtl.my.shop.web.admin.dao;

import com.funtl.my.shop.domain.TbContentCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: TOLUNGKIT
 * @version: 1.0.0
 * @date: 2020-08-22 12:18
 **/

@Repository
public interface TbContentCategoryDao {
    List<TbContentCategory> selectAll();
}
