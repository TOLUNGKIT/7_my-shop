package com.funtl.my.shop.web.admin.service.impl;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.dto.PageInfo;
import com.funtl.my.shop.commons.validator.BeanValidator;
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
        tbContentCategoryDao.updated(entity);
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
    public BaseResult save(TbContentCategory entity) {
        // 验证输入 通过 sprint validation 组件验证
        String validator = BeanValidator.validator(entity);
        // 如果通过不了验证 则返回页面提示
        if(validator != null){
            return BaseResult.fail(validator);
        }
        else{
            // 获取从前台带回来的数据
            TbContentCategory parent = entity.getParent();
            // 如果没有选择父级节点 则默认设置为根目录
            if(parent == null || parent.getId() == null){
                // 0 代表根目录
                parent.setId(0L);
            }

            entity.setUpdated(new Date());
            // 新增
            if(entity.getId() == null){
                entity.setCreated(new Date());
                entity.setIsParent(false);

                // 判断当前新增的节点有没有父级节点
                if(parent.getId() != 0L){
                    TbContentCategory currentCategoryParent = getById(parent.getId());
                    if(currentCategoryParent != null){
                        // 为父级节点设置 isParent 为 true
                        currentCategoryParent.setIsParent(true);
                        update(currentCategoryParent);
                    }
                }

                // 父级节点为0，表示为根目录
                else{
                    // 根目录一定是父级目录
                    entity.setIsParent(true);
                }

                tbContentCategoryDao.insert(entity);
            }
            // 修改
            else {
                tbContentCategoryDao.updated(entity);
            }
            return BaseResult.success("保存信息成功");
        }
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
