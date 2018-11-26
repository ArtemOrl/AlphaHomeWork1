package com.alpha.AlphaPractice_11_11_2018.RailWay;

import java.util.Date;
import java.util.List;

public class Passenger {
    Request request;
    String name;
    int id;

    public Passenger(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public Request createRequest(Station stationStart, Station stationFinish, Date dateOfTrip){
        request = new Request(stationStart, stationFinish, dateOfTrip);
        return request;
    }

    public void seeTrains(){
        List<Train> trains = RailWaySystem.getInstance().searchTrain(request.getStationStart(),
                request.stationFinish);
        for (int i = 0; i < trains.size(); i++) {
            System.out.println(trains.get(i).getId() + " " + trains.get(i).getName() +
                    " " + trains.get(i).getRoute());
        }
    }

    public Bill choiceOfTrain(int id, Station stationStart, Station stationFinish){
        Bill bill = null;
        List<Train> trains = RailWaySystem.getInstance().searchTrain(request.getStationStart(),
                request.stationFinish);
        for (int i = 0; i < trains.size(); i++) {
            if (trains.get(i).getId() == id) {
                bill = createBill(Cost.getInstance().countCost(Cost.getInstance().getCostPerStation(),
                        RailWaySystem.getInstance().countNumberOfStations(trains.get(i), stationStart, stationFinish)));
            }
        }
        return bill;
    }

    public Bill createBill(long amount){
        Bill bill = new Bill(amount);
        return bill;
    }
}
