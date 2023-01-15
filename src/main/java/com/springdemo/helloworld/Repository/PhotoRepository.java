package com.springdemo.helloworld.Repository;

import com.springdemo.helloworld.Entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Integer>{
  //  @Query("SELECT t FROM Photo WHERE t.id = id")
  //  public Photo findPhotoById(@Param("id")Integer Id){

   // }

}
