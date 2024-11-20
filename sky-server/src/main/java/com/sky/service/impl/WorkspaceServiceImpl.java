package com.sky.service.impl;

import com.sky.entity.Orders;
import com.sky.mapper.DishMapper;
import com.sky.mapper.OrderMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.mapper.UserMapper;
import com.sky.result.Result;
import com.sky.service.WorkspaceService;

import com.sky.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;


@Service
@Slf4j
public class WorkspaceServiceImpl implements WorkspaceService {
    @Autowired
    public OrderMapper orderMapper;
    @Autowired
    public UserMapper userMapper;
    @Autowired
    public SetmealMapper setmealMapper;
    @Autowired
    public DishMapper dishMapper;

    /**
     * 查询今日数据
     * @return
     */
    public BusinessDataVO businessDate(LocalDateTime begin, LocalDateTime end) {

        Map map = new HashMap();
        map.put("begin", begin);
        map.put("end", end);
        Integer newUser = userMapper.countByMap(map);
        Integer totalOrders = orderMapper.countByMap(map);
        map.put("status", Orders.COMPLETED);
        Integer validOrderCount = orderMapper.countByMap(map);
        Double orderCompletionRate = validOrderCount.doubleValue() / totalOrders;
        Double turnover = orderMapper.sumByMap(map);
        turnover = turnover == null ? 0.0 : turnover;
        Double unitPrice = turnover / totalOrders;
        return BusinessDataVO
                .builder()
                .newUsers(newUser)
                .turnover(turnover)
                .unitPrice(unitPrice)
                .validOrderCount(validOrderCount)
                .orderCompletionRate(orderCompletionRate)
                .build();


    }


    /**
     * 查询套餐总览
     * @return
     */
    public SetmealOverViewVO overviewSetmeals() {
        Integer discontinued = setmealMapper.countByStatus(0);
        Integer sold = setmealMapper.countByStatus(1);
        return SetmealOverViewVO
                .builder()
                .discontinued(discontinued)
                .sold(sold)
                .build();
    }

    /**
     * 查询菜品总览
     * @return
     */
    public DishOverViewVO overviewDishes() {
        Map map = new HashMap();
        map.put("status",0);
        Integer discontinued = dishMapper.countByMap(map);
        map.put("status",1);
        Integer sold = dishMapper.countByMap(map);
        return DishOverViewVO
                .builder()
                .discontinued(discontinued)
                .sold(sold)
                .build();
    }

    /**
     * 查询订单管理数据
     * @return
     */
    public OrderOverViewVO overviewOrders() {
        Map map = new HashMap();
        map.put("begin",LocalDateTime.of(LocalDate.now(), LocalTime.MIN));
        Integer allOrders = orderMapper.countByMap(map);
        map.put("status",6);
        Integer cancelledOrders = orderMapper.countByMap(map);
        map.put("status",5);
        Integer completedOrders = orderMapper.countByMap(map);
        map.put("status",3);
        Integer deliveredOrders = orderMapper.countByMap(map);
        map.put("status",2);
        Integer waitingOrders = orderMapper.countByMap(map);
        return OrderOverViewVO
                .builder()
                .allOrders(allOrders)
                .cancelledOrders(cancelledOrders)
                .completedOrders(completedOrders)
                .deliveredOrders(deliveredOrders)
                .waitingOrders(waitingOrders)
                .build();


    }
}
