package com.springdemo.helloworld.user;

import java.util.List;

public interface IUserService {
    public void saveUser(User user);
    public List<Object> isUserPresent(User user);
}
