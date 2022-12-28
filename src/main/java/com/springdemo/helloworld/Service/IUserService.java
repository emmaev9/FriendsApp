package com.springdemo.helloworld.Service;

import com.springdemo.helloworld.DTO.UserDTO;
import com.springdemo.helloworld.Entity.Users;

import java.util.List;

public interface IUserService {
    public void saveUser(UserDTO userDto);
    List<UserDTO> findAllUsers();
    Users findUserByEmail(String email);


}