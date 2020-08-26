package com.funtl.my.shop.web.admin.service.impl;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.dto.PageInfo;
import com.funtl.my.shop.commons.utils.RegexpUtils;
import com.funtl.my.shop.domain.TbContent;
import com.funtl.my.shop.web.admin.dao.TbContentDao;
import com.funtl.my.shop.web.admin.service.TbContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: TOLUNGKIT
 * @version: 1.0.0
 * @date: 2020-08-25 15:20
 **/
@Service
public class TbContentServiceImpl implements TbContentService {

    @Autowired
    private TbContentDao tbContentDao;

    @Override
    public List<TbContent> selectAll() {
        return tbContentDao.selectAll();
    }

    @Override
    public BaseResult save(TbContent tbContent) {
        BaseResult baseResult = checkTbContent(tbContent);

        //通过验证
        if(baseResult.getStatus() == BaseResult.STATUS_SUCCESS){

            tbContent.setUpdated(new Date());

            // 新增商品
            if(tbContent.getId() == null){
                tbContent.setCreated(new Date());
                tbContentDao.insert(tbContent);
            }

            // 编辑用户
            else{
                tbContentDao.updated(tbContent);
            }
            baseResult.setMessage("保存商品信息成功");
        }

        return baseResult;
    }

    @Override
    public void delete(Long id) {
        tbContentDao.delete(id);
    }

    @Override
    public TbContent getById(Long id) {
        return tbContentDao.getById(id);
    }

    @Override
    public void updated(TbContent tbContent) {
        tbContentDao.updated(tbContent);
    }

    @Override
    public void deleteMulti(String[] ids) {
        tbContentDao.deleteMulti(ids);
    }

    @Override
    public PageInfo<TbContent> page(int start, int length, int draw, TbContent tbContent) {
        int count = tbContentDao.count(tbContent);

        Map<String, Object> params = new HashMap<>();
        params.put("start", start);
        params.put("length", length);
        params.put("tbContent", tbContent);

        PageInfo<TbContent> pageInfo = new PageInfo<>();
        pageInfo.setDraw(draw);
        pageInfo.setRecordsTotal(count);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setData(tbContentDao.page(params));

        return pageInfo;
    }

    @Override
    public int count(TbContent tbContent) {
        return tbContentDao.count(tbContent);
    }

    /**
     * 商品信息的有效性验证
     * @param tbContent
     */
    private BaseResult checkTbContent(TbContent tbContent){

        BaseResult baseResult = BaseResult.success();

        //非空验证
        if(tbContent.getCategoryId() == null){
            baseResult = BaseResult.fail("内容的所属分类不能为空，请重新输入" );
        }
        else if (StringUtils.isBlank(tbContent.getTitle())) {
            baseResult = BaseResult.fail("标题不能为空，请重新输入" );
        }
        else if (StringUtils.isBlank(tbContent.getSubTitle())) {
            baseResult = BaseResult.fail("子标题不能为空，请重新输入");
        }
        else if (StringUtils.isBlank(tbContent.getTitleDesc())) {
            baseResult = BaseResult.fail("标题描述不能为空，请重新输入");
        }
        else if (StringUtils.isBlank(tbContent.getUrl())) {
            baseResult = BaseResult.fail("地址不能为空，请重新输入");
        }
        else if (!RegexpUtils.checkPhone(tbContent.getPic())) {
            baseResult = BaseResult.fail("相片不能为空，请重新输入" );
        }
        else if (!RegexpUtils.checkPhone(tbContent.getPic2())) {
            baseResult = BaseResult.fail("相片不能为空，请重新输入" );
        }
        else if (!RegexpUtils.checkPhone(tbContent.getContent())) {
            baseResult = BaseResult.fail("内容不能为空，请重新输入" );
        }
        return baseResult;
    }
}
