package com.springdemo.helloworld.Service;

import com.springdemo.helloworld.Entity.Interest;
import com.springdemo.helloworld.Repository.InterestRepository;
import com.springdemo.helloworld.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;

@Service
public class InterestService  {
    @Autowired
    InterestRepository interestRepository;

    public InterestService(InterestRepository interestRepository)
    {
        this.interestRepository = interestRepository;
    }


    public List<Interest> findInterests(List<String> interestIds){
        return interestRepository.findInterests(interestIds);
    }

    @Transactional
    public List<Interest> findAll(){
        return interestRepository.findAll();
    }
}
