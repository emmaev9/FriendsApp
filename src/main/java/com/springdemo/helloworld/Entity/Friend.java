package com.springdemo.helloworld.Entity;

import javax.persistence.*;

@Entity
@Table(name = "friend")
public class Friend {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer friend_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    public Integer getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(Integer friend_id) {
        this.friend_id = friend_id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}