package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealDishMapper {
    /**
     * 根据套餐id删除
     * @param id
     * @return
     */
    @Delete("delete from setmeal_dish where setmeal_id = #{id}")
    void deleteBySetmealId(Long id);

    /**
     *
     * @param ids
     * @return
     */
    List<Long> getSetmealIdsByDishIds(List<Long> ids);


    /**
     * 新增菜品套餐关系
     * @param setmealDishes
     */
    void save(List<SetmealDish> setmealDishes);

    /**
     * 根据套餐主键查找菜品主键
     * @param id
     * @return
     */
    @Select("select dish_id from setmeal_dish where setmeal_id = #{id}")
    List<Long> getDishIdsBySetmealId(Long id);
}
