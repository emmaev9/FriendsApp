package com.springdemo.helloworld.user;

import com.springdemo.helloworld.Friend.Friend;
import com.springdemo.helloworld.Interest.Interest;
import com.springdemo.helloworld.Photo.Photo;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="userr", uniqueConstraints = @UniqueConstraint(columnNames = "email"))

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;


    @Transient
    private Integer age; //nu reprezinta o coloana in tabel

    private LocalDate birthDay;


    @OneToMany(mappedBy = "user")
    private List<Photo> photos;

    @OneToMany(mappedBy = "user")
    private List<Friend> friends;

    @OneToMany(mappedBy = "user")
    private List<Interest> interests;

    private String gender;
    private String work;
    private String school;
    private String city;
    private String about;

    public User(){}

    public User(Integer id, String firstName, String lastName, String email, String password, LocalDate birthDay, List<Photo> photos,
                List<Friend> friends, List<Interest> interests, String gender, String work, String school, String city, String about) {
        this.user_id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthDay = birthDay;
        this.photos = photos;
        this.friends = friends;
        this.interests = interests;
        this.gender = gender;
        this.work = work;
        this.school = school;
        this.city = city;
        this.about = about;
    }

    public User(String firstName, String lastName, String email, String password, LocalDate birthDay, List<Photo> photos,
                List<Friend> friends, List<Interest> interests, String gender, String work, String school, String city, String about) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthDay = birthDay;
        this.photos = photos;
        this.friends = friends;
        this.interests = interests;
        this.gender = gender;
        this.work = work;
        this.school = school;
        this.city = city;
        this.about = about;
    }

    public Integer getId() {
        return user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Integer id) {
        this.user_id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return Period.between(birthDay, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public List<Interest> getInterests() {
        return interests;
    }

    public void setInterests(List<Interest> interests) {
        this.interests = interests;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + user_id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", birthDay=" + birthDay +
                ", photos=" + photos +
                ", friends=" + friends +
                ", interests=" + interests +
                ", gender='" + gender + '\'' +
                ", work='" + work + '\'' +
                ", school='" + school + '\'' +
                ", city='" + city + '\'' +
                ", about='" + about + '\'' +
                '}';
    }
}
