package com.funtl.my.shop.web.admin.service.impl;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.dto.PageInfo;
import com.funtl.my.shop.commons.validator.BeanValidator;
import com.funtl.my.shop.domain.TbContent;
import com.funtl.my.shop.web.admin.abstracts.AbstractBaseServiceImpl;
import com.funtl.my.shop.web.admin.dao.TbContentDao;
import com.funtl.my.shop.web.admin.service.TbContentService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: TOLUNGKIT
 * @version: 1.0.0
 * @date: 2020-08-25 15:20
 **/
@Service
public class TbContentServiceImpl extends AbstractBaseServiceImpl<TbContent, TbContentDao> implements TbContentService {

    @Override
    public BaseResult save(TbContent tbContent) {
        String validator = BeanValidator.validator(tbContent);

        //验证不通过
        if(validator != null){
            return BaseResult.fail(validator);
        }

        //验证通过
        else{
            tbContent.setUpdated(new Date());

            // 新增商品
            if(tbContent.getId() == null){
                tbContent.setCreated(new Date());
                insert(tbContent);
            }

            // 编辑用户
            else{
                update(tbContent);
            }

            return BaseResult.success("保存商品信息成功");
        }
    }


}
