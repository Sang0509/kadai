package com.example.kadai.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "sysuser")
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String picture;
    private String sex;
    private String management;
    private String birthday;
    private String email;
    private String phone;
    private String address;
    private String school;
    private String hobby;
    private int del_flg;
}
