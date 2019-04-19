package com.example.a26083.penalty;

public class Player {
    public String name;
    public String shootPower;
    public String shootAccuracy;
    public String mindset;

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getShootPower(){
        return this.shootPower;
    }

    public void setShootPower(String shootPower){
        this.shootPower = shootPower;
    }

    public String getShootAccuracy(){
        return this.shootAccuracy;
    }

    public void setShootAccuracy(String shootAccuracy){
        this.shootAccuracy = shootAccuracy;
    }

    public String getMindset(){
        return this.mindset;
    }

    public void setMindset(String mindset){
        this.mindset = mindset;
    }

    public String getProfile(){
        return "Name: " + this.name + "\n" + "Shoot power: " + this.shootPower + "\n" + "Shoot accuracy: " + this.shootAccuracy + "\n" + "Mindset: " + this.mindset;
    }
}
