package com.springdemo.helloworld;

;
import com.springdemo.helloworld.Entity.Interest;
import com.springdemo.helloworld.Entity.Photo;
import com.springdemo.helloworld.Entity.Users;
import com.springdemo.helloworld.Models.RegisterFormModel;
import com.springdemo.helloworld.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class HelloWorldController {


    @Autowired
    private UserService IuserService;

    @Autowired
    private PhotoService photoService;

    @Autowired
    private InterestService interestService;

    @Autowired
    FileService fileService;

   // @Autowired
   // private SecurityService securityService;




    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/afterlogin")
    public String afterLogin(){
        return "afterlogin";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model, HttpSession session){
        // create model object to store form data
        RegisterFormModel registerFormModel = new RegisterFormModel();
       // List<Photo> photos = new ArrayList<>();
       // model.addAttribute("photos", photos);
        model.addAttribute("registerFormModel", registerFormModel);
        return "register";
    }
    @PostMapping("/register")
    public String registration(@Valid @ModelAttribute RegisterFormModel registerFormModel,
                               //final @RequestParam("photo") MultipartFile[] photoFiles,
                               //@RequestParam("interest") List<String> interests,
                               //HttpServletRequest request,
                               HttpSession session,
                               BindingResult result,
                               Model model){

        Users existingUser = IuserService.findUserByEmail(registerFormModel.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("registerFormModel", registerFormModel);
            return "/register";
        }
        //Users currentUser = (Users)session.getAttribute("user");
        Users currentUser = new Users();
        currentUser.setFirstName(registerFormModel.getFirstName());
        currentUser.setLastName(registerFormModel.getLastName());
        currentUser.setEmail(registerFormModel.getEmail());
        currentUser.setBirthDay(registerFormModel.getBirthDay());
        currentUser.setGender(registerFormModel.getGender());
        currentUser.setPassword(registerFormModel.getPassword());
        currentUser.setWork(registerFormModel.getWork());
        currentUser.setSchool(registerFormModel.getSchool());
        currentUser.setAbout(registerFormModel.getAbout());


        List<Interest> interests = interestService.findInterests(registerFormModel.getInterests());
        System.out.println(interests);
        currentUser.setInterests(interests);

        try{
            for (int i = 0; i < registerFormModel.getPhotos().length; i++) {
                fileService.saveImage(registerFormModel.getPhotos()[i]);
                String photoPath = "/Upload/image/" + registerFormModel.getPhotos()[i].getOriginalFilename();
                Photo photo = new Photo();

                photo.setLink(photoPath);
                photo.setUser(currentUser);
                System.out.println(photo);
                photoService.savePhoto(photo);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        IuserService.saveUser(currentUser);
       // securityService.autoLogin(currentUser.getEmail(), currentUser.getPassword());
        System.out.println("Se salveaza : " + currentUser.getFirstName() + " " + currentUser.getLastName());
        return "process_register";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<Users> listUsers = IuserService.findAllUsers();
        model.addAttribute("users", listUsers);

        return "users";
    }


}
