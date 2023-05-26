package com.example.kadai.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.kadai.entity.User;
import com.example.kadai.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {
    @Autowired
    private UserMapper userMapper;

    public int saveuser(User user) {
        if (user.getId() == null) {
            return userMapper.insert(user);
        } else {
            return userMapper.update(user);

        }
    }


}