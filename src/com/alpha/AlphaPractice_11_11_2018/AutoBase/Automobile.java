package com.alpha.AlphaPractice_11_11_2018.AutoBase;

public class Automobile {
    String condition;
    boolean isActive;

    public Automobile(String condition, boolean isActive) {
        this.condition = condition;
        this.isActive = isActive;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
