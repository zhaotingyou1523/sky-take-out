package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    /**
     * 根据openid获取user对象
     * @param openid
     * @return
     */
    @Select("select * from user where openid = #{openid}")
    User findByOpenid(String openid);

    /**
     * 添加用户
     * @param user
     */
    @Insert("INSERT INTO user (openid, name, phone, sex, id_number, avatar, create_time) VALUES (#{openid},#{name},#{phone},#{sex},#{idNumber},#{avatar},#{createTime})")
    void insertUser(User user);

    @Select("select * from user where id = #{userId}")
    User getById(Long userId);
}
