package com.funtl.my.shop.web.admin.abstracts;

import com.funtl.my.shop.commons.persistence.BaseEntity;
import com.funtl.my.shop.commons.persistence.BaseTreeDao;
import com.funtl.my.shop.commons.persistence.BaseTreeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author: TOLUNGKIT
 * @version: 1.0.0
 * @date: 2020-10-28 10:59
 **/
public abstract class AbstractBaseTreeServiceImpl<T extends BaseEntity, D extends BaseTreeDao<T>> implements BaseTreeService<T> {

    @Autowired
    protected D dao;

    /**
     * 查询全部信息
     * @return
     */
    @Override
    public List<T> selectAll(){
        return dao.selectAll();
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    /**
     * 根据ID查询信息
     * @param id
     * @return
     */
    @Override
    public T getById(Long id) {
        return dao.getById(id);
    }

    /**
     * 更新
     * @param entity
     */
    @Override
    public void update(T entity) {
        dao.updated(entity);
    }

    /**
     * 根据父节点ID查询所有子节点
     *
     * @param pid
     * @return
     */
    @Override
    public List<T> selectByPid(Long pid) {
        return dao.selectByPid(pid);
    }

}
