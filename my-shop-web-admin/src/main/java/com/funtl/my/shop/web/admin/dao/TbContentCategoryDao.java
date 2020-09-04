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

    /**
     * 根据父级节点 ID 查询所有子节点
     * @param pid
     * @return
     */
    List<TbContentCategory> selectByPid(Long pid);

    /**
     * 根据id查询商品信息
     * @param id
     * @return
     */
    TbContentCategory getById(Long id);

    /**
     * 新增
     * @param tbContentCategory
     */
    void insert(TbContentCategory tbContentCategory);


    /**
     * 更新
     * @param tbContentCategory
     */
    void updated(TbContentCategory tbContentCategory);

}
