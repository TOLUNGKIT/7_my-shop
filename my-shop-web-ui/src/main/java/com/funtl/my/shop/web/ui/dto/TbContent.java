package com.funtl.my.shop.web.ui.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: TOLUNGKIT
 * @version: 1.0.0
 * @date: 2020-10-31 17:24
 **/
@Data
public class TbContent implements Serializable {
    private long id;
    private String title;
    private String subTitle;
    private String titleDesc;
    private String url;
    private String pic;
    private String pic2;
    private String content;
}
