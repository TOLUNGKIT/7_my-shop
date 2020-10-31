package com.funtl.my.shop.web.api.service;

import com.funtl.my.shop.domain.TbContent;

import java.util.List;

/**
 * @author: TOLUNGKIT
 * @version: 1.0.0
 * @date: 2020-10-31 01:13
 **/
public interface TbContentService {
    /**
     *  根据类别 ID 查询内容列表
     * @param categoryId
     * @return
     */
    List<TbContent> selectByCategoryId(Long categoryId);
}
