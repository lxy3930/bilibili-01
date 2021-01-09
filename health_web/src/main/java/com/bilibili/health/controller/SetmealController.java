package com.bilibili.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bilibili.health.constant.MessageConstant;
import com.bilibili.health.entity.PageResult;
import com.bilibili.health.entity.QueryPageBean;
import com.bilibili.health.entity.Result;
import com.bilibili.health.pojo.CheckGroup;
import com.bilibili.health.pojo.Setmeal;
import com.bilibili.health.service.SetmealService;
import com.bilibili.health.utils.QiNiuUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.security.auth.login.CredentialNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author dwen
 * @date 2021/1/9 17:02
 */

@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Reference
    private SetmealService setmealService;


    /**
     * 套餐上传图片
     * @param imgFile
     * @return
     */
    @RequestMapping("/upload")
    public Result upload(MultipartFile imgFile){
        // 获取原有图片名称，截取到后缀名
        String originalFilename = imgFile.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        // 生成唯一文件名，拼接后缀名
        String fileName = UUID.randomUUID().toString() + suffix;

        //- 调用七牛上传文件
        try {
            QiNiuUtils.uploadViaByte(imgFile.getBytes(), fileName);
            // 返回数据给页面
            Map<String,String> map = new HashMap<String, String>();
            map.put("imgName", fileName);
            map.put("domain", QiNiuUtils.DOMAIN);
            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS, map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
    }

    /**
     * 添加套餐
     * @param setmeal
     * @param checkgroupIds
     * @return
     */
    @PostMapping("/add")
    public Result addSetmeal(@RequestBody Setmeal setmeal, Integer[] checkgroupIds){
        setmealService.add(setmeal,checkgroupIds);

        return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
    }

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult<Setmeal> pageResult = setmealService.findPage(queryPageBean);

        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS,pageResult);
    }
}
