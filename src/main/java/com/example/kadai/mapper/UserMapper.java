package com.example.kadai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.kadai.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from sysuser")
    List<User> findAll();

    @Insert("INSERT into sysuser (username,picture,sex,management,birthday,email,phone,address,school,hobby) VALUES (#{username}, #{picture}, #{sex}, #{management}, #{birthday}, #{email}, #{phone}, #{address}, #{school}, #{hobby})")
   int insert(User user);

    int update(User user);

    @Update("update sysuser set del_flg=1 where id = #{id}")
    Integer deleteById(@Param("id") Integer id);

    @Select("select * from sysuser where " +
            "username like #{username} " +
            "or hobby like #{hobby} " +
            "or birthday like #{birthday} " +
            "or email like #{email} " +
            "or phone like #{phone} " +
            "or address like #{address} " +
            "or del_flg =0 limit #{pageNum}, #{pageSize}")
    List<User> selectPage(Integer pageNum, Integer pageSize, String username, String hobby, String birthday, String email, String address, String phone);

    @Select("select count(id) from sysuser where " +
            "username like #{username} " +
            "or hobby like #{hobby} " +
            "or birthday like #{birthday} " +
            "or email like #{email} " +
            "or phone like #{phone} " +
            "or address like #{address} " +
            "or del_flg =0 ")
    Integer SelectTotal(Integer pageNum, Integer pageSize, String username, String hobby, String birthday, String email, String address, String phone);
}
