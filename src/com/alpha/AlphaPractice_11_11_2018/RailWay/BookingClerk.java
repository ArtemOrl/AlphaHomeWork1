package com.alpha.AlphaPractice_11_11_2018.RailWay;

import java.util.ArrayList;
import java.util.List;

public class BookingClerk {
    int id;
    String name;
    List<Station> route;

    public BookingClerk(String name) {
        this.id = 0 + (int)(Math.random() * 100);
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String createNewTrain(String name, int id, List<Station> route){
        Train train = new Train(name, id, route);
        RailWaySystem.getInstance().listOfTrains.add(train);
        return "New train has been created succesfuly";
    }

    public List<Station> createNewTrainRoute(Station... stations){
        List<Station> list = new ArrayList<>();
        for (int i = 0; i < stations.length; i++) {
            list.add(stations[i]);
        }
        System.out.println("List of stations has been created succefully");
        return list;
    }

    public Station createStation(String name, int id, String address){
        return new Station(name, id, address);
    }

    public void setNewCostPerStation(long cost){
        Cost.getInstance().setCostPerStation(cost);
        System.out.println("New Cost has been set succesfully");
    }
}
