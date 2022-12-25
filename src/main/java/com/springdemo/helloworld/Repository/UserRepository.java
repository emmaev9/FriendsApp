package com.springdemo.helloworld.Repository;
import com.springdemo.helloworld.DTO.UserDTO;
import com.springdemo.helloworld.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Users findByEmail(String email);

}
