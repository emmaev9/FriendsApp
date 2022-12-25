package com.springdemo.helloworld.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDate;

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

    @NotEmpty
    private LocalDate birthDay;

    @NotEmpty
    private String gender;

    private String work;

    private String school;

    @NotEmpty
    private String city;

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
