package com.springdemo.helloworld.Controller;

import com.springdemo.helloworld.Entity.Interest;
import com.springdemo.helloworld.Entity.Photo;
import com.springdemo.helloworld.Entity.Users;
import com.springdemo.helloworld.Models.RegisterFormModel;
import com.springdemo.helloworld.Service.FileService;
import com.springdemo.helloworld.Service.IUserService;
import com.springdemo.helloworld.Service.InterestService;
import com.springdemo.helloworld.Service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProfileEditController {
    @Autowired
    IUserService userService;

    @Autowired
    FileService fileService;

    @Autowired
    private PhotoService photoService;

    @Autowired
    private InterestService interestService;

    @GetMapping("/edit")
    public String editProfile(Model model, HttpSession session){
        Users user= (Users) session.getAttribute("user");
        if(user==null){
            return "redirect:/";
        }
        user = userService.findUserByEmail(user.getEmail());
        System.out.println(user);

        model.addAttribute("user",user);
        model.addAttribute("interest",interestService.findAll());
       // System.out.println(user.getNowYear());
        return "profile/edit-profile";
    }

    @PostMapping("/edit")
    public String edit(HttpServletRequest request, Model model, @ModelAttribute RegisterFormModel user,
                   //    @RequestParam("photo") MultipartFile photoFiles,
                   //    @RequestParam("interest_tag") String interest_tag,
                       HttpSession session){


        List<String> messages = new ArrayList<>();

            Users currentUser= (Users) session.getAttribute("user");

            currentUser.setAbout(user.getAbout());
            currentUser.setWork(user.getWork());
            currentUser.setSchool(user.getSchool());
            currentUser.setGender(user.getGender());
            currentUser.setCity(user.getCity());

            try{
                MultipartFile[] files = user.getPhotos();
                for (int i = 0; i < files.length; i++) {
                    fileService.saveImage(files[i]);
                    String photoPath = "/Upload/image/" + files[i].getOriginalFilename();
                    Photo photo = new Photo();

                    photo.setLink(photoPath);
                    photo.setUser(currentUser);
                    System.out.println(photo);
                    userService.saveUser(currentUser);
                    photoService.savePhoto(photo);
                }
            }catch(Exception e){
                System.out.println(e);
            }


            if(!user.getInterests().isEmpty()){
                List<Interest> interests = interestService.findInterests(user.getInterests());
                currentUser.setInterests(interests);
            }

            userService.saveUser(currentUser);
            System.out.println(currentUser);

        return "redirect:/profile";

    }

    @GetMapping("/{userId}/delete/{id}")
    public String deletePhoto(@PathVariable("userId") int userId, @PathVariable("id") int id){
        try {
            System.out.println(id);
           // photoService.deletePhoto(id);
            System.out.println("aaaa");

        }catch (Exception e){
            System.out.println(e);
        }

        return "redirect:/profile";
    }
}