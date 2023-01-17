package com.springdemo.helloworld.Service;


import com.springdemo.helloworld.Entity.Role;
import com.springdemo.helloworld.Entity.Users;
import com.springdemo.helloworld.Repository.RoleRepository;
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

   @Autowired
    private UserRepository userRepository;

   @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,  RoleRepository roleRepository
                           ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Users> findAllUsers() {
        List<Users> users = userRepository.findAll();
        return users;
    }

    @Override
    public void saveUser(Users users) {
        System.out.println("Saving user: " + users.getFirstName() + " " + users.getPassword());
        // encrypt the password using spring security
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        ;
        Role role = roleRepository.findByName("ROLE_ADMIN");
        if (role == null) {
            role = checkRoleExist();
        }
        users.setRoles(Arrays.asList(role));

        userRepository.save(users);
    }

    @Override
    public Users findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }



    @Override
    public List<Users> findAllPosibleMatches(String email) {
        List<Users> users = userRepository.findAll();
        users.remove(userRepository.findByEmail(email));
        return users;
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

    private Role checkRoleExist(){
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }


   /*
    public List<Object> isUserPresent(Users user) {
        boolean userExists = false;
        String message = null;
        Users existingUserEmail = userRepository.findByEmail(user.getEmail());
        if(existingUserEmail != null) {
            userExists = true;
            message = "Email Already Present!";
        }
        return Arrays.asList(userExists, message);
    }*/
}