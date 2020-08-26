package com.funtl.my.shop.web.admin.dao;

import com.funtl.my.shop.domain.TbContent;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author: TOLUNGKIT
 * @version: 1.0.0
 * @date: 2020-08-25 15:19
 **/
@Repository
public interface TbContentDao {
    /**
     * 查询商品全部信息
     * @return
     */
    public List<TbContent> selectAll();

    /**
     * 新增
     * @param tbContent
     */
    void insert(TbContent tbContent);

    /**
     * 删除
     * @param id
     */
    void delete(Long id);


    /**
     * 根据id查询商品信息
     * @param id
     * @return
     */
    TbContent getById(Long id);

    /**
     * 更新
     * @param tbContent
     */
    void updated(TbContent tbContent);

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
    List<TbContent> page(Map<String, Object> params);

    /**
     * 查询总笔数
     * @return
     */
    int count(TbContent tbContentr);
}
