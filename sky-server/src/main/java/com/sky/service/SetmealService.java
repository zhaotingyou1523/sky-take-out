package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;

import java.util.List;

public interface SetmealService {
    /**
     * 分页查询
     * @param setmealPageQueryDTO
     * @return
     */
    PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * 新增套餐
     * @param setmealDTO
     */
    void saveWithDish(SetmealDTO setmealDTO);

    /**
     * 批量删除
     * @param ids
     */
    void deleteBatch(List<Long> ids);


    /**
     * 根据主键查询套餐
     * @param id
     * @return
     */
    SetmealVO findById(Long id);

    /**
     * 修改套餐
     * @param setmealDTO
     */
    void update(SetmealDTO setmealDTO);

    /**
     * 起售禁售
     * @param status
     * @param id
     */
    void updateStatus(Integer status, Long id);

    /**
     * 查询套餐列表
     * @param setmeal
     * @return
     */
    List<Setmeal> list(Setmeal setmeal);

    /**
     * 根据套餐主键获取菜品工具类
     * @param id
     * @return
     */
    List<DishItemVO> getDishItemsBySetmealId(Long id);
}
