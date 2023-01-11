package com.springdemo.helloworld.Service;

import com.springdemo.helloworld.Entity.Match;
import com.springdemo.helloworld.Repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MatchService {
    @Autowired
    MatchRepository matchRepository;

    @Transactional
    public void saveMatch(Match match) {
        matchRepository.save(match);
    }

}
