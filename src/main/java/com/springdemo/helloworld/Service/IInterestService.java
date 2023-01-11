package com.springdemo.helloworld.Service;

import com.springdemo.helloworld.Entity.Interest;

import java.util.List;

public interface IInterestService {
    List<Interest> findInterests(List<String> interestIds);
}
