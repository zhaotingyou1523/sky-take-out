package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.PasswordConstant;
import com.sky.constant.StatusConstant;
import com.sky.context.BaseContext;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.dto.EmployeeUpdatePasswordDTO;
import com.sky.entity.Employee;
import com.sky.exception.AccountLockedException;
import com.sky.exception.AccountNotFoundException;
import com.sky.exception.BaseException;
import com.sky.exception.PasswordErrorException;
import com.sky.mapper.EmployeeMapper;
import com.sky.result.PageResult;
import com.sky.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 员工登录
     *
     * @param employeeLoginDTO
     * @return
     */
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        //1、根据用户名查询数据库中的数据
        Employee employee = employeeMapper.getByUsername(username);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (employee == null) {
            //账号不存在
            throw new BaseException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对
        // TODO 后期需要进行md5加密，然后再进行比对
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        log.info(password);
        if (!password.equals(employee.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (employee.getStatus() == StatusConstant.DISABLE) {
            //账号被锁定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        //3、返回实体对象
        return employee;
    }

    /**
     * 新增员工
     * @param employeeDTO
     * @return
     */
    public boolean Insert(EmployeeDTO employeeDTO){
        System.out.println("当前id " + Thread.currentThread().getId());
        Employee employee = new Employee();

        //对象属性拷贝
        BeanUtils.copyProperties(employeeDTO, employee);

        //设置账号的状态,默认正常状态1表示正常0表示锁定
        employee.setStatus(StatusConstant.ENABLE);
//
//        //设置密码
        employee.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));
//
//        //设置创建时间和修改时间
//        employee.setCreateTime(LocalDateTime.now());
//        employee.setUpdateTime(LocalDateTime.now());
//
//        //设置创建人和修改人
//        //TODO 后续改为当前登陆用户的id
//        employee.setCreateUser(BaseContext.getCurrentId());
//        employee.setUpdateUser(BaseContext.getCurrentId());
        int insert = employeeMapper.insert(employee);
        return insert > 0;
    }

    /**
     * 分页查询
     * @param employeePageQueryDTO
     * @return
     */
    public PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO) {
        //开始分页查询
        PageHelper.startPage(employeePageQueryDTO.getPage(),employeePageQueryDTO.getPageSize());
        Page<Employee> page = employeeMapper.pageQuery(employeePageQueryDTO);
        long total = page.getTotal();
        List<Employee> result = page.getResult();
        return new PageResult(total,result);
    }

    /**
     * 修改员工信息
     * @param employeeDTO
     */
    public void updateUser(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        employeeMapper.update(employee);
    }

    /**
     * 根据id获取用户
     * @param id
     * @return
     */
    public Employee getById(Long id) {
        Employee employee = employeeMapper.getById(id);
        employee.setPassword("*******");
        return employee;
    }

    /**
     * 启用禁用状态
     * @param status
     * @param id
     */
    public void startOrStop(Integer status, Long id) {
        Employee employee = Employee.builder()
                .id(id)
                .status(status)
                .build();
        employeeMapper.update(employee);
    }

    /**
     * 修改密码
     * @param employeeUpdatePasswordDTO
     * @return
     */
    public boolean updatePassword(EmployeeUpdatePasswordDTO employeeUpdatePasswordDTO) {
        String old = DigestUtils.md5DigestAsHex(employeeUpdatePasswordDTO.getOldPassword().getBytes());
        Employee employee = employeeMapper.getById(BaseContext.getCurrentId());
        System.out.println(employee);
        String password = employee.getPassword();
        if (password.equals(old)) {
            employee.setPassword(DigestUtils.md5DigestAsHex(employeeUpdatePasswordDTO.getNewPassword().getBytes()));
            employeeMapper.update(employee);
            return true;
        }else {
            return false;
        }
    }

}
