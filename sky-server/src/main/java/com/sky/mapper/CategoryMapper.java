package com.sky.mapper;


import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Mapper
public interface CategoryMapper {
    /**
     * 分页查询
     * @param categoryPageQueryDTO
     * @return
     */
    Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * 新增分类
     * @param category
     * @return
     */
    @Insert("insert into category (type, name, sort, status, create_time, update_time, create_user, update_user) VALUES (#{type},#{name},#{sort},#{status},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    @AutoFill(OperationType.INSERT)
    void insert(Category category);

    /**
     * 根据主键删除分类
     * @param id
     * @return
     */
    @Delete("delete from category where id = #{id}")
    int deleteById(Long id);

    /**
     * 启用禁用
     * @param status
     * @param id
     */
    @Update("update category set status = #{status} where id = #{id}")
    void updateStatus(Integer status,Long id);

    /**
     * 更新分类数据
     * @param category
     */
    @AutoFill(OperationType.UPDATE)
    void update (Category category);

    /**
     * 根据类型查询分类
     * @param type
     * @return
     */
    List<Category> list(Integer type);
}
