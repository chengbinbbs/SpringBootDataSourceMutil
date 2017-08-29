package com.chengbinbbs.controller;

import com.chengbinbbs.model.User;
import com.chengbinbbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 用户中心
 *
 * @author zhangcb
 * @created 2017-07-10 17:37.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userInfoService;

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

    @RequestMapping("/user/{name}")
    public User findByName(@PathVariable String name){
        return userInfoService.findByUserName(name);
    }

    @RequestMapping("/user/add")
    public int addUser(@Valid User user, BindingResult result){
        if(result.hasErrors()){
            System.out.println("添加用户异常");
            return -1;
        }
        return userInfoService.save(user);
    }
}
