package com.funtl.my.shop.web.api.web.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 内容传输对象
 * @author: TOLUNGKIT
 * @version: 1.0.0
 * @date: 2020-10-31 10:44
 **/
@Data
public class TbContentDTO implements Serializable {
    private Long id;
    private String title;
    private String subTitle;
    private String url;
    private String pic;
    private String pic2;
    private String content;
}
