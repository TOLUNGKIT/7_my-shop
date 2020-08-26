package com.funtl.my.shop.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.funtl.my.shop.commons.persistence.BaseEntity;

/**
 * @author: TOLUNGKIT
 * @version: 1.0.0
 * @date: 2020-08-22 12:03
 **/
public class TbContentCategory extends BaseEntity {
    private Long parentId;
    private String name;
    private Integer status;
    private Integer sortOrder;

    /**
     * 换成 Json 需要的别名，而不需要改代码
     */
    @JsonProperty(value = "isParent")
    private Boolean isParent;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Boolean getParent() {
        return isParent;
    }

    public void setParent(Boolean parent) {
        isParent = parent;
    }
}
