package com.lei.muitdatasourcedruid.dynamic.datasource.service;


import java.util.List;

import com.lei.muitdatasourcedruid.dynamic.datasource.entity.User;

public interface UserService {

    void addUser(User user);

    List selectUser1();

    List selectUser2();

    List selectUser3();
}
