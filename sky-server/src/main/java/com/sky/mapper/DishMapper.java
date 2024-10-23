package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface DishMapper {
    Page<Dish> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    @Select("select * from dish where id = #{id}")
    Dish getById(Integer id);

    @Insert("insert into dish (name, category_id, price, image, description, status, create_time, update_time, create_user, update_user) " +
            "VALUES " +
            "(#{name},#{categoryId},#{price},#{image},#{description},#{status},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    @AutoFill(OperationType.INSERT)
    void insert(Dish dish);

    @Delete("delete from dish where id = #{id}")
    void deleteById(Integer id);

    @Select("select a.* from dish a left join setmeal_dish b on a.id = b.dish_id where b.setmeal_id = #{setmealId}")
    List<Dish> getBySetmealId(Long setmealId);

    Integer countByMap(Map map);

    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    @AutoFill(OperationType.UPDATE)
    void update(Dish dish);
}
