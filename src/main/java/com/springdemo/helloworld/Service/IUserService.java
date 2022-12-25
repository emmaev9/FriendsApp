package com.springdemo.helloworld.Service;

import com.springdemo.helloworld.DTO.UserDTO;
import com.springdemo.helloworld.Entity.Users;

import java.util.List;

public interface IUserService {
    public void saveUser(UserDTO userDto);
    public List<Object> isUserPresent(Users user);
    List<UserDTO> findAllUsers();
    Users findUserByEmail(String email);


}
