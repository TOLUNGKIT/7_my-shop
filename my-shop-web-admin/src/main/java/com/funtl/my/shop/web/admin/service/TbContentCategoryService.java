package com.funtl.my.shop.web.admin.service;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.domain.TbContentCategory;

import java.util.List;

/**
 * @author: TOLUNGKIT
 * @version: 1.0.0
 * @date: 2020-08-22 12:19
 **/
public interface TbContentCategoryService {

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
    BaseResult save(TbContentCategory tbContentCategory);

}
