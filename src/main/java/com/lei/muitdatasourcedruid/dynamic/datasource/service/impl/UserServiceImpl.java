package com.lei.muitdatasourcedruid.dynamic.datasource.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lei.muitdatasourcedruid.dynamic.datasource.annotation.DS;
import com.lei.muitdatasourcedruid.dynamic.datasource.entity.User;
import com.lei.muitdatasourcedruid.dynamic.datasource.mapper.UserMapper;
import com.lei.muitdatasourcedruid.dynamic.datasource.service.UserService;

@Service
@DS("slave")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @DS("master") //默认是master可以省略这个注解,如果在类上注解了其他库，则@DS("master")不能省略
    @Override
    public void addUser(User user) {
        userMapper.addUser(user.getName(), user.getAge());
    }

    @DS("slave_1")
    @Override
    public List selectUser1() {
        return userMapper.selectUsers();
    }

    @DS("master")
    @Override
    public List selectUser2() {
        return userMapper.selectUsers();
    }

    //这个slave随机库
    @Override
    public List selectUser3() {
        return userMapper.selectUsers();
    }

}
