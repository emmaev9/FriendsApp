package com.springdemo.helloworld.Entity;

import  javax.persistence.CascadeType;
import  javax.persistence.Column;
import  javax.persistence.Entity;
import  javax.persistence.GeneratedValue;
import  javax.persistence.GenerationType;
import  javax.persistence.Id;
import  javax.persistence.JoinColumn;
import  javax.persistence.JoinTable;
import  javax.persistence.ManyToMany;
import  javax.persistence.OneToMany;
import  javax.persistence.Table;
import  javax.persistence.Transient;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")

public class Users {

 // private static final long serialVersionUID = 1L;
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Integer user_id;

 @Column(nullable=false)
 private String firstName;

 @Column(nullable = false)
 private  String lastName;

 @Column(nullable=false,  unique=true)
 private String email;

 @Column(nullable=false)
 private String password;


 @Transient
 private Integer age; //nu reprezinta o coloana in tabel

 @Column(nullable=false)
 //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
 private String birthDay;


 @OneToMany(mappedBy = "user")
 private List<Photo> photos;

 @ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
         CascadeType.DETACH, CascadeType.REFRESH})
 @JoinTable(
         name = "user_interest",
         joinColumns = {@JoinColumn(name = "user_id")},
         inverseJoinColumns = {@JoinColumn(name = "interest_id")}
 )
 private List<Interest> interests;

 @OneToMany(mappedBy = "user")
 private List<Like> likes;

 @Column(nullable=false)
 private String gender;

 @Column
 private String work;

 @Column
 private String school;

 @Column
 private String city;

 @Column
 @Size(max = 500,message = "About is max size = 500 character")
 private String about;

 @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
 @JoinTable(
         name="users_roles",
         joinColumns={@JoinColumn(name="user_id", referencedColumnName="user_id")},
         inverseJoinColumns={@JoinColumn(name="role_id", referencedColumnName="role_id")})
 private List<Role> roles = new ArrayList<>();


 public Integer getAge() {
  return 5/*Period.between(birthDay, LocalDate.now()).getYears(); */;
 }

 public void setAge(Integer age) {
  this.age = age;
 }

 public List<Role> getRoles() {
  return roles;
 }

 public void setRoles(List<Role> roles) {
  this.roles = roles;
 }
}