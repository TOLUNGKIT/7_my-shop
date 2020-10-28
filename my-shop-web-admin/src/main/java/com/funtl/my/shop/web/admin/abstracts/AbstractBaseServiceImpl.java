package com.funtl.my.shop.web.admin.abstracts;

import com.funtl.my.shop.commons.dto.PageInfo;
import com.funtl.my.shop.commons.persistence.BaseDao;
import com.funtl.my.shop.commons.persistence.BaseEntity;
import com.funtl.my.shop.commons.persistence.BaseService;
import com.funtl.my.shop.domain.TbContent;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: TOLUNGKIT
 * @version: 1.0.0
 * @date: 2020-10-28 11:48
 **/
public abstract class AbstractBaseServiceImpl<T extends BaseEntity, D extends BaseDao<T>> implements BaseService<T> {

    @Autowired
    private D dao;

    /**
     * 查询全部信息
     * @return
     */
    @Override
    public List<T> selectAll(){
        return dao.selectAll();
    }

    /**
     * 删除信息
     * @param id
     */
    public void delete(Long id){
        dao.delete(id);
    }

    /**
     * 根据 ID 获取信息
     * @param id
     * @return
     */
    public T getById(Long id){
        return dao.getById(id);
    }

    /**
     * 更新信息
     * @param entity
     */
    public void update(T entity){
        dao.updated(entity);
    }

    /**
     * 批量删除
     * @param ids
     */
    public void deleteMulti(String[] ids){
        dao.deleteMulti(ids);
    }

    /**
     * 查询总笔数
     * @return
     */
    public int count(T entity){
        return dao.count(entity);
    }

    /**
     * 新增
     * @param entity
     */
    public void insert(T entity){
        dao.insert(entity);
    }

    /**
     * 分页查询
     * @param start
     * @param length
     * @param draw
     * @param entity
     * @return
     */
    @Override
    public PageInfo<T> page(int start, int length, int draw, T entity) {
        int count = count(entity);

        Map<String, Object> params = new HashMap<>();
        params.put("start", start);
        params.put("length", length);
        params.put("pageParams", entity);

        PageInfo<T> pageInfo = new PageInfo<>();
        pageInfo.setDraw(draw);
        pageInfo.setRecordsTotal(count);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setData(dao.page(params));

        return pageInfo;
    }
}
