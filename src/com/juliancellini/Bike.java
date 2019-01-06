package com.juliancellini;

import java.net.SecureCacheResponse;

public class Bike {

    private String name;
    private Schedule schedule;

    public Bike(String name){
        this.name = name;
        this.schedule = new Schedule();
    }

    public Schedule getSchedule(){
        return this.schedule;
    }

    public String getName(){
        return this.name;
    }

}
