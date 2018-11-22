package com.alpha.AutoBase;

public class Trip {
    boolean isStarted, isFinished;
    private Driver driver = null;

    public Trip(){
        this.isStarted = false;
        this.isFinished = false;
    }

    public Trip(boolean isStarted, boolean isFinished, Driver driver) {
        this.isStarted = isStarted;
        this.isFinished = isFinished;
        this.driver = driver;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
