package com.funtl.my.shop.web.admin.service.impl;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.dto.PageInfo;
import com.funtl.my.shop.domain.TbContentCategory;
import com.funtl.my.shop.web.admin.dao.TbContentCategoryDao;
import com.funtl.my.shop.web.admin.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author: TOLUNGKIT
 * @version: 1.0.0
 * @date: 2020-08-22 12:20
 **/

@Service
public class TbContentCategoryServiceImpl implements TbContentCategoryService {

    @Autowired
    private TbContentCategoryDao tbContentCategoryDao;

    @Override
    public List<TbContentCategory> selectAll() {
        return tbContentCategoryDao.selectAll();
    }

    @Override
    public List<TbContentCategory> selectByPid(Long pid) {
        return tbContentCategoryDao.selectByPid(pid);
    }

    @Override
    public TbContentCategory getById(Long id) {
        return tbContentCategoryDao.getById(id);
    }

    @Override
    public void update(TbContentCategory entity) {

    }

    @Override
    public void deleteMulti(String[] ids) {

    }

    @Override
    public PageInfo<TbContentCategory> page(int start, int length, int draw, TbContentCategory entity) {
        return null;
    }

    @Override
    public int count(TbContentCategory entity) {
        return 0;
    }

    @Override
    public BaseResult save(TbContentCategory tbContentCategory) {
        BaseResult baseResult = checkTbContent(tbContentCategory);

        //通过验证
        if(baseResult.getStatus() == BaseResult.STATUS_SUCCESS){

            tbContentCategory.setUpdated(new Date());

            // 新增商品
            if(tbContentCategory.getId() == null){
                tbContentCategory.setCreated(new Date());
                tbContentCategoryDao.insert(tbContentCategory);
            }

            // 编辑用户
            else{
                tbContentCategoryDao.updated(tbContentCategory);
            }
            baseResult.setMessage("保存商品信息成功");
        }

        return baseResult;
    }

    @Override
    public void delete(Long id) {

    }


    /**
     * 商品信息的有效性验证
     * @param tbContentCategory
     */
    private BaseResult checkTbContent(TbContentCategory tbContentCategory){

        BaseResult baseResult = BaseResult.success();

        //非空验证
        if(tbContentCategory.getName() == null){
            baseResult = BaseResult.fail("内容的所属分类不能为空，请重新输入" );
        }
        return baseResult;
    }
}
