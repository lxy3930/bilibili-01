package com.bilibili.health.dao;


import com.bilibili.health.pojo.CheckGroup;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author dwen
 * @date 2021/1/8 16:39
 */
public interface CheckGroupDao {

    /**
     * 添加检查组
     * @param checkGroup
     */
    void add(CheckGroup checkGroup);

    /**
     * 添加中间表
     * @param checkGroupId
     * @param checkitemId
     */
    void addCheckgroupAndCheckItem(@Param("checkGroupId") Integer checkGroupId, @Param("checkitemId") Integer checkitemId);

    /**
     * 检查组查询条件
     * @param queryString
     * @return
     */
    Page<CheckGroup> findByCondition(String queryString);

    /**
     * 根据id查询检查组
     * @param id
     * @return
     */
    CheckGroup findById(int id);

    /**
     * 通过检查组id查询选中的检查项id
     * @param id
     * @return
     */
    List<Integer> findCheckItemIdsByCheckGroupId(int id);

    /**
     * 编辑检查组
     * @param checkGroup
     */
    void updateCheckGroup(CheckGroup checkGroup);

    /**
     * 删除旧的中间表数据
     * @param id
     */
    void deleteCheckGroupAndCheckItem(Integer id);

    /**
     * 查询检查组与套餐是否有关联
     * @param id
     * @return
     */
    int findSetmealByCheckGroupId(int id);

    /**
     * 删除检查组
     * @param id
     */
    void deleteById(int id);

    /**
     * 查询所有检查组
     * @return
     */
    List<CheckGroup> findAll();
}
