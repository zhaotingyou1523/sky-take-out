package com.sky.mapper;

import com.sky.entity.AddressBook;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AddressBookMapper {
    /**
     * 添加地址
     * @param addressBook
     */
    @Insert("insert into address_book (user_id, consignee, sex, phone, province_code, province_name, city_code, city_name, district_code, district_name, detail, label, is_default) VALUES (#{userId}, #{consignee}, #{sex},#{phone},#{provinceCode}, #{provinceName}, #{cityCode}, #{cityName},#{districtCode}, #{districtName}, #{detail}, #{label}, #{isDefault})")
    void save(AddressBook addressBook);

    /**
     * 展示地址簿列表
     * @param addressBook
     * @return
     */
    List<AddressBook> list(AddressBook addressBook);

    /**
     * 根据用户id修改默认地址
     * @param addressBook
     */
    @Update("update address_book set is_default = #{isDefault} where user_id = #{userId}")
    void updateIsDefaultByUserId(AddressBook addressBook);

    /**
     * 更新操作
     * @param addressBook
     */
    void update(AddressBook addressBook);

    /**
     * 根据主键id删除地址
     * @param id
     */
    @Delete("delete from address_book where id = #{id}")
    void deleteById(Long id);

    /**
     * 根据主键id查询地址
     * @param id
     */
    @Select("select * from address_book where id = #{id}")
    AddressBook getById(Long id);
}
