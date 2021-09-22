package com.example.springchallenge.challenge;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ChallengeRepository extends JpaRepository<Routes, Integer> {
    List<Routes> findByStringName(String stringName);
}
