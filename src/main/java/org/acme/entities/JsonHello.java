/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.acme.entities;

import javax.json.bind.annotation.JsonbProperty;

/**
 *
 * @author flax
 */
public class JsonHello {
    
    @JsonbProperty("greeting")
    private String myGreeting;
    
    public JsonHello() {
        myGreeting = "Greeting unset!";
    }
    
    public JsonHello(String greeting) {
        myGreeting = greeting;
    }

    /**
     * @return the myGreeting
     */
    public String getMyGreeting() {
        return myGreeting;
    }

    /**
     * @param myGreeting the myGreeting to set
     */
    public void setMyGreeting(String myGreeting) {
        this.myGreeting = myGreeting;
    }
    
}
