package com.springdemo.helloworld.user;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner commandLineRunner(UserRepository repo){
        return args -> {
           /* User maria = new User(
                    "ana",
                    "maria",
                    "maria@yahoo.com",
                    LocalDate.of(2000, Month.JANUARY,3),
                    "sport");
            User flavia = new User(
                    "flavia",
                    "pop",
                    "flavia@yahoo.com",
                    LocalDate.of(2000, Month.DECEMBER,3),
                    "sport");
            repo.saveAll(
                    List.of(maria, flavia)
            );*/
        };
    }
}
