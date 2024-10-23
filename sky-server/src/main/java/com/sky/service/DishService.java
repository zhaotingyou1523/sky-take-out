package com.sky.service;


import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;

public interface DishService {
    PageResult page(DishPageQueryDTO dishPageQueryDTO);

    Dish findById(Integer id);

    void save(DishDTO dishDTO);
}
