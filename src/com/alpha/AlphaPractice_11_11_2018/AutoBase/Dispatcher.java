package com.alpha.AutoBase;

public class Dispatcher {
    String name;
    int id;

    public Dispatcher(String name, int id) {
        this.name = name;
        this.id = id;
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

    public void createTrip(Driver driver, Automobile automobile){
        Trip trip = new Trip();
        trip.setDriver(driver);
        driver.setAutomobile(automobile);
    }

    public String setDriverNotActive(Driver driver){
        driver.setActive(false);
        return "Driver is not active.";
    }
}
