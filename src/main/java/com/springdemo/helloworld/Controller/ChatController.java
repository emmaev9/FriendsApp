package com.springdemo.helloworld.Controller;

import com.springdemo.helloworld.Entity.Users;
import com.springdemo.helloworld.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ChatController {

    @Autowired
    IUserService userService;

    @RequestMapping("/chat")
    public String index(HttpServletRequest request, Model model) {
        String username = (String) request.getSession().getAttribute("email");

        if (username == null || username.isEmpty()) {
            return "redirect:/chat/login";
        }
        model.addAttribute("email", username);

        return "chat/chat";
    }

    @RequestMapping(path = "/chat", method = RequestMethod.GET)
    public String showLoginPage() {
        return "chat";
    }

    @RequestMapping(path = "/chat/login", method = RequestMethod.POST)
    public String doLogin(HttpServletRequest request, @RequestParam(defaultValue = "") String username) {
        username = username.trim();

        if (username.isEmpty()) {
            return "chat/login";
        }
        request.getSession().setAttribute("username", username);

        return "redirect:/";
    }

    @RequestMapping(path = "/chat/logout")
    public String logout(HttpServletRequest request) {
        request.getSession(true).invalidate();

        return "redirect:/chat/login";
    }


    @GetMapping("/message")
    public String message(Model model, HttpSession session){
        Users user= (Users) session.getAttribute("user");

        if(user==null){
            return  "redirect:/home";
        }

        user=userService.findUserByEmail(user.getEmail());

        model.addAttribute("user",user);

        return "message";
    }
}
