package com.funtl.my.shop.web.admin.service.impl;

import com.funtl.my.shop.web.admin.dao.TbContentCategoryDao;
import com.funtl.my.shop.web.admin.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: TOLUNGKIT
 * @version: 1.0.0
 * @date: 2020-08-22 12:20
 **/

@Service
public class TbContentCategoryServiceImpl implements TbContentCategoryService {

    @Autowired
    private TbContentCategoryDao tbContentCategoryDao;
}
