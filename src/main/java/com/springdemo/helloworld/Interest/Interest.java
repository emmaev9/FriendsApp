package com.springdemo.helloworld.Interest;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springdemo.helloworld.user.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "interest")
public class Interest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interest_id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



    public Long getInterest_id() {
        return interest_id;
    }

    public void setInterest_id(Long interest_id) {
        this.interest_id = interest_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUsers(User user) {
        this.user = user;
    }
}
