package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishFlavorMapper {
    /**
     * 批量插入口味数据
     * @param flavors
     */
    void insertBatch(List<DishFlavor> flavors);

    /**
     * 根据菜品id获取口味
     * @param id
     * @return
     */
    @Select("select * from dish_flavor where dish_id = #{dishId} ")
     List<DishFlavor> getByDishId(Long id);

    @Delete("delete from dish_flavor where dish_id = #{dishId}")
    void deleteByDishId(Long id);
}