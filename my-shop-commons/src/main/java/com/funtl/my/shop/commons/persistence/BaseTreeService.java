package com.funtl.my.shop.commons.persistence;

import com.funtl.my.shop.commons.dto.BaseResult;

import java.util.List;

/**
 * 通用的树形接口
 * @author: TOLUNGKIT
 * @version: 1.0.0
 * @date: 2020-10-28 10:40
 **/
public interface BaseTreeService<T extends BaseEntity> {
    /**
     * 查询全部信息
     * @return
     */
    public List<T> selectAll();

    /**
     * 新增
     * @param entity
     */
    BaseResult save(T entity);

    /**
     * 删除
     * @param id
     */
    void delete(Long id);

    /**
     * 根据id查询信息
     * @param id
     * @return
     */
    T getById(Long id);

    /**
     * 更新
     * @param entity
     */
    void update(T entity);

    /**
     * 根据父级节点 ID 查询所有子节点
     * @param pid
     * @return
     */
    List<T> selectByPid(Long pid);
}
