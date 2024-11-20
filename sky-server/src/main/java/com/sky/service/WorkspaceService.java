package com.sky.service;

import com.sky.vo.BusinessDataVO;
import com.sky.vo.DishOverViewVO;
import com.sky.vo.OrderOverViewVO;
import com.sky.vo.SetmealOverViewVO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface WorkspaceService {
    BusinessDataVO businessDate(LocalDateTime begin, LocalDateTime end);

    SetmealOverViewVO overviewSetmeals();

    DishOverViewVO overviewDishes();

    OrderOverViewVO overviewOrders();
}
