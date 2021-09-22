package com.example.springchallenge.challenge;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(path = "api/v1/challenge")
public class ChallengeController {
    private final ChallengeService challengeService;

    @Autowired
    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    // route that checks the number of allowed times and show the correct html page. 
    @GetMapping("/{name}")
    public ModelAndView redirectWithUsingRedirectPrefix(ModelMap model,HttpServletResponse response, @PathVariable String name) {
        Routes selectedString =  challengeService.getSelectedString(name);
        
        if(selectedString.getNumberCall() != selectedString.getMaxNumberCall()){
            if(!challengeService.updateString(selectedString.getId())) return new ModelAndView("redirect:/404.html", model);    
            response.addCookie(new Cookie("COOKIENAME", "123456"));
            
            return new ModelAndView("redirect:/%s".formatted(selectedString.getPage()), model);    
        }else{
            
            return new ModelAndView("redirect:/404.html", model);    
        }
        
    } 
    
}
