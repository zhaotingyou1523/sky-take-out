package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/dish")
public class DishController {
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
    public Result<?> findById(@PathVariable Integer id) {
        Dish dish = dishService.findById(id);
        return Result.success(dish);
    }

    @PostMapping
    @ApiOperation("新增菜品")
    public Result<?> save(@RequestBody DishDTO dishDTO) {
        dishService.save(dishDTO);
        return Result.success();
    }
}
