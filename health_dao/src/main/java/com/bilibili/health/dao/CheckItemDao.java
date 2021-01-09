package com.bilibili.health.dao;

import com.bilibili.health.pojo.CheckItem;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * @author dwen
 * @date 2021/1/6 17:53
 */
public interface CheckItemDao {
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
     * @param queryString
     * @return
     */
    Page<CheckItem> findByCondition(String queryString);

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
     * 根据检查项id查询中间表关系是否存在
     * @param id
     * @return
     */
    Long findCountByCheckItemId(int id);

    /**
     * 根据id删除检查项
     * @param id
     */
    void deleteById(int id);
}
