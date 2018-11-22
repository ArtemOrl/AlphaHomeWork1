package com.alpha.RailWay;

import java.util.Date;

public class Request {
    Station stationStart;
    Station stationFinish;
    Date dateOfTrip;
    boolean isRegistred;

    public Request(Station stationStart, Station stationFinish, Date dateOfTrip) {
        this.stationStart = stationStart;
        this.stationFinish = stationFinish;
        this.dateOfTrip = dateOfTrip;
    }

    public boolean isRegistred() {
        return isRegistred;
    }

    public void setRegistred(boolean registred) {
        isRegistred = registred;
    }

    public Station getStationStart() {
        return stationStart;
    }

    public Station getStationFinish() {
        return stationFinish;
    }
}
