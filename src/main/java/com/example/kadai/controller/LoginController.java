package com.example.kadai.controller;

import com.example.kadai.entity.User;
import com.example.kadai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Result;

@RestController
public class LoginController {

//    @Autowired
//    private UserService userService;
//    @PostMapping("/login")
//    public Result login(@RequestBody User user) {
//        log.info("yuangongdenglu: ()", user);
//        User e = userService.login(user);
//        return e != null?Result.success():Result.error("メールアドレスまたはパスワード間違いました");
//
//    }
}
