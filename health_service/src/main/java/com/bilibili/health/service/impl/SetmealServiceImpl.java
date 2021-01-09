package com.bilibili.health.service.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;

import com.bilibili.health.dao.SetmealDao;
import com.bilibili.health.entity.PageResult;
import com.bilibili.health.entity.QueryPageBean;
import com.bilibili.health.pojo.CheckGroup;
import com.bilibili.health.pojo.Setmeal;
import com.bilibili.health.service.SetmealService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author dwen
 * @date 2021/1/9 18:00
 */

@Service(interfaceClass = SetmealService.class)
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealDao setmealDao;

    /**
     * 添加套餐
     * @param setmeal
     * @param checkgroupIds
     */
    @Override
    public void add(Setmeal setmeal, Integer[] checkgroupIds) {
        setmealDao.add(setmeal);

        Integer setmealId = setmeal.getId();

        // 添加套餐与检查组的关系
        if (checkgroupIds != null){
            for (Integer checkgroupId : checkgroupIds) {
                setmealDao.addSetmealAndCheckGroup(setmealId,checkgroupId);
            }
        }
    }

    /**
     * 分页条件查询
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult<Setmeal> findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());

        // 模糊查询 拼接 %
        // 判断是否有查询条件
        if (StringUtils.isNotEmpty(queryPageBean.getQueryString())){
            queryPageBean.setQueryString("%" + queryPageBean.getQueryString() + "%");
        }

        Page<Setmeal> page = setmealDao.findByCondition(queryPageBean.getQueryString());
        PageResult<Setmeal> pageResult = new PageResult<Setmeal>(page.getTotal(), page.getResult());
        return pageResult;
    }
}
