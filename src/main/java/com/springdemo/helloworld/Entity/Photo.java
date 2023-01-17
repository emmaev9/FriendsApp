package com.springdemo.helloworld.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int photo_id;

    private String link;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    public int getPhoto_id() {
        return photo_id;
    }

    public void setPhoto_id(int photo_id) {
        this.photo_id = photo_id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
   }

    @Override
    public String toString() {
        return "Photo{" +
                "photo_id=" + photo_id +
                ", link='" + link + '\'' +
                ", user=" + user +
                '}';
    }
}
