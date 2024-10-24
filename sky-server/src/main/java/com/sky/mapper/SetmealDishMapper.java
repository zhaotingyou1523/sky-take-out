package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealDishMapper {
    @Delete("delete from setmeal_dish where dish_id = #{id}")
    int deleteByDishId(Long id);

    List<Long> getSetmealIdsByDishIds(List<Long> ids);
}
