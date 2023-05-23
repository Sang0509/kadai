package com.example.kadai.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.kadai.entity.User;
import com.example.kadai.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {
    public boolean saveuser(User user) {
        if (user.getId() == null){
            save(user); }
        else {
            return updateById(user);

        }



            return false;
    }

    public User login(User user) {


        return user;
    }


    //@Autowired
   // private UserMapper userMapper;

    //public int save(User user) {
        //if (user.getId() == null) {
           // return userMapper.insert(user);
        //} else {
            //return userMapper.update(user);
        //}

    //}
}
