package com.springdemo.helloworld;

;
import com.springdemo.helloworld.DTO.UserDTO;
import com.springdemo.helloworld.Entity.Users;
import com.springdemo.helloworld.Repository.UserRepository;
import com.springdemo.helloworld.Service.IUserService;
import com.springdemo.helloworld.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.List;

@Controller
public class HelloWorldController {

    private IUserService IuserService;

    public HelloWorldController(IUserService IuserService){
        this.IuserService = IuserService;
    }

    @GetMapping("/index")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(WebRequest request, Model model)
    {
        UserDTO user = new UserDTO();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDTO userDTO,
                               BindingResult result,
                               Model model){
        Users existingUser = IuserService.findUserByEmail(userDTO.getEmail());
        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDTO);
            return "/register";
        }

        IuserService.saveUser(userDTO);
        return "redirect:/register?success";
    }

    @GetMapping("/allusers")
    public String listUsers(Model model) {
        List<UserDTO> listUsers = IuserService.findAllUsers();
        model.addAttribute("listUsers", listUsers);

        return "users";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
