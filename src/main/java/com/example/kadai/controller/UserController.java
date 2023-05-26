package com.example.kadai.controller;



import com.example.kadai.entity.User;
import com.example.kadai.mapper.UserMapper;
import com.example.kadai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;


    // 新增和修改
    @PostMapping("/add")
    public int save(@RequestBody User user) {
        return userService.saveuser(user);

    }

    // 查询所有数据　
    @GetMapping
    public List<User> findAll() {
        return userService.list();

    }

    //删除数据　データ削除
    @GetMapping("/del/{id}")
    public boolean delete(@PathVariable Integer id) {

        return userService.removeById(id);
    }

    //分页查询　ページ割り
    @GetMapping("/page")
   public Map<String, Object> findPage(@RequestParam Integer pageNum,
                                       @RequestParam Integer pageSize,
                                       @RequestParam String username,
                                       @RequestParam String hobby,
                                       @RequestParam String birthday,
                                       @RequestParam String address,
                                       @RequestParam String phone,
                                       @RequestParam String email) {
        pageNum = (pageNum - 1) * pageSize;
        //氏名より检索
        username = "%" + username + "%";
        hobby = "%" + hobby + "%";
        birthday = "%" + birthday + "%";
        email = "%" + email + "%";
        address = "%" + address + "%";
        phone = "%" + phone + "%";
       List<User> data = userMapper.selectPage(pageNum, pageSize, username, hobby, birthday, email, address, phone);
        System.out.println("AAAAAA"+username+"BBBB"+hobby+"CCCC"+birthday+"DDDDD"+email+"EEEEE"+address+"FFFFFF"+phone);
        Integer total = userMapper.SelectTotal(pageNum, pageSize, username, hobby, birthday, email, address, phone);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }
    //分页查询　mybatis plus的方式


}



