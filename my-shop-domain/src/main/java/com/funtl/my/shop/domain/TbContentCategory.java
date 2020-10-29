package com.funtl.my.shop.domain;

import com.funtl.my.shop.commons.persistence.BaseTreeEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 分类管理
 * @author: TOLUNGKIT
 * @version: 1.0.0
 * @date: 2020-08-22 12:03
 **/
@Data
public class TbContentCategory extends BaseTreeEntity {
    private Long parentId;
    @Length(min = 1, max = 20, message = "分类名称必须介于1~20位之间")
    private String name;
    private Integer status;
    @NotNull(message = "排序不能为空")
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
