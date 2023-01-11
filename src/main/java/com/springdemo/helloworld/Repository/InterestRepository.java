package com.springdemo.helloworld.Repository;

import com.springdemo.helloworld.Entity.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface InterestRepository extends JpaRepository<Interest, Integer> {
    @Query("from Interest WHERE name in :tags")
    List<Interest> findInterests(@Param("tags") Collection<String> tag);

}
