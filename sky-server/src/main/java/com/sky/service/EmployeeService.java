package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.dto.EmployeeUpdatePasswordDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 添加员工
     * @param employeeDTO
     * @return
     */
    boolean Insert (EmployeeDTO employeeDTO);

    /**
     * 分页查询
     * @param employeePageQueryDTO
     * @return
     */
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 修改员工信息
     * @param employeeDTO
     */
    void updateUser(EmployeeDTO employeeDTO);

    /**
     * 根据主键获取员工数据
     * @param id
     * @return
     */
    Employee getById(Long id);

    /**
     * 启用禁用
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);

    /**
     * 修改密码
     * @param employeeUpdatePasswordDTO
     * @return
     */
    boolean updatePassword(EmployeeUpdatePasswordDTO employeeUpdatePasswordDTO);
}
