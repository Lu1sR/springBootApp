package com.example.springchallenge.challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChallengeService {
    private final ChallengeRepository challengeRepository;

    @Autowired
    public ChallengeService(ChallengeRepository challengeRepository) {
        this.challengeRepository = challengeRepository;
    }

    public List<Routes> getStrings(String stringName){
        return challengeRepository.findAll();
    }

    public Routes getSelectedString(String stringName){
        List<Routes> selectedString = challengeRepository.findByStringName(stringName);
        if(selectedString.size() == 0){
            return new Routes("notFound", 0, 0, "404.html");
        }else{
            return challengeRepository.findByStringName(stringName).get(0);
        }
        
    }
    public Boolean updateString(Integer id){
        Routes route = challengeRepository.findById(id).orElse(new Routes(0,"notFound", 0, 0, "404.html"));
        if(route.getId() != 0){
            route.setNumberCall(route.getNumberCall()+1);
            challengeRepository.save(route);
            return true;

        }else {
            return false;
        }
        

    }

}
