package com.example.kadai.controller;

import com.example.kadai.mapper.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private Login login;

    @GetMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password) {
        Map<String, Object> res = new HashMap<>();
        String bool = login.login(username,password);
        res.put("bool", bool);
        return bool;
    }
}