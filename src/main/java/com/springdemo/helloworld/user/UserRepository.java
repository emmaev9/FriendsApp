package com.springdemo.helloworld.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    //select * from user where email = ?
    Optional<User> findUserByEmail(String email);
}
