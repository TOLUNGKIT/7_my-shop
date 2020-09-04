package com.funtl.my.shop.domain;

import com.funtl.my.shop.commons.persistence.BaseEntity;
import lombok.Data;

/**
 * 内容管理
 * @author: TOLUNGKIT
 * @version: 1.0.0
 * @date: 2020-08-25 15:14
 **/
@Data
public class TbContent extends BaseEntity {
    private Long categoryId;
    private String title;
    private String subTitle;
    private String titleDesc;
    private String url;
    private String pic;
    private String pic2;
    private String content;
}
