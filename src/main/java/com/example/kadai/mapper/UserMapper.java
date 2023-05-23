package com.example.kadai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.kadai.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;


//@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from sysuser")
    List<User> findAll();

    @Insert("INSERT into sysuser (username,picture,sex,management,birthday,email,phone,address,school,hobby) VALUES (#{username}, #{picture}, #{sex}, #{management}, #{birthday}, #{email}, #{phone}, #{address}, #{school}, #{hobby})")
   int insert(User user);

    int update(User user);

    @Update("update sysuser set del_flg=1 where id = #{id}")
    Integer deleteById(@Param("id") Integer id);
    @Select("select * from sysuser where username like #{username} and hobby like #{hobby} and del_flg =0 limit #{pageNum}, #{pageSize}")
    List<User> selectPage(Integer pageNum, Integer pageSize, String username, String hobby);

    @Select("select count(id) from sysuser where del_flg=0")
    Integer SelectTotal();
}
