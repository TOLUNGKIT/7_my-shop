package com.funtl.my.shop.commons.persistence;

import lombok.Data;

import java.io.Serializable;

/**
 * 继承 基础实体，同时增加特殊 parent 属性
 * @author: TOLUNGKIT
 * @version: 1.0.0
 * @date: 2020-10-29 11:56
 **/
@Data
public abstract class BaseTreeEntity<T extends BaseEntity> extends BaseEntity implements Serializable {
    private T parent;
    private Boolean isParent;
}
