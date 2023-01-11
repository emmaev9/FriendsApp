package com.springdemo.helloworld.Service;


import com.springdemo.helloworld.Entity.Users;

import java.util.List;

public interface IUserService {
    public void saveUser(Users users);
    List<Users> findAllUsers();
    Users findUserByEmail(String email);



}