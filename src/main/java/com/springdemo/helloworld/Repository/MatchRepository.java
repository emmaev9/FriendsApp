package com.springdemo.helloworld.Repository;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.springdemo.helloworld.Entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository<Match, Integer> {
}
