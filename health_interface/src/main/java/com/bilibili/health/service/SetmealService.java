package com.bilibili.health.service;

import com.bilibili.health.entity.PageResult;
import com.bilibili.health.entity.QueryPageBean;
import com.bilibili.health.pojo.CheckGroup;
import com.bilibili.health.pojo.Setmeal;

/**
 * @author dwen
 * @date 2021/1/9 17:03
 */
public interface SetmealService {

    /**
     * 新增套餐
     * @param setmeal
     * @param checkgroupIds
     */
    void add(Setmeal setmeal, Integer[] checkgroupIds);

    /**
     * 分页条件查询
     * @param queryPageBean
     * @return
     */
    PageResult<Setmeal> findPage(QueryPageBean queryPageBean);
}
