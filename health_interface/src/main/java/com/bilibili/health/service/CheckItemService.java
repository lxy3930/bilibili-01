package com.bilibili.health.service;

import com.bilibili.health.entity.PageResult;
import com.bilibili.health.entity.QueryPageBean;
import com.bilibili.health.pojo.CheckItem;

import java.util.List;

/**
 * @author dwen
 * @date 2021/1/6 17:50
 */

public interface CheckItemService {
    /**
     * 查询所有检查项
     * @return
     */
    List<CheckItem> findAll();

    /**
     * 新增检查项
     * @param checkItem
     */
    void add(CheckItem checkItem);

    /**
     * 分页条件查询
     * @param queryPageBean
     * @return
     */
    PageResult<CheckItem> findPage(QueryPageBean queryPageBean);

    /**
     * 根据id查询检查项
     * @param id
     * @return
     */
    CheckItem findById(int id);

    /**
     * 编辑检查项
     * @param checkItem
     */
    void update(CheckItem checkItem);

    /**
     * 删除检查项
     * @param id
     */
    void delete(int id);
}
