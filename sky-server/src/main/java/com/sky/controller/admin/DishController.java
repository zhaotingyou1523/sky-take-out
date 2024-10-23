package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/dish")
@Api(tags = "菜品管理")
public class DishController {
    private static final Logger log = LoggerFactory.getLogger(DishController.class);
    @Autowired
    private DishService dishService;

    /**
     * 分页查询
     * @param dishPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("分页查询")
    public Result<?> page(DishPageQueryDTO dishPageQueryDTO) {
        log.info("分页查询:{}", dishPageQueryDTO);
        PageResult page = dishService.page(dishPageQueryDTO);
        return Result.success(page);
    }

    /**
     * 根据id查询菜品
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查询菜品")
    public Result<?> findById(@PathVariable Long id) {
        log.info("查询的id:{}",id);
        DishVO dishVO = dishService.findById(id);
        return Result.success(dishVO);
    }

    /**
     * 新增菜品
     * @param dishDTO
     * @return
     */
    @PostMapping
    @ApiOperation("新增菜品")
    public Result<?> save(@RequestBody DishDTO dishDTO) {
        log.info("新增的菜品:{}",dishDTO.toString());
        dishService.saveWithFlavor(dishDTO);
        return Result.success();
    }

    @PutMapping
    @ApiOperation("修改菜品和口味")
    public Result<?> update(@RequestBody DishDTO dishDTO) {
        dishService.updateWithFlavor(dishDTO);
        return Result.success();
    }

}
