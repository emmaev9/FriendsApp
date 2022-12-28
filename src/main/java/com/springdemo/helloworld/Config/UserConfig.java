/*package com.springdemo.helloworld.Config;

import com.springdemo.helloworld.DTO.UserDTO;
import com.springdemo.helloworld.Entity.Users;
import com.springdemo.helloworld.Repository.UserRepository;
import com.springdemo.helloworld.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class UserConfig {


    @Bean
    CommandLineRunner commandLineRunner(UserRepository repo){
        return args -> {
            Users maria = new Users(
                    "ana",
                    "maria",
                    "maria@yahoo.com",
                    "password",
                    LocalDate.of(2000, Month.JANUARY,3),
                    "sport", "work", "city", "about","about");
            Users flavia = new Users(
                    "flavia",
                    "maria",
                    "flavia@yahoo.com",
                    "password",
                    LocalDate.of(2000, Month.JANUARY,3),
                    "sport", "work", "city", "about","about");

            repo.saveAll(List.of(maria, flavia));
        };

    }
    @Bean
    PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

}*/