package com.springdemo.helloworld;

import com.springdemo.helloworld.user.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloWorldController {
    @RequestMapping("/goodbye")
    public  String goodbye(){
        return "Goodbye";
    }
}
