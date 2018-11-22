package com.alpha.AutoBase;

public class Repair {
    boolean repairIsStarted, repairIsFinshed;
    Automobile automobile;

    public Repair(Automobile automobile) {
        this.repairIsStarted = false;
        this.repairIsFinshed = false;
        this.automobile = automobile;
    }

    public Repair(boolean repairIsStarted, boolean repairIsFinshed, Automobile automobile) {
        this.repairIsStarted = repairIsStarted;
        this.repairIsFinshed = repairIsFinshed;
        this.automobile = automobile;
    }

    public boolean isRepairIsStarted() {
        return repairIsStarted;
    }

    public void setRepairIsStarted(boolean repairIsStarted) {
        this.repairIsStarted = repairIsStarted;
    }

    public boolean isRepairIsFinshed() {
        return repairIsFinshed;
    }

    public void setRepairIsFinshed(boolean repairIsFinshed) {
        this.repairIsFinshed = repairIsFinshed;
    }
}
