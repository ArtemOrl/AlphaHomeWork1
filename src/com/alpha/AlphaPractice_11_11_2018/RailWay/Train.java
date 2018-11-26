package com.alpha.AlphaPractice_11_11_2018.RailWay;

import java.util.ArrayList;
import java.util.List;

public class Train {
    String name;
    int id;
    List<Station> route = new ArrayList<>();

    public Train(String name, int id, List<Station> route) {
        this.name = name;
        this.id = id;
        this.route = route;
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

    public List<Station> getRoute() {
        return route;
    }

    public void setRoute(List<Station> route) {
        this.route = route;
    }
}
