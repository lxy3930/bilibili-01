package com.bilibili.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bilibili.health.constant.MessageConstant;
import com.bilibili.health.entity.PageResult;
import com.bilibili.health.entity.QueryPageBean;
import com.bilibili.health.entity.Result;
import com.bilibili.health.pojo.CheckGroup;
import com.bilibili.health.service.CheckGroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author dwen
 * @date 2021/1/8 16:26
 */
@RestController
@RequestMapping("/checkGroup")
public class CheckGroupController {

    @Reference
    private CheckGroupService checkGroupService;

    /**
     * 添加检查组
     * @param checkGroup
     * @return
     */
    @RequestMapping("/add")
    public Result addCheckGroup(@RequestBody CheckGroup checkGroup,Integer[] checkitemIds){
        checkGroupService.add(checkGroup,checkitemIds);

        return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult<CheckGroup> pageResult = checkGroupService.findPage(queryPageBean);

        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,pageResult);
    }

    /**
     * 根据id查询检查组
     * @param id
     * @return
     */
    @GetMapping("/findById")
    public Result findById(int id){
        CheckGroup checkGroup = checkGroupService.findById(id);
        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroup);
    }

    /**
     * 通过检查组id查询选中的检查项id
     * @param id
     * @return
     */
    @GetMapping("/findCheckItemIdsByCheckGroupId")
    public Result findCheckItemIdsByCheckGroupId(int id){
        // 通过检查组id查询选中的检查项id集合
        List<Integer> checkItemIds = checkGroupService.findCheckItemIdsByCheckGroupId(id);

        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItemIds);
    }

    /**
     * 编辑检查组
     * @param checkGroup
     * @param checkitemIds
     * @return
     */
    @PostMapping("/update")
    public Result updateCheckGroup(@RequestBody CheckGroup checkGroup,Integer[] checkitemIds){
        checkGroupService.update(checkGroup,checkitemIds);

        return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
    }


    /**
     * 根据id删除检查组
     * @param id
     * @return
     */
    @PostMapping("/deleteById")
    public Result delete(int id){
        checkGroupService.deleteById(id);

        return new Result(true, MessageConstant.DELETE_CHECKGROUP_SUCCESS);
    }


    /**
     * 查询所有检查组
     * @return
     */
    @GetMapping("/findAll")
    public Result findAll(){
        List<CheckGroup> checkGroupList = checkGroupService.findAll();

        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, checkGroupList);
    }
}
