package com.springdemo.helloworld.Service;

import com.springdemo.helloworld.DTO.UserDTO;
import com.springdemo.helloworld.Entity.Users;
import com.springdemo.helloworld.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//import javax.transaction.Transactional;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

     private UserRepository userRepository;
     private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public UserService(){}

    @Autowired
    public UserService(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    private UserDTO mapToUserDTO(Users user){
        UserDTO userDto = new UserDTO();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getFirstName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }


    @Override
    public List<UserDTO> findAllUsers() {
        List<Users> users = userRepository.findAll();
        return users.stream()
                .map((user) -> mapToUserDTO(user))
                .collect(Collectors.toList());
    }

    @Override
    public void saveUser(UserDTO userDto) {
        Users user = new Users();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(user.getLastName());
        user.setEmail(userDto.getEmail());
        // encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));;
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
        Users user =userRepository.findById(userId).orElseThrow(() -> new IllegalStateException(
                "student with id" + userId +"does not exists"
        ));
        if(name!=null && name.length() > 0 && !Objects.equals(user.getLastName(),name)){
            user.setLastName(name);
        }
        if(email!=null && email.length() > 0 && !Objects.equals(user.getEmail(),email)){
            Users userOptional = userRepository.findByEmail(email);
            if(userOptional!=null){
                throw new IllegalStateException("email taken");
            }
            user.setEmail(email);
        }
    }

    @Override
    public Users findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private UserDTO convertEntityToDto(Users user){
        UserDTO userDto = new UserDTO();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    @Override
    public List<Object> isUserPresent(Users user) {
        boolean userExists = false;
        String message = null;
        Users existingUserEmail = userRepository.findByEmail(user.getEmail());
        if(existingUserEmail != null) {
            userExists = true;
            message = "Email Already Present!";
        }
        return Arrays.asList(userExists, message);
    }
}
