package com.sky.service;

import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;

import java.io.IOException;

public interface UserService {
    /**
     * 微信登录
     * @param userLoginDTO
     * @return
     */
    User wxLogin(UserLoginDTO userLoginDTO) throws IOException;

    /**
     * 获得openid
     * @param code
     * @return
     * @throws IOException
     */
    String getOpenid(String code) throws IOException;
}
