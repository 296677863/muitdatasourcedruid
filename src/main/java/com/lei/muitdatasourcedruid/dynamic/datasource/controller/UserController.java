package com.lei.muitdatasourcedruid.dynamic.datasource.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lei.muitdatasourcedruid.dynamic.datasource.entity.User;
import com.lei.muitdatasourcedruid.dynamic.datasource.service.UserService;

@Controller
public class UserController {
	private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @RequestMapping("user/add")
    @ResponseBody
    public User getUser(Integer id,String name,Integer age) {
    	User user=new User();
    	user.setId(id);
    	user.setName(name);
    	user.setAge(age);
    	userService.addUser(user);
    	return user;
    }
    
    @RequestMapping("user/select1")
    @ResponseBody
    public List<User> getUser1() {
    	return userService.selectUser1();
    }

    @RequestMapping("user/select2")
    @ResponseBody
    public List<User> getUser2() {
    	return userService.selectUser2();
    }

}
