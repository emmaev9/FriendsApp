package com.springdemo.helloworld.Friend;

import com.springdemo.helloworld.user.User;

import javax.persistence.*;

@Entity
@Table(name = "friends")
public class Friend {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer friend_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Integer getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(Integer friend_id) {
        this.friend_id = friend_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
