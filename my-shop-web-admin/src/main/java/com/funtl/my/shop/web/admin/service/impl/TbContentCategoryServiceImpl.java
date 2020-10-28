package com.funtl.my.shop.web.admin.service.impl;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.validator.BeanValidator;
import com.funtl.my.shop.domain.TbContentCategory;
import com.funtl.my.shop.web.admin.abstracts.AbstractBaseTreeServiceImpl;
import com.funtl.my.shop.web.admin.dao.TbContentCategoryDao;
import com.funtl.my.shop.web.admin.service.TbContentCategoryService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author: TOLUNGKIT
 * @version: 1.0.0
 * @date: 2020-08-22 12:20
 **/

@Service
public class TbContentCategoryServiceImpl extends AbstractBaseTreeServiceImpl<TbContentCategory, TbContentCategoryDao> implements TbContentCategoryService {

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

                dao.insert(entity);
            }
            // 修改
            else {
                update(entity);
            }
            return BaseResult.success("保存信息成功");
        }
    }
}
