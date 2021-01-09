package com.bilibili.health.dao;

import com.bilibili.health.pojo.Setmeal;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

/**
 * @author dwen
 * @date 2021/1/9 18:01
 */
public interface SetmealDao {
    /**
     * 添加套餐
     * @param setmeal
     */
    void add(Setmeal setmeal);

    /**
     * 添加套餐与检查组的关系
     * @param setmealId
     * @param checkgroupId
     */
    void addSetmealAndCheckGroup(@Param("setmealId") Integer setmealId,@Param("checkgroupId") Integer checkgroupId);

    /**
     * 分页条件查询
     * @param queryString
     * @return
     */
    Page<Setmeal> findByCondition(String queryString);
}
