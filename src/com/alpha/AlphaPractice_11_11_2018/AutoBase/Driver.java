package com.alpha.AlphaPractice_11_11_2018.AutoBase;

public class Driver {
    String name;
    int id;
    boolean isActive;
    Automobile automobile;


    public Driver() {
        this.id = 0 + (int)(Math.random() * 100);
        this.name = "Vasya";
        this.isActive = false;
        automobile = null;
    }

    public Driver(String name, int id, boolean isActive, Automobile automobile) {
        this.name = name;
        this.id = id;
        this.isActive = isActive;
        this.automobile = automobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Automobile getAutomobile() {
        return automobile;
    }

    public void setAutomobile(Automobile automobile) {
        this.automobile = automobile;
    }

    public void setRepair(Automobile automobile){
        Repair repair = new Repair(automobile);
    }

    public void finishTheTrip(Trip trip){
        trip.setFinished(true);
    }
}
