package com.springdemo.helloworld.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

@Service
public class UserService implements IUserService {
   // @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private  UserRepository userRepository;

    public List<User> getUser(){
        return userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        Optional<User> userByEmail  = userRepository.findUserByEmail(user.getEmail());
        if(userByEmail.isPresent()){
            throw new IllegalStateException("email taken");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        userRepository.save(user);
    }

    public void deleteUser(Integer id) {
        boolean exists = userRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("student with id" + id +"does not exists");
        }
        userRepository.deleteById(id);
    }

    @Transactional
    public void updateUser(Integer userId, String name, String email) {
        boolean exists = userRepository.existsById(userId);
        if(!exists){
            throw new IllegalStateException("student with id" + userId +"does not exists");
        }
        User user =userRepository.findById(userId).orElseThrow(() -> new IllegalStateException(
                "student with id" + userId +"does not exists"
        ));
        if(name!=null && name.length() > 0 && !Objects.equals(user.getLastName(),name)){
            user.setLastName(name);
        }
        if(email!=null && email.length() > 0 && !Objects.equals(user.getEmail(),email)){
            Optional<User> userOptional = userRepository.findUserByEmail(email);
            if(userOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            user.setEmail(email);
        }
    }

    @Override
    public List<Object> isUserPresent(User user) {
        boolean userExists = false;
        String message = null;
        Optional<User> existingUserEmail = userRepository.findUserByEmail(user.getEmail());
        if(existingUserEmail.isPresent()){
            userExists = true;
            message = "Email Already Present!";
        }
        System.out.println("existingUserEmail.isPresent() - "+existingUserEmail.isPresent());
        return Arrays.asList(userExists, message);
    }
}
