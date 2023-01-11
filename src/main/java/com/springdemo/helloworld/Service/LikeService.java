package com.springdemo.helloworld.Service;

import com.springdemo.helloworld.Entity.Like;
import com.springdemo.helloworld.Repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class LikeService {
    @Autowired
    LikeRepository likeRepository;

    @Transactional
    public void saveLike(Like like){
        likeRepository.save(like);
    }

}
