package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.service.WorkspaceService;
import com.sky.vo.BusinessDataVO;
import com.sky.vo.DishOverViewVO;
import com.sky.vo.OrderOverViewVO;
import com.sky.vo.SetmealOverViewVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RestController
@RequestMapping("/admin/workspace/")
@Api(tags = "工作台相关接口")
@Slf4j
public class WorkspaceController {
    @Autowired
    private WorkspaceService workspaceService;

    @GetMapping("/businessData")
    @ApiOperation("查询今日相关数据")
    public Result<BusinessDataVO> businessData() {
        LocalDate now = LocalDate.now();
        LocalDateTime begin = LocalDateTime.of(now, LocalTime.MIN);
        LocalDateTime end = LocalDateTime.of(now, LocalTime.MAX);
        return Result.success(workspaceService.businessDate(begin,end));
    }

    @GetMapping("/overviewSetmeals")
    @ApiOperation("查询套餐总览")
    public Result<SetmealOverViewVO> overviewSetmeals() {
        return Result.success(workspaceService.overviewSetmeals());
    }

    @GetMapping("/overviewDishes")
    @ApiOperation("查询菜品总览")
    public Result<DishOverViewVO> overviewDishes() {
        return Result.success(workspaceService.overviewDishes());
    }

    @GetMapping("/overviewOrders")
    @ApiOperation("查询订单管理数据")
    public Result<OrderOverViewVO> overviewOrders() {
        return Result.success(workspaceService.overviewOrders());
    }
}
