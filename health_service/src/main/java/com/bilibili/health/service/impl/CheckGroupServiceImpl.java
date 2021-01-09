package com.bilibili.health.service.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.bilibili.health.dao.CheckGroupDao;
import com.bilibili.health.entity.PageResult;
import com.bilibili.health.entity.QueryPageBean;
import com.bilibili.health.exception.MyException;
import com.bilibili.health.pojo.CheckGroup;
import com.bilibili.health.service.CheckGroupService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author dwen
 * @date 2021/1/8 16:36
 */
@Service(interfaceClass = CheckGroupService.class)
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupDao checkGroupDao;

    /**
     * 添加检查组
     * @param checkGroup
     */
    @Override
    @Transactional
    public void add(CheckGroup checkGroup,Integer[] checkitemIds) {
        checkGroupDao.add(checkGroup);

        Integer checkGroupId = checkGroup.getId();

        // 判断检查项是否为空，不为空就往中间表插入数据
        if (checkitemIds != null){

            for (Integer checkitemId : checkitemIds) {
                checkGroupDao.addCheckgroupAndCheckItem(checkGroupId,checkitemId);
            }
        }
    }

    /**
     * 检查组分页查询
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult<CheckGroup> findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());

        // 模糊查询 拼接 %
        // 判断是否有查询条件
        if (StringUtils.isNotEmpty(queryPageBean.getQueryString())){
            queryPageBean.setQueryString("%" + queryPageBean.getQueryString() + "%");
        }

        Page<CheckGroup> page = checkGroupDao.findByCondition(queryPageBean.getQueryString());
        PageResult<CheckGroup> pageResult = new PageResult<CheckGroup>(page.getTotal(), page.getResult());
        return pageResult;
    }

    /**
     * 根据id查询检查组
     * @param id
     * @return
     */
    @Override
    public CheckGroup findById(int id) {
        return checkGroupDao.findById(id);
    }

    /**
     * 通过检查组id查询选中的检查项id
     * @param id
     * @return
     */
    @Override
    public List<Integer> findCheckItemIdsByCheckGroupId(int id) {
        return checkGroupDao.findCheckItemIdsByCheckGroupId(id);
    }

    /**
     * 编辑检查组
     * @param checkGroup
     * @param checkitemIds
     */
    @Override
    @Transactional
    public void update(CheckGroup checkGroup, Integer[] checkitemIds) {
        // 编辑检查组
        checkGroupDao.updateCheckGroup(checkGroup);

        // 删除旧的中间表数据
        checkGroupDao.deleteCheckGroupAndCheckItem(checkGroup.getId());


        if (checkitemIds != null){
            // 添加新的中间表数据
            for (Integer checkitemId : checkitemIds) {
                checkGroupDao.addCheckgroupAndCheckItem(checkGroup.getId(),checkitemId);
            }
        }
    }

    /**
     * 根据id删除检查组
     * @param id
     */
    @Override
    @Transactional
    public void deleteById(int id) throws MyException{
        // 查询 这个检查组是否与套餐有关联
        int count = checkGroupDao.findSetmealByCheckGroupId(id);

        if (count > 0){
            throw new MyException("检查组与套餐业务有关联，无法删除！");
        }

        // 没有被套餐使用,就可以删除数据
        // 先删除检查组与检查项的关系
        checkGroupDao.deleteCheckGroupAndCheckItem(id);

        // 删除检查组
        checkGroupDao.deleteById(id);
    }

    /**
     * 查询所有检查组
     * @return
     */
    @Override
    public List<CheckGroup> findAll() {
        return checkGroupDao.findAll();
    }


}
