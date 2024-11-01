package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.mapper.DishMapper;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("admin/dish")
@Api(tags = "菜品相关接口")
public class DishController {

    public static final String DELETE_DISH = "DishCache";

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
    @CachePut(value = DELETE_DISH, key = "#dishDTO.categoryId")
    public Result<?> save(@RequestBody DishDTO dishDTO) {
        log.info("新增的菜品:{}",dishDTO.toString());
        dishService.saveWithFlavor(dishDTO);
        return Result.success();
    }

    /**
     * 修改菜品和口味
     * @param dishDTO
     * @return
     */
    @PutMapping
    @ApiOperation("修改菜品和口味")
    @CacheEvict(value = DELETE_DISH, allEntries = true)
    public Result<?> update(@RequestBody DishDTO dishDTO) {
        log.info("修改菜品和口味:{}",dishDTO.toString());
        dishService.updateWithFlavor(dishDTO);
        return Result.success();
    }

    /**
     * 起售 禁售
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("起售禁售")
    @CacheEvict(value = DELETE_DISH, allEntries = true)
    public Result<?> updateStatus(@PathVariable Integer status,Long id) {
        log.info("起售禁售:{},{}",status,id);
        Dish dish = new Dish();
        dish.setId(id);
        dish.setStatus(status);
        dishService.updateStatus(dish);
        return Result.success();
    }

    /**
     * 菜品批量删除
     * @param ids
     * @return
     */
    @DeleteMapping
    @ApiOperation("菜品批量删除")
    @CacheEvict(value = DELETE_DISH, allEntries = true)
    public Result<?> delete(@RequestParam List<Long> ids) {
        log.info("菜品批量删除:{}",ids);
        dishService.deleteBatch(ids);

        //将所有的菜品缓存数据清理掉,所有以dish_开头的key
        return Result.success();
    }

    /**
     * 根据分类id查询菜品
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("根据分类id查询菜品")
    public Result<List<Dish>> list(Long categoryId) {
        log.info("根据分类id查询菜品:{}",categoryId);
        List<Dish> list = dishService.list(categoryId);
        return Result.success(list);
    }

//    /**
//     * 清理缓存数据
//     * @param pattern
//     */
//    private void cleanCache(String pattern){
//        Set keys = redisTemplate.keys(pattern);
//        log.info(keys.toString());
//        redisTemplate.delete(keys);
//    }
}
