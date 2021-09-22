package com.example.springchallenge.challenge;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;

@Entity
@Table(name ="Routes")
public class Routes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id; 
    private String stringName;    
    private Integer maxNumberCall;
    private Integer numberCall;
    private String page;

    
    public Routes(Integer id, String stringName, Integer maxNumberCall, Integer numberCall, String page) {
        this.id = id;
        this.setStringName(stringName);
        this.setMaxNumberCall(maxNumberCall);
        this.setNumberCall(numberCall);
        this.setPage(page);
    }
    public Routes(String stringName, Integer maxNumberCall, Integer numberCall, String page) {
        this.stringName = stringName;
        this.maxNumberCall = maxNumberCall;
        this.numberCall = numberCall;
        this.page = page;
    }
    public Routes() {
    }
    
    

    
    public String getStringName() {
        return stringName;
    }

    public void setStringName(String stringName) {
        this.stringName = stringName;
    }

    public Integer getMaxNumberCall() {
        return maxNumberCall;
    }

    public void setMaxNumberCall(Integer maxNumberCall) {
        this.maxNumberCall = maxNumberCall;
    }

    public Integer getNumberCall() {
        return numberCall;
    }

    public void setNumberCall(Integer numberCall) {
        this.numberCall = numberCall;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
    




    
}
