package com.sky.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("修改密码模型")
public class EmployeeUpdatePasswordDTO {
    private Integer employeeId;
    private String oldPassword;
    private String newPassword;
}
