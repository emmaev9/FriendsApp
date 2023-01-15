package com.springdemo.helloworld.Service;

import com.springdemo.helloworld.Entity.Photo;
import com.springdemo.helloworld.Repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoService {
    @Autowired
    PhotoRepository photoRepository;

    public PhotoService(PhotoRepository photoRepository){
        this.photoRepository = photoRepository;
    }

    public List<Photo> findAll(){
        return photoRepository.findAll();
    }

    //public Photo findPhotoById(Integer id){return photoRepository.findById(id);}

    public void savePhoto(Photo photo){
        photoRepository.save(photo);
    }

    public void deletePhoto(Photo photo){
        photoRepository.delete(photo);
    }
}
