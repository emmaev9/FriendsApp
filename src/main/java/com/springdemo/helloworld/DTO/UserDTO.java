package com.springdemo.helloworld.DTO;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


import com.springdemo.helloworld.Entity.Friend;
import com.springdemo.helloworld.Entity.Interest;
import com.springdemo.helloworld.Entity.Photo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Integer user_id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;

    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;
    @NotEmpty(message = "Password should not be empty")
    private String password;

    @Past
    private LocalDate birthDay;

    @NotEmpty
    private String gender;

    private String work;

    private String school;

    @NotEmpty
    private String city;

    @NotEmpty
    private List<Photo> photos;

    private List<Friend> friends;

    private List<Interest> interests;

    private String about;

    public UserDTO(String firstName, String lastName, String email, String password, LocalDate birthDay, String gender, String work, String school, String city, String about) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthDay = birthDay;
        this.gender = gender;
        this.work = work;
        this.school = school;
        this.city = city;
        this.about = about;
    }
}
