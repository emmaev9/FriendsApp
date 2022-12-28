package com.springdemo.helloworld;

;
import com.springdemo.helloworld.DTO.UserDTO;
import com.springdemo.helloworld.Entity.Users;
import com.springdemo.helloworld.Repository.UserRepository;
import com.springdemo.helloworld.Service.IUserService;
import com.springdemo.helloworld.Service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class HelloWorldController {


    @Autowired
    private IUserService IuserService;

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login.html";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        UserDTO user = new UserDTO();
        model.addAttribute("user", user);
        return "register";
    }
    @PostMapping("/register")
    public String registration(@Valid @ModelAttribute("user") UserDTO userDto,
                               BindingResult result,
                               Model model){
        Users existingUser = IuserService.findUserByEmail(userDto.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/register";
        }

        IuserService.saveUser(userDto);
        return "redirect:/process_register";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<UserDTO> listUsers = IuserService.findAllUsers();
        model.addAttribute("users", listUsers);

        return "users";
    }


}
