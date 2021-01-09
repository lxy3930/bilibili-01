package com.bilibili.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bilibili.health.constant.MessageConstant;
import com.bilibili.health.entity.PageResult;
import com.bilibili.health.entity.QueryPageBean;
import com.bilibili.health.entity.Result;
import com.bilibili.health.pojo.CheckItem;
import com.bilibili.health.service.CheckItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author dwen
 * @date 2021/1/6 17:48
 */
@RestController
@RequestMapping("/checkItem")
public class CheckItemController {

    @Reference
    private CheckItemService checkItemService;


    /**
     * 查询所有检查项
     * @return
     */
    @GetMapping("/findAll")
    public Result findAll(){

        List<CheckItem> checkItemList = checkItemService.findAll();

        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, checkItemList);

    }

    /**
     * 新增检查项
     * @param checkItem
     * @return
     */
    @PostMapping("/add")
    public Result addCheckItem(@RequestBody CheckItem checkItem){

        checkItemService.add(checkItem);
        
        return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
    }


    /**
     * 分页条件查询
     * @param queryPageBean
     * @return
     */
    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){

        PageResult<CheckItem> pageResult = checkItemService.findPage(queryPageBean);

        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, pageResult);
    }


    /**
     * 根据id查询检查项
     * @param id
     * @return
     */
    @GetMapping("/findById")
    public Result findById(int id){
        CheckItem checkItem = checkItemService.findById(id);

        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, checkItem);
    }

    /**
     * 编辑检查项
     * @param checkItem
     * @return
     */
    @PostMapping("/update")
    public Result updateCheckItem(@RequestBody CheckItem checkItem){
        checkItemService.update(checkItem);

        return new Result(true, MessageConstant.EDIT_CHECKITEM_SUCCESS);
    }

    /**
     * 删除检查项
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public Result deleteCheckItem(int id){
        checkItemService.delete(id);

        return new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }
}
