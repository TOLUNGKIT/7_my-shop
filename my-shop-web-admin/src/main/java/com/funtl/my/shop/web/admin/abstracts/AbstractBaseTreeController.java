package com.funtl.my.shop.web.admin.abstracts;

import com.funtl.my.shop.commons.persistence.BaseTreeEntity;
import com.funtl.my.shop.commons.persistence.BaseTreeService;
import com.funtl.my.shop.domain.TbContentCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author: TOLUNGKIT
 * @version: 1.0.0
 * @date: 2020-10-29 11:38
 **/
public abstract class AbstractBaseTreeController<T extends BaseTreeEntity, S extends BaseTreeService<T>>{
    @Autowired
    protected S service;

    /**
     * 跳转列表页
     * @param model
     * @return
     */
    public abstract String list(Model model);

    /**
     * 跳转用户表单页
     * @return
     * @param tbContentCategory
     */
    public abstract String form(T entity);

    /**
     * 保存
     * @param tbContentCategory
     * @param model
     * @param redirectAttributes
     * @return
     */
    public abstract String save(T entity, Model model, RedirectAttributes redirectAttributes);

    /**
     * 树形结构
     * @param id
     * @return
     */
    public abstract List<TbContentCategory> treeData(Long id);

    /**
     * 排序
     * @param sourceList 数据源集合
     * @param targetList 排序后集合
     * @param parentId 父节点的ID
     */
    protected void sortList(List<T> sourceList, List<T> targetList, Long parentId){
        for (T sourceEntity : sourceList) {
            if (sourceEntity.getParent().getId().equals(parentId)){
                targetList.add(sourceEntity);

                //判断有没有子节点，如果有则继续追加
                if(sourceEntity.getIsParent()){
                    for(T currentEntity : sourceList){
                        if(currentEntity.getParent().getId().equals(sourceEntity.getId())){
                            sortList(sourceList, targetList, sourceEntity.getId());
                            break;
                        }
                    }
                }
            }
        }
    }
}
