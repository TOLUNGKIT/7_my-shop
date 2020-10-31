package com.funtl.my.shop.web.api.web.controller.v1;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.domain.TbContent;
import com.funtl.my.shop.web.api.service.TbContentService;
import com.funtl.my.shop.web.api.web.dto.TbContentDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: TOLUNGKIT
 * @version: 1.0.0
 * @date: 2020-10-31 01:16
 **/
// @RestController 返回客户端页面的数据都是JSON，因为没有视图解析器  @ResponseBody
@RestController
// 在 myshop.properties 配置 api.path
@RequestMapping(value = "${api.path.v1}/contents")
public class TbContentController {
    @Autowired
    private TbContentService tbContentService;

    @ModelAttribute
    public TbContent getTbContent(Long id){
        TbContent tbContent = null;

        if(id == null){
            tbContent = new TbContent();
        }

        return tbContent;
    }

    /**
     * API文档 根据类别 ID 查询内容列表
     * 请求地址：http://localhost:8081/contents/<category_id>
     * 请求方式：GET
     * 请求参数：category_id 类目 ID 必须 数字
     * @param categoryId
     * @return
     */
    @RequestMapping(value = "{category_id}", method = RequestMethod.GET)
    public BaseResult findContentByCategoryId(@PathVariable(value = "category_id") Long categoryId){
        List<TbContentDTO> tbContentDTOS = null;
        List<TbContent> tbContents = tbContentService.selectByCategoryId(categoryId);
        if(tbContents != null && tbContents.size() >0){
            tbContentDTOS = new ArrayList<>();
            for (TbContent tbContent : tbContents) {
                TbContentDTO dto = new TbContentDTO();
                // 将POJO 原生java对象 转换成 DTO 传输需要的对象 简化数据
                BeanUtils.copyProperties(tbContent, dto);
                tbContentDTOS.add(dto);
            }
        }
        return BaseResult.success("成功", tbContentDTOS);
    }
}
