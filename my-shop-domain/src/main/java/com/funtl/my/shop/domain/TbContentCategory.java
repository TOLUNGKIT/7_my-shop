package com.funtl.my.shop.domain;

import com.funtl.my.shop.commons.persistence.BaseEntity;
import lombok.Data;

/**
 * 分类管理
 * @author: TOLUNGKIT
 * @version: 1.0.0
 * @date: 2020-08-22 12:03
 **/
@Data
public class TbContentCategory extends BaseEntity {
    private Long parentId;
    private String name;
    private Integer status;
    private Integer sortOrder;

    /**
     * 换成 Json 需要的别名，而不需要改代码
     *
     * 用了 Lombok 之后自动生成 getIsParent 不需要 Json
     */
//    @JsonProperty(value = "isParent")
    private Boolean isParent;
    private TbContentCategory parent;
}
